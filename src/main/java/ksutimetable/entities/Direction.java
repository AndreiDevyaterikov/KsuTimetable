package ksutimetable.entities;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "directions")
public class Direction {

    @Id
    @Column(name = "direction_id")
    private String id;

    @Column(name = "direction_name")
    private String title;


    @OneToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
}
