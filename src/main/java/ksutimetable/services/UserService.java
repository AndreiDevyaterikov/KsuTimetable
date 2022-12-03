package ksutimetable.services;

import ksutimetable.entities.User;

import java.util.List;

public interface UserService {
    void saveUsers(List<User> users);
    List<User> getUsers();
}
