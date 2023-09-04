package ksutimetable.services;

import ksutimetable.entities.Building;

import java.util.List;

public interface BuildingService {

    /**
     * Метод сохранения корпусов\строений
     *
     * @param buildings Список корпусов\строений для сохранения
     */
    void saveBuildings(List<Building> buildings);
    void saveBuilding(Building building);

    /**
     * Метод получения всех корпусов\строений
     *
     * @return {@link Building} Список всех корпусов\строений
     */
    List<Building> getBuildings();

    Building getBuildingById(String buildingId);
}
