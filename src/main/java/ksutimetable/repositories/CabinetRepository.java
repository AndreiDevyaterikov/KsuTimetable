package ksutimetable.repositories;


import ksutimetable.entities.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, String> {

    Optional <Cabinet> findById(String id);
    Optional <Cabinet> findByTitle(String title);

    @Modifying
    @Transactional
    @Query(value = "CALL add_cabinet (:cabinets)", nativeQuery = true)
    void saveCabinets(String cabinets);

}
