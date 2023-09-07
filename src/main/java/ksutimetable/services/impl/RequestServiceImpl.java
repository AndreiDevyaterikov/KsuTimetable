package ksutimetable.services.impl;

import ksutimetable.entities.*;
import ksutimetable.models.TimetableResponseModel;
import ksutimetable.services.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Value("${timetable.resource}")
    private String resource;

    private final RestTemplate restTemplate;

    @Override
    public List<Building> getBuildings() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        var response = postRequest(requestParams, Building[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<Faculty> getFaculties() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getfaculties");
        var response = postRequest(requestParams, Faculty[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<User> getUsers() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getteachers");
        var response = postRequest(requestParams, User[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<Cabinet> getCabinetsByBuilding(String buildingId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getauds");
        requestParams.add("id", buildingId);
        var response = postRequest(requestParams, Cabinet[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<Direction> getDirectionsByFaculty(String facultyId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbranches");
        requestParams.add("id", facultyId);
        var response = postRequest(requestParams, Direction[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<Group> getGroupByDirection(String directionId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getgroups");
        requestParams.add("id", directionId);
        var response = postRequest(requestParams, Group[].class);
        return Arrays.stream(response).toList();
    }

    @Override
    public List<TimetableResponseModel> getTimetableByGroup(String groupId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "gettimetable");
        requestParams.add("mode", "student");
        requestParams.add("id", groupId);
        var response = postRequest(requestParams, TimetableResponseModel[].class);
        return Arrays.stream(response).toList();
    }

    private <T> T postRequest(MultiValueMap<String, String> requestParams, Class<T> typeResponse) {

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestParams, headers);

        return restTemplate.postForObject(resource, requestEntity, typeResponse);
    }
}
