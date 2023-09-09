package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Cabinet;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.BuildingService;
import ksutimetable.services.CabinetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CabinetServiceImpl implements CabinetService {

    private final CabinetRepository cabinetRepository;
    private final BuildingService buildingService;

    @Override
    public void saveCabinet(Cabinet cabinet) {
        cabinetRepository.save(cabinet);
    }

    @Override
    public Cabinet getCabinetById(String cabinetId) {
        return cabinetRepository
                .findById(cabinetId)
                .orElseThrow(() -> {
                    var message = String.format(Constants.NOT_FOUND_CABINET_WITH_ID, cabinetId);
                    log.info(message);
                    return new KsuTimetableException(message, 404);
                });
    }

    @Override
    public List<Cabinet> getCabinetsByBuildingId(String buildingId) {
        var building = buildingService.getBuildingById(buildingId);
        return cabinetRepository.findAllByBuildingId(building.getId());
    }
}
