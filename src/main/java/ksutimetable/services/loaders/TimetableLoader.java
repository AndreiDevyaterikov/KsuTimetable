package ksutimetable.services.loaders;

import ksutimetable.entities.Group;
import ksutimetable.models.RequestModel;
import ksutimetable.repositories.GroupRepository;
import ksutimetable.services.MapperService;
import ksutimetable.services.RequestService;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

@Slf4j
@Service
@AllArgsConstructor
public class TimetableLoader implements LoaderService {
    private final RequestService requestService;
    private final MapperService mapperService;

    private final GroupRepository groupRepository;

    @Override
    public void loadData() {



    }


    @AllArgsConstructor
    @Getter
    @Setter
    public static class TimetableResponseModel implements Serializable {
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
