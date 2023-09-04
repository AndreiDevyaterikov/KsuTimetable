package ksutimetable.services;

import ksutimetable.entities.Group;

import java.util.List;

public interface GroupService {

    /**
     * Сохранить список групп
     *
     * @param groups Список групп для сохранения
     */
    void saveGroups(List<Group> groups);

    void saveGroup(Group group);

    /**
     * Поиск группы по имени
     *
     * @param groupName Имя группы для поиска
     * @return {@link Group} Найденная группа
     */
    Group getGroupByName(String groupName);

    /**
     * Поиск группы по id
     *
     * @param groupId Id группы для поиска
     * @return {@link Group} Найденная группа
     */
    Group getGroupById(String groupId);

    /**
     * Получение всех групп
     *
     * @return {@link Group} Список всех групп
     */
    List<Group> getGroups();

    /**
     * Метод получения всех групп для направления подготовки
     *
     * @param directionId Id направления подготовки для поиска групп
     * @return {@link Group} Список всех групп для направления подготовки
     */
    List<Group> getGroupsByDirectionId(String directionId);
}
