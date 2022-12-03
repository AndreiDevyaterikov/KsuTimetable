package ksutimetable.services;

import ksutimetable.entities.Faculty;

import java.util.List;

public interface FacultyService {
    void saveFaculties(List<Faculty> faculties);
    List<Faculty> getFaculties();
}
