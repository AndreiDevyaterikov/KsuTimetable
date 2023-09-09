package ksutimetable.services;

import ksutimetable.entities.Faculty;

import java.util.List;

public interface FacultyService {

    void saveFaculty(Faculty faculty);

    List<Faculty> getFaculties();

    Faculty getFacultyById(String facultyId);
}
