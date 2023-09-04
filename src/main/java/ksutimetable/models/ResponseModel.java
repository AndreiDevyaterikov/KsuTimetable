package ksutimetable.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseModel {

    private int statusCode;
    private String description;
}
