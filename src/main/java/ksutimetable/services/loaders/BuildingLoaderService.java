package ksutimetable.services.loaders;

import ksutimetable.models.Building;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@AllArgsConstructor
public class BuildingLoaderService implements LoaderService {

    RequestService requestService;
    MapperService mapperService;

    @Override
    public void loadDataToDatabase() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbuildings");
        var response = requestService.doPost(requestParams);
        var buildings = mapperService.mapResponseToList(Building.class, response);
        System.out.println(buildings);
    }
}
