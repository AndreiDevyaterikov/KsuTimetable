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

    /**
     * Поиск группы по имени
     *
     * @param groupName Имя группы для поиска
     * @return {@link Group} Найденная группа
     */
    Group getByGroupName(String groupName);

    /**
     * Получение всех групп
     *
     * @return {@link Group} Список всех групп
     */
    List<Group> getGroups();
}
