package ksutimetable.services.loaders;

import ksutimetable.entities.Cabinet;
import ksutimetable.repositories.BuildingRepository;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
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

                    var response = requestService.doPost(requestParams);
                    var cabinets = mapperService.mapResponseToList(Cabinet.class, response);
                    cabinets.forEach(cabinet -> cabinet.setBuilding(building));
                    var jsonStringCabinets = mapperService.mapListToJsonString(cabinets);

                    log.info("Loading cabinets to database...");
                    cabinetRepository.saveCabinets(jsonStringCabinets);
                    log.info("Cabinets has been loaded");
                });
    }
}
