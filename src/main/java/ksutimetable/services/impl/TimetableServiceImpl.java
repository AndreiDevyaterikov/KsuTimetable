package ksutimetable.services.impl;

import ksutimetable.entities.Cabinet;
import ksutimetable.entities.Timetable;
import ksutimetable.models.TimetableResponseModel;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.services.CabinetService;
import ksutimetable.services.GroupService;
import ksutimetable.services.TimetableService;
import ksutimetable.services.UserService;
import ksutimetable.utils.MapperService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TimetableServiceImpl implements TimetableService {

    private final UserService userService;
    private final GroupService groupService;
    private final CabinetService cabinetService;
    private final TimetableRepository timetableRepository;

    @Override
    public List<Timetable> getTimetableForGroup(String groupId, Boolean lessonsForToday) {
        var group = groupService.getGroupById(groupId);
        return timetableRepository.getLessonsForGroup(group.getId(), lessonsForToday);
    }

    @Override
    public List<Timetable> getTimetableForCabinet(String cabinetId, Boolean lessonsForToday) {
        var cabinet = cabinetService.getCabinetById(cabinetId);
        return timetableRepository.getLessonsForCabinet(cabinet.getId(), lessonsForToday);
    }

    @Override
    public List<Timetable> getTimetableForTeacher(String teacherId, Boolean lessonsForToday) {
        var teacher = userService.getUserById(teacherId);
        return timetableRepository.getLessonsForTeacher(teacher.getId(), lessonsForToday);
    }

    @Override
    public void saveTimetables(List<TimetableResponseModel> timetables) {
        var timetablesJson = MapperService.mapListToJsonString(timetables);
        timetableRepository.addTimetable(timetablesJson);
    }

    @Override
    public void saveLesson(TimetableResponseModel lesson) {
        var lessonJson = MapperService.mapModelToJsonString(lesson);
        timetableRepository.saveLesson(lessonJson);
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

    @Override
    public String getCabinetForActivity(String cabinetId, String userId) {
        return timetableRepository.getCabinetForActivity(cabinetId, userId);
    }

    @Override
    public Boolean returnCabinetFromActivity(String cabinetId) {
        return timetableRepository.returnCabinetFromActivity(cabinetId);
    }
}
