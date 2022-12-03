package ksutimetable.services.impl;

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
        log.info("Загрузка данных...");
        loadBuildings();
        loadCabinets();
        loadUsers();
        loadFaculties();
        loadDirections();
        loadGroups();
        loadTimetables();
        log.info("Загрузка данных завершена");
        return new ResponseModel(200, "Данные загружены");
    }

    private void loadBuildings() {
        log.info("Загрузка строений...");
        var buildings = requestService.getBuildingsRequest();
        var buildingsJson = MapperService.mapListToJsonString(buildings);
        buildingService.saveBuildings(buildingsJson);
        log.info("Загрузка строений завершена");
    }

    private void loadCabinets() {
        log.info("Загрузка кабинетов...");
        var buildings = buildingService.getBuildings();
        buildings.forEach(building -> {
            var cabinets = requestService.getCabinetsByBuildingRequest(building.getId());
            cabinets.forEach(cabinet -> cabinet.setBuilding(building));
            var cabinetsJson = MapperService.mapListToJsonString(cabinets);
            cabinetService.saveCabinets(cabinetsJson);
        });
        log.info("Загрузка кабинетов завершена");
    }

    private void loadUsers() {
        log.info("Загрузка пользователей...");
        var users = requestService.getUsersRequest();
        userService.saveUsers(users);
        log.info("Загрузка пользователей завершена");
    }

    private void loadFaculties() {
        log.info("Загрузка институтов...");
        var faculties = requestService.getFacultiesRequest();
        facultyService.saveFaculties(faculties);
        log.info("Загрузка институтов завершена");
    }

    private void loadDirections() {
        log.info("Загрузка направлений подготовки...");
        var faculties = facultyService.getFaculties();
        faculties.forEach(faculty -> {
            var directions = requestService.getDirectionsByFacultyRequest(faculty.getId());
            directions.forEach(direction -> direction.setFaculty(faculty));
            directionService.saveDirections(directions);
        });
        log.info("Загрузка направлений подготовки завершена");
    }

    private void loadGroups() {
        log.info("Загрузка групп...");
        var directions = directionService.getDirections();
        directions.forEach(direction -> {
            var groups = requestService.getGroupByDirectionRequest(direction.getId());
            groups.forEach(group -> group.setDirection(direction));
            groupService.saveGroups(groups);
        });
        log.info("Загрузка групп завершена");
    }

    private void loadTimetables() {
        log.info("Загрузка расписаний для групп...");
        var groups = groupService.getGroups();
        groups.forEach(group -> {
            var timetables = requestService.getTimetableByGroupRequest(group.getId());
            timetables.forEach(timetable -> timetable.setGroup(group));
            var timetablesJson = MapperService.mapListToJsonString(timetables);
            timetableService.saveTimetables(timetablesJson);
        });
        log.info("Загрузка расписаний для групп завершена");
    }
}
