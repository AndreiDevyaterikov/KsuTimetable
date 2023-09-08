package ksutimetable.services;

import ksutimetable.entities.Cabinet;

import java.util.List;

public interface CabinetService {

    void saveCabinet(Cabinet cabinet);

    Cabinet getCabinetById(String cabinetId);

    List<Cabinet> getCabinetsByBuildingId(String buildingId);
}
