package ksutimetable.services.impl;

import ksutimetable.entities.Faculty;
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
}
