package ksutimetable.services;

import ksutimetable.entities.Direction;

import java.util.List;

public interface DirectionService {

    /**
     * Метод сохранения направлений подготовки
     *
     * @param directions Список направлений подготовки для сохранения
     */
    void saveDirections(List<Direction> directions);
    void saveDirection(Direction direction);

    /**
     * Метод получения всех направлений подготовки
     *
     * @return {@link Direction} Список всех направлений подготовки
     */
    List<Direction> getDirections();

    /**
     * Метод получения всех направлений подготовки для выбранного факультета\института
     *
     * @param facultyId Id факультета для получения направлений подготовки
     * @return {@link Direction} Список всех направлений подготовки для факультета
     */
    List<Direction> getDirectionsByFacultyId(String facultyId);

    /**
     * Метод получения направления подготовки по id
     *
     * @param directionId - Id для поиска направления подготовки
     * @return {@link Direction} Найденное направление подготовки
     */
    Direction getDirectionById(String directionId);
}
