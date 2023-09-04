package ksutimetable.services;

import ksutimetable.entities.User;

import java.util.List;

public interface UserService {

    /**
     * Метод сохранения пользователей
     *
     * @param users Список пользователей для сохранения
     */
    void saveUsers(List<User> users);

    void saveUser(User user);

    /**
     * Метод поиска пользователя по id
     *
     * @param userId Id пользователя для поиска
     * @return {@link User} Найденный пользователь
     */
    User getUserById(String userId);

    List<User> getUsers();
}
