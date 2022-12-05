package ksutimetable.repositories;


import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DirectionRepository extends JpaRepository<Direction, String> {
    List<Direction> findAllByFaculty(Faculty faculty);
}
