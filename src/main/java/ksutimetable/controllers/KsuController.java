package ksutimetable.controllers;

import ksutimetable.controllers.api.KsuControllerApi;
import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.entities.Group;
import ksutimetable.services.KsuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ksu")
@AllArgsConstructor
public class KsuController implements KsuControllerApi {
    private final KsuService ksuService;

    @Override
    public List<Direction> getDirectionsByFacultyId(String facultyId) {
        return ksuService.getDirectionsByFacultyId(facultyId);
    }

    @Override
    public List<Faculty> getAllFaculties() {
        return ksuService.getAllFaculties();
    }

    @Override
    public List<Group> getGroupsByDirectionId(String directionId) {
        return ksuService.getGroupsByDirection(directionId);
    }
}
