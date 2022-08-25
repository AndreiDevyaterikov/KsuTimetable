package ksutimetable.controllers;

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
    public void loadAllDataToDatabase(){
        mainLoader.loadAllDataToDatabase();
    }
}
