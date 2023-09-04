package ksutimetable.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "buildings")
public class Building {
    @Id
    @Column(name = "building_id")
    private String id;

    @Column(name = "building_name")
    private String title;
}
