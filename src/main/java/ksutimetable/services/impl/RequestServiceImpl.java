package ksutimetable.services.impl;

import ksutimetable.entities.*;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.models.TimetableResponseModel;
import ksutimetable.services.RequestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;


@Slf4j
@Service
@RequiredArgsConstructor
public class RequestServiceImpl implements RequestService {

    @Value("${timetable.uri}")
    private String uri;

    private final WebClient client;

    @Override
    public Flux<Building> getBuildingsRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        return executePostRequest(Building.class, requestParams);
    }

    @Override
    public Flux<Faculty> getFacultiesRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getfaculties");
        return executePostRequest(Faculty.class, requestParams);
    }

    @Override
    public Flux<User> getUsersRequest() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getteachers");
        return executePostRequest(User.class, requestParams);
    }

    @Override
    public Flux<Cabinet> getCabinetsByBuildingRequest(String buildingId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getauds");
        requestParams.add("id", buildingId);
        return executePostRequest(Cabinet.class, requestParams);
    }

    @Override
    public Flux<Direction> getDirectionsByFacultyRequest(String facultyId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbranches");
        requestParams.add("id", facultyId);
        return executePostRequest(Direction.class, requestParams);
    }

    @Override
    public Flux<Group> getGroupByDirectionRequest(String directionId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getgroups");
        requestParams.add("id", directionId);
        return executePostRequest(Group.class, requestParams);
    }

    @Override
    public Flux<TimetableResponseModel> getTimetableByGroupRequest(String groupId) {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "gettimetable");
        requestParams.add("mode", "student");
        requestParams.add("id", groupId);
        return executePostRequest(TimetableResponseModel.class, requestParams);
    }

    private <T> Flux<T> executePostRequest(Class<T> model, MultiValueMap<String, String> requestParams) {
        return client
                .post()
                .uri(uri)
                .bodyValue(requestParams)
                .retrieve()
                .bodyToFlux(model)
                .doOnError(throwable -> {
                    log.error(throwable.getMessage());
                    var message = String.format(
                            "Error download on %s with params %s",
                            model.getSimpleName(),
                            requestParams
                    );
                    throw new KsuTimetableException(message, 500);
                });
    }
}
