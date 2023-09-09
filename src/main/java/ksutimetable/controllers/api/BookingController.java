package ksutimetable.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import ksutimetable.constants.Constants;
import ksutimetable.models.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Tag(name = "Booking controller", description = "Контроллер бронирования аудиторий")
public interface BookingController {

    @GetMapping("/{cabinetId}/{userId}")
    @Operation(summary = "Взять аудиторию для занятия")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Вы успешно успешно взяли аудиторию"
            )
    })
    ResponseModel getCabinetForActivity(@PathVariable String cabinetId, @PathVariable String userId);

    @PutMapping("/{cabinetId}")
    @Operation(summary = "Освободить аудиторию после занятия")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = Constants.CABINET_SUCCESSFULLY_FREED
            ),
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = Constants.NOT_FOUND_CABINET_WITH_ID
            )
    })
    ResponseModel returnCabinetFromActivity(@PathVariable String cabinetId);
}
