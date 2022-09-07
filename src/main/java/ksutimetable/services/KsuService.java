package ksutimetable.services;

import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.entities.Group;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.repositories.FacultyRepository;
import ksutimetable.repositories.GroupRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class KsuService {
    private final FacultyRepository facultyRepository;
    private final DirectionRepository directionRepository;
    private final GroupRepository groupRepository;

    public List<Faculty> getAllFaculties() {


        var faculties = facultyRepository.findAll();
        if (faculties.isEmpty()) {
            throw new KsuTimetableException("Институты отсутствуют в БД", 404);
        } else {
            return faculties;
        }
    }

    public List<Direction> getDirectionsByFacultyId(String facultyId) {
        var directions = directionRepository.findAllByFacultyId(facultyId);
        if (directions.isEmpty()) {
            throw new KsuTimetableException("Не найдено направлений подготовки для выбранного института", 404);
        } else {
            return directions;
        }
    }

    public List<Group> getGroupsByDirection(String directionId) {
        var groups = groupRepository.findAllByDirectionId(directionId);
        if (groups.isEmpty()) {
            throw new KsuTimetableException("Не найдено групп для выбранного направления подготовки", 404);
        } else {
            return groups;
        }
    }
}
