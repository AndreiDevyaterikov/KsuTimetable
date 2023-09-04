package ksutimetable;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Faculty;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.services.FacultyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

public class FacultyServiceTest {

    FacultyService facultyService = Mockito.mock(FacultyService.class);

    @Test
    public void getAllFacultiesTest() {
        List<Faculty> faculties = List.of(
                (new Faculty("1", "Институт автоматизированных систем и технологий")),
                (new Faculty("4", "Институт гуманитарных наук и социальных технологий"))
        );
        Mockito.when(facultyService.getFaculties())
                .thenReturn(faculties);
    }

    @Test
    public void getEmptyFacultiesTest() {
        Mockito.when(facultyService.getFaculties())
                .thenReturn(Collections.emptyList());
    }

    @Test
    public void getFacultyByIdTest() {
        Mockito.when(facultyService.getFacultyById("1"))
                .thenReturn(
                        new Faculty(
                                "1", "Институт автоматизированных систем и технологий"
                        )
                )
                .thenThrow(
                        new KsuTimetableException(
                                String.format(Constants.NOT_FOUND_FACULTY_WITH_ID, "1"), 404
                        )
                );
    }
}
