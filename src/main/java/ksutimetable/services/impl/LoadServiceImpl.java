package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Building;
import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.entities.Group;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoadServiceImpl implements LoadService {

    private final RequestService requestService;
    private final BuildingService buildingService;
    private final CabinetService cabinetService;
    private final UserService userService;
    private final FacultyService facultyService;
    private final DirectionService directionService;
    private final GroupService groupService;
    private final TimetableService timetableService;

    @Override
    public ResponseModel loadData() {

        log.info("Started loading data");

        loadBuildings();
        loadUsers();
        loadFaculties();

        log.info("Finished loading data");

        return new ResponseModel(200, Constants.DATA_HAS_BEEN_LOADED);
    }

    private void loadBuildings() {
        log.info("Start loading buildings");
        requestService.getBuildingsRequest()
                .doOnComplete(() -> log.info("All buildings has been loaded"))
                .subscribe(building -> {
                    buildingService.saveBuilding(building);
                    loadCabinets(building);
                });
    }

    private void loadCabinets(Building building) {

        var buildingName = building.getTitle();

        log.info(
                String.format(
                        "Start loading cabinets for building %s",
                        buildingName
                )
        );
        requestService.getCabinetsByBuildingRequest(building.getId())
                .doOnComplete(() -> log.info(
                        String.format(
                                "All cabinets for building %s has been loaded",
                                buildingName
                        )
                ))
                .subscribe(cabinet -> {
                    cabinet.setBuilding(building);
                    cabinetService.saveCabinet(cabinet);
                });
    }

    private void loadUsers() {
        log.info("Start loading users");
        requestService.getUsersRequest()
                .doOnComplete(() -> log.info("All users has been loaded"))
                .subscribe(userService::saveUser);
    }

    private void loadFaculties() {
        log.info("Start loading faculties");
        requestService.getFacultiesRequest()
                .doOnComplete(() -> log.info("All faculties has been loaded"))
                .subscribe(faculty -> {
                    facultyService.saveFaculty(faculty);
                    loadDirections(faculty);
                });
    }

    private void loadDirections(Faculty faculty) {

        var facultyName = faculty.getTitle();

        log.info(
                String.format(
                        "Start loading directions for faculty %s",
                        facultyName
                )
        );
        requestService.getDirectionsByFacultyRequest(faculty.getId())
                .doOnComplete(() -> log.info(
                        String.format(
                                "All directions for faculty %s has been loaded",
                                facultyName
                        )
                ))
                .subscribe(direction -> {
                    direction.setFaculty(faculty);
                    directionService.saveDirection(direction);
                    loadGroups(direction);
                });
    }

    private void loadGroups(Direction direction) {

        var directionName = direction.getTitle();

        log.info(
                String.format(
                        "Start loading groups for direction %s",
                        directionName
                )
        );
        requestService.getGroupByDirectionRequest(direction.getId())
                .doOnComplete(() -> log.info(
                        String.format(
                                "All groups for direction %s has been loaded",
                                directionName
                        )
                ))
                .subscribe(group -> {
                    group.setDirection(direction);
                    groupService.saveGroup(group);
                    loadTimetables(group);
                });
    }

    private void loadTimetables(Group group) {

        var groupName = group.getTitle();

        log.info(
                String.format(
                        "Start loading timetable for group %s",
                        groupName
                )
        );
        requestService.getTimetableByGroupRequest(group.getId())
                .doOnComplete(() -> log.info(
                        String.format(
                                "All lessons for group %s has been loaded",
                                groupName
                        )
                ))
                .subscribe(timetable -> {
                    timetable.setGroup(group);
                    timetableService.saveLesson(timetable);
                });
    }
}
