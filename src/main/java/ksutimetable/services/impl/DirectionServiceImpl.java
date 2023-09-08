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
    public void saveDirection(Direction direction) {
        directionRepository.save(direction);
    }

    @Override
    public List<Direction> getDirectionsByFacultyId(String facultyId) {
        var faculty = facultyService.getFacultyById(facultyId);
        return directionRepository.findAllByFaculty(faculty);
    }

    @Override
    public Direction getDirectionById(String directionId) {
        return directionRepository
                .findById(directionId)
                .orElseThrow(() -> {
                    var message = String.format(Constants.NOT_FOUND_DIRECTION_WITH_ID, directionId);
                    log.info(message);
                    return new KsuTimetableException(message, 404);
                });
    }
}
