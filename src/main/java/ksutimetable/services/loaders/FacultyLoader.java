package ksutimetable.services.loaders;

import ksutimetable.entities.Faculty;
import ksutimetable.repositories.FacultyRepository;
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
public class FacultyLoader implements LoaderService {
    private final RequestService requestService;
    private final MapperService mapperService;
    private final FacultyRepository facultyRepository;


    @Override
    public void loadData() {
        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getfaculties");

        var response = requestService.postRequest(requestParams);
        var faculties = mapperService.mapResponseToList(Faculty.class, response);
        facultyRepository.saveAll(faculties);
    }
}
