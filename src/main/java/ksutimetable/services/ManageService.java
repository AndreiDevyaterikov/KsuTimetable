package ksutimetable.services;

import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.models.ResponseModel;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.repositories.TimetableRepository;
import ksutimetable.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ManageService {

    private final TimetableRepository timetableRepository;
    private final UserRepository userRepository;
    private final CabinetRepository  cabinetRepository;

    public ResponseModel getCabinetForActivity(String cabinetId, String userId){

        var user = userRepository.findById(userId);
        if(user.isEmpty()){
            throw new KsuTimetableException("Не существует такого пользователя", 404);
        }
        var cabinet = cabinetRepository.findById(cabinetId);
        if(cabinet.isEmpty()){
            throw new KsuTimetableException("Не существует такой аудитории", 404);
        }

        var description = timetableRepository.getCabinetForActivity(cabinetId, userId);

        if(description.equals("Вы успешно успешно взяли аудиторию")){
            return new ResponseModel(200, description);
        } else {
            return new ResponseModel(409, description);
        }
    }

}
