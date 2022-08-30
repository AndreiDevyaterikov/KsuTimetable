package ksutimetable.services.loaders;

import ksutimetable.entities.Direction;
import ksutimetable.repositories.DirectionRepository;
import ksutimetable.repositories.FacultyRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Service
@AllArgsConstructor
public class DirectionLoader implements LoaderService {

    private final DirectionRepository directionRepository;
    private final FacultyRepository facultyRepository;
    private final RequestService requestService;
    private final MapperService mapperService;

    @Override
    public void loadData() {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "getbranches");
        facultyRepository.findAll()
                .forEach(faculty -> {
                    requestParams.add("id", faculty.getId());

                    var response = requestService.postRequest(requestParams);
                    var directions = mapperService.mapResponseToList(Direction.class, response);
                    directions.forEach(direction -> direction.setFaculty(faculty));
                    directionRepository.saveAll(directions);
                });
    }
}
