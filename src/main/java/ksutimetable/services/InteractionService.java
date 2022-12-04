package ksutimetable.services;

import ksutimetable.constants.Constants;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.models.ResponseModel;
import ksutimetable.repositories.TimetableRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InteractionService {

    private final TimetableRepository timetableRepository;
    private final UserService userService;
    private final CabinetService cabinetService;

    public ResponseModel getCabinetForActivity(String cabinetId, String userId) {

        var user = userService.getUserById(userId);
        var cabinet = cabinetService.getCabinetById(cabinetId);
        var successfullyResult = timetableRepository.getCabinetForActivity(cabinet.getId(), user.getId());

        if (successfullyResult) {
            var message = String.format(Constants.CABINET_SUCCESSFULLY_BOOKED, cabinet.getTitle());
            log.info(message);
            return new ResponseModel(200, message);
        } else {
            var message = Constants.CANT_BOOK_CABINET_LOW_TIME_DIFFERENCE;
            log.info(message);
            throw new KsuTimetableException(message, 409);
        }
    }

    public ResponseModel returnCabinetFromActivity(String cabinetId) {

        var cabinet = cabinetService.getCabinetById(cabinetId);
        var successfullyResult = timetableRepository.returnCabinetFromActivity(cabinet.getId());

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
