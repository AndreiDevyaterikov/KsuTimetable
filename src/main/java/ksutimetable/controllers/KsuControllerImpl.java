package ksutimetable.controllers;

import ksutimetable.controllers.api.KsuController;
import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.entities.Group;
import ksutimetable.services.DirectionService;
import ksutimetable.services.FacultyService;
import ksutimetable.services.GroupService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ksu")
@AllArgsConstructor
public class KsuControllerImpl implements KsuController {
    private final GroupService groupService;
    private final FacultyService facultyService;
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
}
