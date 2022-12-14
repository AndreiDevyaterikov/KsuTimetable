package ksutimetable.services;

import ksutimetable.entities.Cabinet;
import ksutimetable.entities.Timetable;
import ksutimetable.models.TimetableResponseModel;

import java.util.List;

public interface TimetableService {

    /**
     * Метод сохранения списка расписаний
     *
     * @param timetables Список расписаний для сохранения
     */
    void saveTimetables(List<TimetableResponseModel> timetables);

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
    Boolean getCabinetForActivity(String cabinetId, String userId);

    /**
     * Метод проверки на доступность аудитории к возврату
     *
     * @param cabinetId Id аудитории для возврата после занятия
     * @return {@link Boolean} Результат проверки, true или false
     */
    Boolean returnCabinetFromActivity(String cabinetId);
}
