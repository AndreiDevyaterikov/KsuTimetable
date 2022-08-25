package ksutimetable.services.loaders;

import ksutimetable.entities.Building;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;


@Service
@AllArgsConstructor
public class BuildingLoader implements LoaderService {

    private final BuildingRepository buildingRepository;
    private final RequestService requestService;
    private final MapperService mapperService;

    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Override
    public void loadData() {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        var response = requestService.doPost(requestParams);
        var buildings = mapperService.mapResponseToList(Building.class, response);
        var jsonStringBuilding = mapperService.mapListToJsonString(buildings);
        log.info("Loading buildings to database...");
        buildingRepository.saveBuldings(jsonStringBuilding);
        log.info("Buildings has been loaded");
    }
}
