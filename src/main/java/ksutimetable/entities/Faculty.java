package ksutimetable.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "faculties")
public class Faculty {

    @Id
    @Column(name = "faculty_id")
    private String id;

    @Column(name = "faculty_name")
    private String title;

}
