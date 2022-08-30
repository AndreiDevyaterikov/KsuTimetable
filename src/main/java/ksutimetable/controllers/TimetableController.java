package ksutimetable.controllers;

import ksutimetable.controllers.api.TimetableControllerApi;
import ksutimetable.entities.Timetable;
import ksutimetable.services.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@AllArgsConstructor
public class TimetableController implements TimetableControllerApi {

    private final TimetableService timetableService;

    @Override
    public List<Timetable> getTimetableForTodayByGroupId(String groupId) {
        return timetableService.getTodayTimetableForGroup(groupId);
    }
}
