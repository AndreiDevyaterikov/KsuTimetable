package ksutimetable.services.impl;

import ksutimetable.entities.Timetable;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.GroupRepository;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.services.TimetableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {
    private final TimetableRepository timetableRepository;
    private final GroupRepository groupRepository;

    public List<Timetable> getTodayTimetableForGroup(String groupName) {

        var group = groupRepository.findGroupByTitle(groupName);
        if (group.isPresent()) {
            return timetableRepository.findAllByGroupIdAndLessonDayOrderByLessonNumberAsc(group.get().getId(), LocalDate.now().getDayOfWeek().getValue());
        } else {
            throw new KsuTimetableException("Не существует такой группы", 404);
        }

    }

    @Override
    public void saveTimetables(String timetables) {
        timetableRepository.addTimetable(timetables);
    }

    @Override
    public List<Timetable> getTimetableByCabinetId(String cabinetId) {
        return timetableRepository.findAllByCabinetId(cabinetId);
    }
}
