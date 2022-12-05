package ksutimetable.controllers;

import ksutimetable.controllers.api.LoadController;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.LoadService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@AllArgsConstructor
public class LoadControllerImpl implements LoadController {

    private final LoadService loadService;

    @Override
    public ResponseModel loadDataToDatabase() {
        return loadService.loadData();
    }
}
