package ksutimetable.services;

import ksutimetable.entities.Cabinet;
import ksutimetable.entities.Timetable;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface TimetableService {

    /**
     * Метод запроса расписания для группы
     *
     * @param groupId         Id группы для поиска расписания
     * @param lessonsForToday {@link Boolean} True - расписание на текущий день, False - общее расписание
     * @return {@link Timetable} Расписание для группы
     */
    List<Timetable> getTimetableForGroup(String groupId, Boolean lessonsForToday);

    /**
     * Метод запроса расписания для кабинета
     *
     * @param cabinetId       Id кабинета для поиска расписания
     * @param lessonsForToday {@link Boolean} True - расписание на текущий день, False - общее расписание
     * @return {@link Timetable} Расписание для кабинета
     */
    List<Timetable> getTimetableForCabinet(String cabinetId, Boolean lessonsForToday);

    /**
     * Метод запроса расписания для преподавателя
     *
     * @param teacherId       Id преподавателя для поиска расписания
     * @param lessonsForToday {@link Boolean} True - расписание на текущий день, False - общее расписание
     * @return {@link Timetable} Расписание для преподавателя
     */
    List<Timetable> getTimetableForTeacher(String teacherId, Boolean lessonsForToday);

    /**
     * Метод сохранения списка расписаний
     *
     * @param timetables Список расписаний для сохранения
     */
    void saveTimetables(List<TimetableResponseModel> timetables);

    void saveLesson(TimetableResponseModel lesson);

    /**
     * Метод получения расписания для кабинета
     *
     * @param cabinetId Id кабинета для поиска расписания
     * @return {@link Timetable} Расписание для найденного кабинета
     */

    List<Timetable> getTimetableByCabinetId(String cabinetId);

    /**
     * Метод получения текущей пары в кабинете
     *
     * @param cabinetId Id кабинета для поиска
     * @return {@link Timetable} Текущая пара в кабинете
     */
    Timetable getCurrentLessonByCabinetId(String cabinetId);

    /**
     * Метод получения списка пар на текущий день для выбранной аудитории
     *
     * @param cabinet Аудитория для поиска пар
     * @return {@link Timetable} Список пар на текущий день
     */
    List<Timetable> getTodayLessonsByCabinet(Cabinet cabinet);

    /**
     * Метод проверки на доступность аудитории для бронирования
     *
     * @param cabinetId Id аудитории для бронирования
     * @param userId    Id Пользователя, бронирующего аудиторию
     * @return {@link Boolean} Результат проверки, true или false
     */
    String getCabinetForActivity(String cabinetId, String userId);

    /**
     * Метод проверки на доступность аудитории к возврату
     *
     * @param cabinetId Id аудитории для возврата после занятия
     * @return {@link Boolean} Результат проверки, true или false
     */
    Boolean returnCabinetFromActivity(String cabinetId);
}
