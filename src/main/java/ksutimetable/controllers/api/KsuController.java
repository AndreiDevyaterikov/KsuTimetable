package ksutimetable.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ksutimetable.constants.Constants;
import ksutimetable.entities.*;
import ksutimetable.models.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Tag(name = "Ksu controller", description = "Контроллер для получения прочей информации")
public interface KsuController {

    @GetMapping("/directionsForFaculty/{facultyId}")
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
                    description = Constants.NOT_FOUND_FACULTY_WITH_ID
            ),
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Direction.class))
                            )
                    }
            )
    })
    List<Direction> getDirectionsByFacultyId(@PathVariable String facultyId);


    @GetMapping("/faculties")
    @Operation(summary = "Получить все институты")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Faculty.class))
                            )
                    }
            )
    })
    List<Faculty> getAllFaculties();


    @GetMapping("/groupsForDirection/{directionId}")
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
                    description = Constants.NOT_FOUND_DIRECTION_WITH_ID
            ),
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Group.class))
                            )
                    }
            )
    })
    List<Group> getGroupsByDirectionId(@PathVariable String directionId);

    @GetMapping("/buildings")
    @Operation(summary = "Получить все корпуса")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Building.class))
                            )
                    }
            )
    })
    List<Building> getBuildings();

    @GetMapping("/cabinetsForBuilding{buildingId}")
    @Operation(summary = "Получить все аудитории для выбранного корпуса")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = Constants.NOT_FOUND_BUILDING_WITH_ID
            ),
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Cabinet.class))
                            )
                    }
            )
    })
    List<Cabinet> getCabinetsByBuildingId(@PathVariable String buildingId);

    @GetMapping("/users")
    @Operation(summary = "Получить всех пользователей")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = User.class))
                            )
                    }
            )
    })
    List<User> getUsers();
}
