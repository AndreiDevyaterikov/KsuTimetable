package ksutimetable.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.loaders.MainLoader;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@AllArgsConstructor
public class LoaderDataController {

    private final MainLoader mainLoader;

    @PostMapping("/allData")
    @Operation(summary = "Загрузка данных в БД")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные успешно загружены")
    })
    public ResponseModel loadAllDataToDatabase() {
        return mainLoader.loadAllDataToDatabase();
    }
}
