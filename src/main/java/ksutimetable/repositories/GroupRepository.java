package ksutimetable.repositories;


import ksutimetable.entities.Direction;
import ksutimetable.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    List<Group> findAllByDirectionId(String directionId);

    List<Group> findAllByDirection(Direction direction);

    Optional<Group> findGroupByTitle(String groupName);
}
