package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Building;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.services.BuildingService;
import ksutimetable.utils.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;

    @Override
    public void saveBuildings(List<Building> buildings) {
        var buildingsJson = MapperService.mapListToJsonString(buildings);
        buildingRepository.saveBuildings(buildingsJson);
    }

    @Override
    public void saveBuilding(Building building) {
        buildingRepository.save(building);
    }

    @Override
    public List<Building> getBuildings() {
        return buildingRepository.findAll();
    }

    @Override
    public Building getBuildingById(String buildingId) {
        return buildingRepository
                .findById(buildingId)
                .orElseThrow(() -> {
                    var message = String.format(Constants.NOT_FOUND_BUILDING_WITH_ID, buildingId);
                    log.info(message);
                    return new KsuTimetableException(message, 404);
                });

    }
}
