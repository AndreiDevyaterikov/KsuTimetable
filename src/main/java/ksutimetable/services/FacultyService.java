package ksutimetable.services;

import ksutimetable.entities.Faculty;

import java.util.List;

public interface FacultyService {

    /**
     * Метод сохранения факультетов
     *
     * @param faculties Список факультетов для сохранения
     */
    void saveFaculties(List<Faculty> faculties);

    void saveFaculty(Faculty faculty);

    /**
     * Метод получения факультетов
     *
     * @return {@link Faculty} Список факультетов
     */
    List<Faculty> getFaculties();

    /**
     * Метод поиска факультета по id
     *
     * @param facultyId Id факультета для поиска
     * @return {@link Faculty} Найденый факультет
     */
    Faculty getFacultyById(String facultyId);
}
