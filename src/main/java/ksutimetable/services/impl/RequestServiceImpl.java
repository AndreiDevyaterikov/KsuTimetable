package ksutimetable.services.impl;

import ksutimetable.entities.*;
import ksutimetable.models.TimetableResponseModel;
import ksutimetable.services.RequestService;
import ksutimetable.utils.MapperService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;


@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Value("${timetable.uri}")
    private String uri;

    private final WebClient client;

    @Override
    public List<Building> getBuildingsRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(Building.class, response);
    }

    @Override
    public List<Faculty> getFacultiesRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getfaculties");
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(Faculty.class, response);
    }

    @Override
    public List<User> getUsersRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getteachers");
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(User.class, response);
    }

    @Override
    public List<Cabinet> getCabinetsByBuildingRequest(String buildingId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getauds");
        requestParams.add("id", buildingId);
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(Cabinet.class, response);
    }

    @Override
    public List<Direction> getDirectionsByFacultyRequest(String facultyId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbranches");
        requestParams.add("id", facultyId);
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(Direction.class, response);
    }

    @Override
    public List<Group> getGroupByDirectionRequest(String directionId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getgroups");
        requestParams.add("id", directionId);
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(Group.class, response);
    }

    @Override
    public List<TimetableResponseModel> getTimetableByGroupRequest(String groupId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "gettimetable");
        requestParams.add("mode", "student");
        requestParams.add("id", groupId);
        var response = executePostRequest(requestParams);
        return MapperService.mapResponseToList(TimetableResponseModel.class, response);
    }

    /**
     * Метод выполения запроса
     *
     * @param requestParams Переданные параметры для запроса
     * @return {@link String} Ответ запроса
     */
    private String executePostRequest(MultiValueMap<String, String> requestParams) {
        return client
                .post()
                .uri(uri)
                .bodyValue(requestParams)
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
