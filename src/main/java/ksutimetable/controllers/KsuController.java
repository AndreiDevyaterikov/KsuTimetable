package ksutimetable.controllers;

import ksutimetable.entities.Faculty;
import ksutimetable.services.KsuService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ksu")
@AllArgsConstructor
public class KsuController {
    private final KsuService ksuService;

    @GetMapping("/faculties")
    public List<Faculty> getAllFaculties(){
        return ksuService.getAllFaculties();
    }
}
