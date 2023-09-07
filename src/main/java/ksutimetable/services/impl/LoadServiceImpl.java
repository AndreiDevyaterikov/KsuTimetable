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
                log.info("{} started", Thread.currentThread().getName());
                loadBuildings();
                log.info("{} finished", Thread.currentThread().getName());
            } finally {
                threadLatch.countDown();
            }
        });

        taskExecutor.execute(() -> {
            try {
                log.info("{} started", Thread.currentThread().getName());
                loadUsers();
                log.info("{} finished", Thread.currentThread().getName());
            } finally {
                threadLatch.countDown();
            }
        });


        try {
            threadLatch.await();
            loadFaculties();
            log.info("Finished loading data");
            return new ResponseModel(200, Constants.DATA_HAS_BEEN_LOADED);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadBuildings() {
        requestService.getBuildings().forEach(building -> {
            buildingService.saveBuilding(building);
            loadCabinets(building);
        });
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
                   log.info("{} started", Thread.currentThread().getName());

                   var faculty = faculties.get(finalIndexThread);
                   facultyService.saveFaculty(faculty);
                   loadDirections(faculty);
               } finally {
                   threadLatch.countDown();
                   log.info("{} finished", Thread.currentThread().getName());
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
        requestService.getGroupByDirection(direction.getId())
                .forEach(group -> {
                    group.setDirection(direction);
                    groupService.saveGroup(group);
                    loadTimetables(group);
                });
    }

    private void loadTimetables(Group group) {

        requestService.getTimetableByGroup(group.getId())
                .forEach(timetable -> {
                    timetable.setGroup(group);
                    timetableService.saveLesson(timetable);
                });
    }
}
