package ksutimetable.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "cabinets")
public class Cabinet {

    @Id
    @Column(name = "cabinet_id")
    private String id;

    @Column(name = "cabinet_name")
    private String title;

    @OneToOne
    @JoinColumn(name = "building_id")
    private Building building;
}

