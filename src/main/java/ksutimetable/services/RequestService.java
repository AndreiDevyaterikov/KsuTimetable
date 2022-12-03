package ksutimetable.services;

import ksutimetable.entities.*;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface RequestService {
    List<Building> getBuildingsRequest();
    List<Faculty> getFacultiesRequest();
    List<User> getUsersRequest();
    List<Cabinet> getCabinetsByBuildingRequest(String buildingId);
    List<Direction> getDirectionsByFacultyRequest(String facultyId);
    List<Group> getGroupByDirectionRequest(String directionId);
    List<TimetableResponseModel> getTimetableByGroupRequest(String groupId);
}
