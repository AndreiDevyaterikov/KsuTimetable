package ksutimetable.services;

import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.repositories.FacultyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KsuService {
    private final FacultyRepository facultyRepository;
    private final DirectionRepository directionRepository;

    public List<Faculty> getAllFaculties() {
        return facultyRepository.findAll();
    }
}
