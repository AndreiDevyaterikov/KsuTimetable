package ksutimetable.entities;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name = "user_id")
    private String id;

    @Column(name = "user_name")
    private String title;
}
