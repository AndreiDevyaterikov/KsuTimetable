package ksutimetable.services;

import ksutimetable.entities.Cabinet;

import java.util.List;

public interface CabinetService {
    void saveCabinets(String cabinets);
    List<Cabinet> getCabinets();
    Cabinet getCabinetById(String cabinetId);
}
