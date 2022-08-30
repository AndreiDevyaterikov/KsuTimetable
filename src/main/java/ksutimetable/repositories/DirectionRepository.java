package ksutimetable.repositories;


import ksutimetable.entities.Direction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface DirectionRepository extends JpaRepository<Direction, String> {
    List<Direction> findAllByFacultyId(String facultyId);
}
