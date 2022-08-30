package ksutimetable.services;

import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.models.ResponseModel;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Slf4j
public class InteractionService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    private final CabinetRepository cabinetRepository;

    public ResponseModel getCabinetForActivity(String cabinetId, String userId) {

        var user = userRepository.findById(userId);
        if (user.isEmpty()) {
            log.error("Not found user with id: " + userId);
            throw new KsuTimetableException("Не существует такого пользователя", 404);
        }
        var cabinet = cabinetRepository.findById(cabinetId);
        if (cabinet.isEmpty()) {
            log.error("Not found cabinet with id: " + cabinetId);
            throw new KsuTimetableException("Не существует такой аудитории", 404);
        }

        var description = timetableRepository.getCabinetForActivity(cabinetId, userId);

        if (description.equals("Вы успешно успешно взяли аудиторию")) {
            log.info("User with id: " + userId + " takes cabinet with id: " + cabinetId);
            return new ResponseModel(200, description);
        } else {
            log.error(description);
            throw new KsuTimetableException(description, 409);
        }
    }

    public ResponseModel returnCabinetFromActivity(String cabinetId) {

        var cabinet = cabinetRepository.findById(cabinetId);
        if (cabinet.isEmpty()) {
            log.error("Not found cabinet with id: " + cabinetId);
            throw new KsuTimetableException("Не существует такой аудитории", 404);
        }

        var description = timetableRepository.returnCabinetFromActivity(cabinetId);
        if (description.equals("Вы освободили аудиторию")) {
            return new ResponseModel(200, description);
        } else {
            throw new KsuTimetableException(description, 409);
        }
    }
}
