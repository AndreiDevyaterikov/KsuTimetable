package ksutimetable.services.loaders;

import ksutimetable.entities.Building;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@AllArgsConstructor
public class BuildingLoader implements LoaderService {

    private final BuildingRepository buildingRepository;
    private final RequestService requestService;
    private final MapperService mapperService;

    @Override
    public void loadData() {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        var response = requestService.postRequest(requestParams);
        var buildings = mapperService.mapResponseToList(Building.class, response);
        var jsonStringBuildings = mapperService.mapListToJsonString(buildings);

        buildingRepository.saveBuildings(jsonStringBuildings);
    }
}
