package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Direction;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.services.DirectionService;
import ksutimetable.services.FacultyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;
    private final FacultyService facultyService;

    @Override
    public void saveDirections(List<Direction> directions) {
        directionRepository.saveAll(directions);
    }

    @Override
    public List<Direction> getDirections() {
        return directionRepository.findAll();
    }

    @Override
    public List<Direction> getDirectionsByFacultyId(String facultyId) {
        var faculty = facultyService.getFacultyById(facultyId);
        return directionRepository.findAllByFaculty(faculty);
    }

    @Override
    public Direction getDirectionById(String directionId) {
        var directionOpt = directionRepository.findById(directionId);
        if (directionOpt.isPresent()) {
            return directionOpt.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_DIRECTION_WITH_ID, directionId);
            log.info(message);
            throw new KsuTimetableException(message, 404);
        }
    }
}
