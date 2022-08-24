package ksutimetable.repositories;

import ksutimetable.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByTitle(String title);
    Optional<User> findById(String id);
}
