package ksutimetable.repositories;

import ksutimetable.entities.Cabinet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CabinetRepository extends JpaRepository<Cabinet, String> {

    @Modifying
    @Transactional
    @Query(value = "call save_cabinets(:cabinets)", nativeQuery = true)
    void saveCabinets(String cabinets);

    List<Cabinet> findAllByBuildingId(String buildingId);

}
