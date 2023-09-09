package ksutimetable.services;

import ksutimetable.entities.*;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface RequestService {

    List<Building> getBuildings();

    List<Faculty> getFaculties();

    List<User> getUsers();

    List<Cabinet> getCabinetsByBuilding(String buildingId);

    List<Direction> getDirectionsByFaculty(String facultyId);

    List<Group> getGroupsByDirection(String directionId);

    List<TimetableResponseModel> getLessonsByGroup(String groupId);
}
