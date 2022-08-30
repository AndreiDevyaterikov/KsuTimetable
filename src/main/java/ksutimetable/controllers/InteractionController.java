package ksutimetable.controllers;

import ksutimetable.controllers.api.InteractionControllerApi;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.InteractionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
@AllArgsConstructor
public class InteractionController implements InteractionControllerApi {

    private final InteractionService interactionService;


    @Override
    public ResponseModel getCabinetForActivity(String cabinetId, String userId) {
        return interactionService.getCabinetForActivity(cabinetId, userId);
    }

    @Override
    public ResponseModel returnCabinetFromActivity(String cabinetId) {
        return interactionService.returnCabinetFromActivity(cabinetId);
    }
}
