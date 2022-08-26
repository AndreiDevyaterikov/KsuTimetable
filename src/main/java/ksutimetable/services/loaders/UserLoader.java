package ksutimetable.services.loaders;

import ksutimetable.entities.User;
import ksutimetable.repositories.UserRepository;
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
public class UserLoader implements LoaderService{

    private final RequestService requestService;
    private final MapperService mapperService;
    private final UserRepository userRepository;

    @Override
    public void loadData() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getteachers");

        var response = requestService.postRequest(requestParams);
        var users = mapperService.mapResponseToList(User.class, response);

        log.info("Loading users to database...");
        userRepository.saveAll(users);
        log.info("Users has been loaded");
    }
}
