package ksutimetable.services;

import ksutimetable.entities.Direction;

import java.util.List;

public interface DirectionService {

    void saveDirection(Direction direction);

    List<Direction> getDirectionsByFacultyId(String facultyId);

    Direction getDirectionById(String directionId);
}
