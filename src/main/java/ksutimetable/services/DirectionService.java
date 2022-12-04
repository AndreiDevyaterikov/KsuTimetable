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

    /**
     * Метод получения всех направлений подготовки
     *
     * @return {@link Direction} Список всех направлений подготовки
     */
    List<Direction> getDirections();
}
