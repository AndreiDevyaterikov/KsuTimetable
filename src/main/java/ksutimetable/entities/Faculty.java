package ksutimetable.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "faculties")
public class Faculty {

    @Id
    @Column(name = "faculty_id")
    private String id;

    @Column(name = "faculty_name")
    private String title;

}
