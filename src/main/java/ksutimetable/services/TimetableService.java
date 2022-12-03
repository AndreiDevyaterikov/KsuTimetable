package ksutimetable.services;

import ksutimetable.entities.Timetable;

import java.util.List;

public interface TimetableService {
    void saveTimetables(String timetables);
    List<Timetable> getTimetableByCabinetId(String cabinetId);
}
