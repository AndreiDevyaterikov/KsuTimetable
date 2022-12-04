package ksutimetable.services.impl;

import ksutimetable.entities.Cabinet;
import ksutimetable.entities.Timetable;
import ksutimetable.models.TimetableResponseModel;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.services.GroupService;
import ksutimetable.services.TimetableService;
import ksutimetable.utils.MapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    private final TimetableRepository timetableRepository;
    private final GroupService groupService;

    public List<Timetable> getTodayTimetableForGroup(String groupName) {
        var group = groupService.getByGroupName(groupName);
        return timetableRepository.getTodayLessonsForGroup(group.getId());
    }

    @Override
    public void saveTimetables(List<TimetableResponseModel> timetables) {
        var timetablesJson = MapperService.mapListToJsonString(timetables);
        timetableRepository.addTimetable(timetablesJson);
    }

    @Override
    public List<Timetable> getTimetableByCabinetId(String cabinetId) {
        return timetableRepository.findAllByCabinetId(cabinetId);
    }

    @Override
    public Timetable getCurrentLessonByCabinetId(String cabinetId) {
        return timetableRepository.getCurrentLessonByCabinetId(cabinetId);
    }

    @Override
    public List<Timetable> getTodayLessonsByCabinet(Cabinet cabinet) {
        return timetableRepository.getTodayLessonsInCabinet(cabinet.getId());
    }
}
