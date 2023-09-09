package ksutimetable.services;

import ksutimetable.entities.User;

import java.util.List;

public interface UserService {

    void saveUser(User user);

    User getUserById(String userId);

    List<User> getUsers();
}
