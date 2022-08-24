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

    @Query(value = "select * from get_current_lesson_in_cabinet(:cabinetId)", nativeQuery = true)
    Timetable getCurrentLessonInCabinet(String cabinetId);

    List<Timetable> findAllByLessonDayAndCabinetIdOrderByLessonNumberAsc(Integer lessonDay, String cabinetId);

    Timetable findByCabinetIdAndLessonDayAndLessonNumber(String cabinetId, Integer lessonDay, Integer lessonNumber);
}