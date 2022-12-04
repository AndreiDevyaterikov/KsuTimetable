package ksutimetable.services;

import ksutimetable.entities.*;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface RequestService {

    /**
     * Запрос на получение корпусов\строений
     *
     * @return {@link Building} Список полученных корпусов\строений
     */
    List<Building> getBuildingsRequest();

    /**
     * Запрос на получение всех факультетов\институтов
     *
     * @return {@link Faculty} Список полученных факультетов\институтов
     */
    List<Faculty> getFacultiesRequest();

    /**
     * Запрос на получение всех пользователей
     *
     * @return {@link User} Список полученных пользователей
     */
    List<User> getUsersRequest();

    /**
     * Запрос на получение всех кабинетов\аудиторий по корпусу
     *
     * @param buildingId Id строения\корпуса для получения кабинетов
     * @return {@link Cabinet} Список полученных кабинетов\аудиторий
     */
    List<Cabinet> getCabinetsByBuildingRequest(String buildingId);

    /**
     * Запрос на получение всех направлений подготовки для института\факультета
     *
     * @param facultyId Id факультета\института для получения направлений подготовки
     * @return {@link Direction} Список полученных направлений подготовки
     */
    List<Direction> getDirectionsByFacultyRequest(String facultyId);

    /**
     * Запрос на получение всех групп для направления подготовки
     *
     * @param directionId - Id направления подготовки для получения групп
     * @return {@link Group} Список полученных групп
     */
    List<Group> getGroupByDirectionRequest(String directionId);

    /**
     * Запрос на получение расписания для группы
     *
     * @param groupId Id группы для получения расписания
     * @return {@link TimetableResponseModel} Полученное расписание для группы
     */
    List<TimetableResponseModel> getTimetableByGroupRequest(String groupId);
}
