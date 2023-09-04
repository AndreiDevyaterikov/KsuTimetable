package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.models.ResponseModel;
import ksutimetable.services.BookingService;
import ksutimetable.services.CabinetService;
import ksutimetable.services.TimetableService;
import ksutimetable.services.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class BookingServiceImpl implements BookingService {

    private final TimetableService timetableService;
    private final UserService userService;
    private final CabinetService cabinetService;

    @Override
    public ResponseModel getCabinetForActivity(String cabinetId, String userId) {

        var user = userService.getUserById(userId);
        var cabinet = cabinetService.getCabinetById(cabinetId);
        var resultMessage = timetableService.getCabinetForActivity(cabinet.getId(), user.getId());

        if (Constants.YOU_SUCCESSFULLY_BOOKED_CABINET.equals(resultMessage)) {
            var message = String.format(Constants.CABINET_SUCCESSFULLY_BOOKED, cabinet.getTitle());
            log.info(message);
            return new ResponseModel(200, message);
        } else {
            log.info(resultMessage);
            throw new KsuTimetableException(resultMessage, 409);
        }
    }

    @Override
    public ResponseModel returnCabinetFromActivity(String cabinetId) {

        var cabinet = cabinetService.getCabinetById(cabinetId);
        var successfullyResult = timetableService.returnCabinetFromActivity(cabinet.getId());

        if (successfullyResult) {
            var message = String.format(Constants.CABINET_SUCCESSFULLY_FREED, cabinet.getTitle());
            log.info(message);
            return new ResponseModel(200, message);
        } else {
            var message = String.format(Constants.CABINET_ALREADY_FREED, cabinet.getTitle());
            log.info(message);
            throw new KsuTimetableException(message, 409);
        }
    }
}
