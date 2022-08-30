package ksutimetable.services;

import ksutimetable.entities.Timetable;
import ksutimetable.repositories.TimetableRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class TimetableService {
    private final TimetableRepository timetableRepository;

    public List<Timetable> getTodayTimetableForGroup(String groupId){
        return timetableRepository.findAllByGroupIdAndLessonDay(groupId, LocalDate.now().getDayOfWeek().getValue());
    }
}
