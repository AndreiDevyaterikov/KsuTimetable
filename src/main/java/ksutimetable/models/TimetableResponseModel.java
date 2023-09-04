package ksutimetable.models;

import ksutimetable.entities.Group;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
@ToString
public class TimetableResponseModel {
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