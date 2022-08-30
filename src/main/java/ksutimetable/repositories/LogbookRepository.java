package ksutimetable.repositories;


import ksutimetable.entities.Logbook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface LogbookRepository extends JpaRepository<Logbook, Integer> {

    @Query(value = "select * from get_current_log(:cabinetId)", nativeQuery = true)
    Logbook getActualLog(String cabinetId);

    @Modifying
    @Transactional
    @Query(value = "call write_log(:cabinet, :teacher, :status)", nativeQuery = true)
    void writeLog(String cabinet, String teacher, String status);

}
