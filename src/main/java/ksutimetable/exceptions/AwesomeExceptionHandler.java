package ksutimetable.exceptions;

import ksutimetable.models.ResponseModel;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;


@RestControllerAdvice
public class AwesomeExceptionHandler {

    @ExceptionHandler(KsuTimetableException.class)
    public ResponseModel notFoundExceptionHandler(KsuTimetableException exception, HttpServletResponse response) {
        response.setStatus(exception.getHttpCode());
        return new ResponseModel(exception.getHttpCode(),
                exception.getMessage());
    }
}
