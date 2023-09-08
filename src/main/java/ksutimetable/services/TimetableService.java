package ksutimetable.services;

import ksutimetable.entities.Timetable;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface TimetableService {

    List<Timetable> getTimetableForGroup(String groupId, Boolean lessonsForToday);

    List<Timetable> getTimetableForCabinet(String cabinetId, Boolean lessonsForToday);

    List<Timetable> getTimetableForTeacher(String teacherId, Boolean lessonsForToday);

    void saveLesson(TimetableResponseModel lesson);

    String getCabinetForActivity(String cabinetId, String userId);

    Boolean returnCabinetFromActivity(String cabinetId);
}
