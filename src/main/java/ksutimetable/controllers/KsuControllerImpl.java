package ksutimetable.controllers;

import ksutimetable.controllers.api.KsuController;
import ksutimetable.entities.*;
import ksutimetable.services.*;
import ksutimetable.services.impl.UserServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ksu")
@AllArgsConstructor
public class KsuControllerImpl implements KsuController {

    private final GroupService groupService;
    private final UserServiceImpl userService;
    private final FacultyService facultyService;
    private final CabinetService cabinetService;
    private final BuildingService buildingService;
    private final DirectionService directionService;

    @Override
    public List<Direction> getDirectionsByFacultyId(String facultyId) {
        return directionService.getDirectionsByFacultyId(facultyId);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return facultyService.getFaculties();
    }

    @Override
    public List<Group> getGroupsByDirectionId(String directionId) {
        return groupService.getGroupsByDirectionId(directionId);
    }

    @Override
    public List<Building> getBuildings() {
        return buildingService.getBuildings();
    }

    @Override
    public List<Cabinet> getCabinetsByBuildingId(String buildingId) {
        return cabinetService.getCabinetsByBuildingId(buildingId);
    }

    @Override
    public List<User> getUsers() {
        return userService.getUsers();
    }
}
