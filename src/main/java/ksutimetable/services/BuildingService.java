package ksutimetable.services;

import ksutimetable.entities.Building;

import java.util.List;

public interface BuildingService {
    void saveBuildings(String buildings);
    List<Building> getBuildings();
}
