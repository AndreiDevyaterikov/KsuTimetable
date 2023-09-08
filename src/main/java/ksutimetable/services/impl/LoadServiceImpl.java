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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;


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

    @Qualifier("taskPoolConfig")
    private final ThreadPoolTaskExecutor taskExecutor;

    @Override
    public ResponseModel loadData() {

        CountDownLatch threadLatch = new CountDownLatch(2);

        log.info("Started loading data");

        taskExecutor.execute(() -> {
            try {
                log.info("Load buildings with cabinets started");
                loadBuildings();
            } finally {
                threadLatch.countDown();
                log.info("Load buildings with cabinets finished");
            }
        });

        taskExecutor.execute(() -> {
            try {
                log.info("Load users started");
                loadUsers();
            } finally {
                threadLatch.countDown();
                log.info("Load users finished");
            }
        });


        try {
            threadLatch.await();
            loadFaculties();
            Thread.sleep(5); //Дождаться отображения лога в последнем потоке в loadFaculties()
            log.info("Finished loading data");
            return new ResponseModel(200, Constants.DATA_HAS_BEEN_LOADED);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBuildings() {

        var buildings = requestService.getBuildings();

        CountDownLatch threadLatch = new CountDownLatch(buildings.size());

        for (int indexThread = 0; indexThread < buildings.size(); indexThread++) {
            int finalIndexThread = indexThread;

            taskExecutor.execute(() -> {
                try{
                    var building = buildings.get(finalIndexThread);
                    log.info("Started loading cabinets for {}", building.getTitle());
                    buildingService.saveBuilding(building);
                    loadCabinets(building);
                } finally {
                    threadLatch.countDown();
                    log.info("Finished loading cabinets for {}", buildings.get(finalIndexThread).getTitle());
                }
            });
        }
    }

    private void loadCabinets(Building building) {
        requestService.getCabinetsByBuilding(building.getId())
                .stream()
                .filter(cabinet -> cabinet.getTitle().contains(building.getTitle()))
                .forEach(cabinet -> {
                    cabinet.setBuilding(building);
                    cabinetService.saveCabinet(cabinet);
                });
    }

    private void loadUsers() {
        requestService.getUsers().forEach(userService::saveUser);
    }

    private void loadFaculties() {

        var faculties = requestService.getFaculties();
        CountDownLatch threadLatch = new CountDownLatch(faculties.size());

        for (int indexThread = 0; indexThread < faculties.size(); indexThread++) {

            int finalIndexThread = indexThread;
            taskExecutor.execute(() -> {
               try {
                   var faculty = faculties.get(finalIndexThread);
                   log.info("Loading data for {} started", faculty.getTitle());
                   facultyService.saveFaculty(faculty);
                   loadDirections(faculty);
               } finally {
                   threadLatch.countDown();
                   log.info("Loading data for {} finished", faculties.get(finalIndexThread).getTitle());
               }
            });
        }

        try {
            threadLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadDirections(Faculty faculty) {
        requestService.getDirectionsByFaculty(faculty.getId())
                .forEach(direction -> {
                    direction.setFaculty(faculty);
                    directionService.saveDirection(direction);
                    loadGroups(direction);
                });
    }

    private void loadGroups(Direction direction) {
        requestService.getGroupsByDirection(direction.getId())
                .forEach(group -> {
                    group.setDirection(direction);
                    groupService.saveGroup(group);
                    loadLessons(group);
                });
    }

    private void loadLessons(Group group) {

        requestService.getLessonsByGroup(group.getId())
                .forEach(timetable -> {
                    timetable.setGroup(group);
                    timetableService.saveLesson(timetable);
                });
    }
}
