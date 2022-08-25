package ksutimetable.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Data
@Builder
public class RequestModel {

    @JsonInclude(Include.NON_NULL)
    private final String action;

    @JsonInclude(Include.NON_NULL)
    private final String mode;

    @JsonInclude(Include.NON_NULL)
    private final String id;
}
