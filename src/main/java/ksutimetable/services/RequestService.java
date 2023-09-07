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

    List<Group> getGroupByDirection(String directionId);

    List<TimetableResponseModel> getTimetableByGroup(String groupId);
}
