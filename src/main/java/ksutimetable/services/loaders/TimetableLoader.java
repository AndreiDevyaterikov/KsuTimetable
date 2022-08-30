package ksutimetable.services.loaders;

import ksutimetable.entities.Group;
import ksutimetable.repositories.GroupRepository;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Slf4j
@Service
@AllArgsConstructor
public class TimetableLoader implements LoaderService {

    private final RequestService requestService;
    private final MapperService mapperService;
    private final GroupRepository groupRepository;

    private final TimetableRepository timetableRepository;

    @Override
    public void loadData() {

        MultiValueMap<String, String> requestParams = new LinkedMultiValueMap<>();
        requestParams.add("action", "gettimetable");
        requestParams.add("mode", "student");

        groupRepository.findAll()
                .forEach(group -> {
                    requestParams.add("id", group.getId());

                    var response = requestService.postRequest(requestParams);
                    var timetableResponseModels = mapperService.mapResponseToList(TimetableResponseModel.class, response);
                    timetableResponseModels.forEach(timetableResponseModel -> timetableResponseModel.setGroup(group));
                    var jsonStringTimetable = mapperService.mapListToJsonString(timetableResponseModels);
                    timetableRepository.addTimetable(jsonStringTimetable);
                });


    }


    @Getter
    @Setter
    @AllArgsConstructor
    @RequiredArgsConstructor
    @ToString
    public static class TimetableResponseModel {
        private Integer x;
        private Integer y;
        private Integer n;
        private String subject1;
        private String subject2;
        private String lessontype;
        private String subject3;
        private String subgroup;
        private String starttime;
        private String endtime;
        private Group group;
    }
}
