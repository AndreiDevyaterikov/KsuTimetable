package ksutimetable.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @Column(name = "group_id")
    private String id;

    @Column(name = "group_name")
    private String title;

    @OneToOne
    @JoinColumn(name = "direction_id")
    private Direction direction;

}
