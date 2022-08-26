package ksutimetable.services.loaders;

import ksutimetable.entities.Group;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.repositories.GroupRepository;
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
public class GroupLoader implements LoaderService {
    private final GroupRepository groupRepository;
    private final DirectionRepository directionRepository;
    private final RequestService requestService;
    private final MapperService mapperService;



    @Override
    public void loadData() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getgroups");

        log.info("Loading directions to database...");
        directionRepository.findAll()
                .forEach(direction -> {
                    requestParams.add("id", direction.getId());

                    var response = requestService.postRequest(requestParams);
                    var groups = mapperService.mapResponseToList(Group.class, response);
                    groups.forEach(group -> group.setDirection(direction));


                    groupRepository.saveAll(groups);
                });
        log.info("Directions has been loaded");

    }
}
