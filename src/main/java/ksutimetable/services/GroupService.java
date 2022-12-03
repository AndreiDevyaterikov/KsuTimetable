package ksutimetable.services;

import ksutimetable.entities.Group;

import java.util.List;

public interface GroupService {
    void saveGroups(List<Group> groups);
    List<Group> getGroups();
}
