package ksutimetable.controllers;

import ksutimetable.controllers.api.TimetableControllerApi;
import ksutimetable.entities.Timetable;
import ksutimetable.services.impl.TimetableServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/timetable")
@AllArgsConstructor
public class TimetableController implements TimetableControllerApi {

    private final TimetableServiceImpl timetableServiceImpl;

    @Override
    public List<Timetable> getTimetableForTodayByGroupName(String groupName) {
        return timetableServiceImpl.getTodayTimetableForGroup(groupName);
    }
}
