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
    @Query(value = "call save_timetable(:timetable)", nativeQuery = true)
    void addTimetable(String timetable);

    @Transactional
    @Query(value = "select * from get_cabinet_for_activity(:cabinetId, :userId)", nativeQuery = true)
    Boolean getCabinetForActivity(String cabinetId, String userId);

    @Transactional
    @Query(value = "select * from return_cabinet_from_activity(:cabinetId)", nativeQuery = true)
    Boolean returnCabinetFromActivity(String cabinetId);

    @Transactional
    @Query(value = "select * from get_current_lesson_in_cabinet(:cabinetId)", nativeQuery = true)
    Timetable getCurrentLessonByCabinetId(String cabinetId);

    @Transactional
    @Query(value = "select * from get_today_lessons_in_cabinet(:cabinetId)", nativeQuery = true)
    List<Timetable> getTodayLessonsInCabinet(String cabinetId);

    @Transactional
    @Query(value = "select * from get_today_lessons_for_group(:groupId)", nativeQuery = true)
    List<Timetable> getTodayLessonsForGroup(String groupId);

    List<Timetable> findAllByCabinetId(String cabinetId);
}