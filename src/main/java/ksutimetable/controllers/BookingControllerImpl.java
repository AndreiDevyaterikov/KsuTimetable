package ksutimetable.controllers;

import ksutimetable.controllers.api.BookingController;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/cabinet")
public class BookingControllerImpl implements BookingController {

    private final BookingService bookingService;

    @Override
    public ResponseModel getCabinetForActivity(String cabinetId, String userId) {
        return bookingService.getCabinetForActivity(cabinetId, userId);
    }

    @Override
    public ResponseModel returnCabinetFromActivity(String cabinetId) {
        return bookingService.returnCabinetFromActivity(cabinetId);
    }
}
