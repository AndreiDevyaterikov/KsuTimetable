package ksutimetable.entities;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "logbook")
public class Logbook {

    @Id
    @Column(name = "log_id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Sequence")
    @SequenceGenerator(name = "Sequence", sequenceName = "logs_sequence", allocationSize = 1)
    private Integer id;


    @OneToOne
    @JoinColumn(name = "teacher_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "cabinet_id")
    private Cabinet cabinet;

    @Column(name = "status")
    private String status;

    @Column(name = "time_changed")
    private Timestamp timeChanged;

    @Column(name = "week_day")
    private Integer weekDay;
}
