package ksutimetable.services;

import ksutimetable.entities.Group;

import java.util.List;

public interface GroupService {

    void saveGroup(Group group);

    Group getGroupById(String groupId);

    List<Group> getGroupsByDirectionId(String directionId);
}
