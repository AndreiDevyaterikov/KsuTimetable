package ksutimetable.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class KsuTimetableException extends RuntimeException{
    private final String description;
    private final Integer httpCode;
}
