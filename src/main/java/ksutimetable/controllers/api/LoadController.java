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
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Load controller", description = "Контроллер загрузки данных в БД")
public interface LoadController {
    @PostMapping("/allData")
    @Operation(summary = "Загрузка данных в БД")
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = ResponseModel.class))
                            )
                    },
                    description = Constants.DATA_HAS_BEEN_LOADED
            )
    })
    ResponseModel loadDataToDatabase();
}
