package ksutimetable.services.impl;

import ksutimetable.entities.Building;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.services.BuildingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public void saveBuildings(String buildings) {
        buildingRepository.saveBuildings(buildings);
    }

    @Override
    public List<Building> getBuildings() {
        return buildingRepository.findAll();
    }
}
