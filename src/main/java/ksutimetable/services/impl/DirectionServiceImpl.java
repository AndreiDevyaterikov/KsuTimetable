package ksutimetable.services.impl;

import ksutimetable.entities.Direction;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.services.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DirectionServiceImpl implements DirectionService {

    private final DirectionRepository directionRepository;

    @Override
    public void saveDirections(List<Direction> directions) {
        directionRepository.saveAll(directions);
    }

    @Override
    public List<Direction> getDirections() {
        return directionRepository.findAll();
    }
}
