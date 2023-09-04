package ksutimetable.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ksutimetable.entities.Timetable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Timetable controller", description = "Контроллер для получения расписания")
public interface TimetableController {
    @GetMapping("/forGroup/{groupId}")
    @Operation(summary = "Получить рассписание для группы")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Timetable.class))
                            )
                    }
            )
    })
    List<Timetable> getTimetableForGroup(
            @PathVariable String groupId,
            @Parameter(description = "Расписание на текущий день") Boolean lessonForToday
    );

    @GetMapping("/forCabinet/{cabinetId}")
    @Operation(summary = "Получить рассписание для аудитории")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Timetable.class))
                            )
                    }
            )
    })
    List<Timetable> getTimetableForCabinet(
            @PathVariable String cabinetId,
            @Parameter(description = "Расписание на текущий день") Boolean lessonForToday
    );

    @GetMapping("/forTeacher/{teacherId}")
    @Operation(summary = "Получить рассписание для преподавателя")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Timetable.class))
                            )
                    }
            )
    })
    List<Timetable> getTimetableForTeacher(
            @PathVariable String teacherId,
            @Parameter(description = "Расписание на текущий день") Boolean lessonForToday
    );
}
