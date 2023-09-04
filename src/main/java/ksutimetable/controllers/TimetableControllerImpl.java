package ksutimetable.controllers;

import ksutimetable.controllers.api.TimetableController;
import ksutimetable.entities.Timetable;
import ksutimetable.services.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@AllArgsConstructor
public class TimetableControllerImpl implements TimetableController {

    private final TimetableService timetableService;

    @Override
    public List<Timetable> getTimetableForGroup(String groupId, Boolean lessonForToday) {
        return timetableService.getTimetableForGroup(groupId, lessonForToday);
    }

    @Override
    public List<Timetable> getTimetableForCabinet(String cabinetId, Boolean lessonForToday) {
        return timetableService.getTimetableForCabinet(cabinetId, lessonForToday);
    }

    @Override
    public List<Timetable> getTimetableForTeacher(String teacherId, Boolean lessonForToday) {
        return timetableService.getTimetableForTeacher(teacherId, lessonForToday);
    }
}
