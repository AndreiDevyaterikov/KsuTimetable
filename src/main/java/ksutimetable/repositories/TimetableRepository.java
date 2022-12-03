package ksutimetable.repositories;

import ksutimetable.entities.Timetable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface TimetableRepository extends JpaRepository<Timetable, Integer> {

    @Modifying
    @Transactional
    @Query(value = "CALL add_timetable(:timetable)", nativeQuery = true)
    void addTimetable(String timetable);

    @Transactional
    @Query(value = "select * from get_cabinet_for_activity(:cabinetId, :userId)", nativeQuery = true)
    String getCabinetForActivity(String cabinetId, String userId);

    @Transactional
    @Query(value = "select * from return_cabinet_from_activity(:cabinetId)", nativeQuery = true)
    String returnCabinetFromActivity(String cabinetId);

    List<Timetable> findAllByGroupIdAndLessonDayOrderByLessonNumberAsc(String groupName, Integer lessonDay);
    List<Timetable> findAllByCabinetId(String cabinetId);
}