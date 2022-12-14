package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Faculty;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.FacultyRepository;
import ksutimetable.services.FacultyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;

    @Override
    public void saveFaculties(List<Faculty> faculties) {
        facultyRepository.saveAll(faculties);
    }

    @Override
    public List<Faculty> getFaculties() {
        return facultyRepository.findAll();
    }

    @Override
    public Faculty getFacultyById(String facultyId) {
        var facultyOpt = facultyRepository.findById(facultyId);
        if (facultyOpt.isPresent()) {
            return facultyOpt.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_FACULTY_WITH_ID, facultyId);
            throw new KsuTimetableException(message, 404);
        }
    }
}
