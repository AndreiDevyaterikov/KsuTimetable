package ksutimetable.controllers.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ksutimetable.models.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

public interface InteractionControllerApi {

    @GetMapping("/{cabinetId}/{userId}")
    @Operation(summary = "Взятие аудитории")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Не существует такого пользователя, Не существует такой аудитории"
            ),

            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Вы успешно успешно взяли аудиторию"
            ),

            @ApiResponse(
                    responseCode = "409",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Конфликтные ошибки"
            )
    })
    ResponseModel getCabinetForActivity(@PathVariable String cabinetId,
                                        @PathVariable String userId);

    @PutMapping("/{cabinetId}")
    @Operation(summary = "Возврат аудитории")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Вы освободили аудиторию"
            ),

            @ApiResponse(
                    responseCode = "404",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Не существует такой аудитории"
            ),

            @ApiResponse(
                    responseCode = "409",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = "Конфликтные ошибки"
            )
    })
    ResponseModel returnCabinetFromActivity(@PathVariable String cabinetId);
}
