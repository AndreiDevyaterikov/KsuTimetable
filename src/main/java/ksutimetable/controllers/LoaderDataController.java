package ksutimetable.controllers;

import ksutimetable.controllers.api.LoaderControllerApi;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.loaders.MainLoader;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/load")
@AllArgsConstructor
public class LoaderDataController implements LoaderControllerApi {

    private final MainLoader mainLoader;

    @Override
    public ResponseModel loadAllDataToDatabase() {
        return mainLoader.loadAllDataToDatabase();
    }
}
