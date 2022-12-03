package ksutimetable.services;

import ksutimetable.entities.Direction;

import java.util.List;

public interface DirectionService {
    void saveDirections(List<Direction> directions);
    List<Direction> getDirections();
}
