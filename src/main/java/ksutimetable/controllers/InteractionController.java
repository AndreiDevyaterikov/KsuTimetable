package ksutimetable.controllers;

import io.swagger.v3.oas.annotations.Operation;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.InteractionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manage")
@AllArgsConstructor
public class InteractionController {

    private final InteractionService interactionService;

    @GetMapping("/{cabinetId}/{userId}")
    @Operation(summary = "Взятие аудитории")
    public ResponseModel getCabinetForActivity(@PathVariable String cabinetId,
                                               @PathVariable String userId) {
        return interactionService.getCabinetForActivity(cabinetId, userId);
    }


    @PutMapping("/{cabinetId}")
    @Operation(summary = "Возврат аудитории")
    public ResponseModel returnCabinetFromActivity(@PathVariable String cabinetId) {
        return interactionService.returnCabinetFromActivity(cabinetId);
    }
}
