package ksutimetable.services;

import ksutimetable.entities.Building;

import java.util.List;

public interface BuildingService {

    void saveBuilding(Building building);

    List<Building> getBuildings();

    Building getBuildingById(String buildingId);
}
