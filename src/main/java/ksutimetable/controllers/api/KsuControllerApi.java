package ksutimetable.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ksutimetable.entities.Direction;
import ksutimetable.entities.Faculty;
import ksutimetable.entities.Group;
import ksutimetable.models.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface KsuControllerApi {

    @GetMapping("/directions/{facultyId}")
    @Operation(summary = "Получить направления подготовки для выбранного института")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Не найдено направлений подготовки для выбранного института"),

            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Direction.class))
                    )
            })
    })
    List<Direction> getDirectionsByFacultyId(@PathVariable String facultyId);


    @GetMapping("/faculties")
    @Operation(summary = "Получить все институты")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Институты отсутствуют в БД"),

            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Faculty.class))
                    )
            })
    })
    List<Faculty> getAllFaculties();



    @GetMapping("/groups/{directionId}")
    @Operation(summary = "Получить все группы для выбранного направления подготовки")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Не найдено групп для выбранного направления подготовки"),

            @ApiResponse(responseCode = "200", content = {
                    @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Group.class))
                    )
            })
    })
    List<Group> getGroupsByDirectionId(@PathVariable String directionId);
}
