package ksutimetable.services.loaders;

import ksutimetable.entities.Cabinet;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@AllArgsConstructor
public class CabinetLoader implements LoaderService {

    private final CabinetRepository cabinetRepository;
    private final BuildingRepository buildingRepository;
    private final RequestService requestService;
    private final MapperService mapperService;

    @Override
    public void loadData() {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getauds");

        buildingRepository.findAll()
                .forEach(building -> {
                    requestParams.add("id", building.getId());

                    var response = requestService.postRequest(requestParams);
                    var cabinets = mapperService.mapResponseToList(Cabinet.class, response);
                    cabinets.forEach(cabinet -> cabinet.setBuilding(building));
                    var jsonStringCabinets = mapperService.mapListToJsonString(cabinets);
                    cabinetRepository.saveCabinets(jsonStringCabinets);
                });
    }
}
