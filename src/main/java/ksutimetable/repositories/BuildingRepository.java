package ksutimetable.repositories;

import ksutimetable.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface BuildingRepository extends JpaRepository<Building, String> {

    @Modifying
    @Transactional
    @Query(value = "call save_buildings(:buildings)", nativeQuery = true)
    void saveBuildings(String buildings);
}
