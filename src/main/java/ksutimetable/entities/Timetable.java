package ksutimetable.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "timetable")
public class Timetable {

    @Id
    @Column(name = "lesson_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence")
    @SequenceGenerator(name = "Sequence", sequenceName = "timetable_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "lesson_day")
    private Integer lessonDay;

    @Column(name = "lesson_name")
    private String lessonName;

    @OneToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @Column(name = "lesson_type")
    private String lessonType;

    @OneToOne
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(name = "lesson_time_start")
    private String lessonTimeStart;

    @Column(name = "lesson_time_end")
    private String lessonTimeEnd;

    @Column(name = "lesson_number")
    private Integer lessonNumber;

    @OneToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column(name = "type_week")
    private String typeWeek;

    @Column(name = "subgroup")
    private String subgroup;

}
