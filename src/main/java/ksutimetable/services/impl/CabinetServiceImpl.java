package ksutimetable.services.impl;

import ksutimetable.constants.Constants;
import ksutimetable.entities.Cabinet;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.CabinetService;
import ksutimetable.utils.MapperService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CabinetServiceImpl implements CabinetService {

    private final CabinetRepository cabinetRepository;

    @Override
    public void saveCabinets(List<Cabinet> cabinets) {
        var cabinetsJson = MapperService.mapListToJsonString(cabinets);
        cabinetRepository.saveCabinets(cabinetsJson);
    }

    @Override
    public Cabinet getCabinetById(String cabinetId) {
        var cabinetOpt = cabinetRepository.findById(cabinetId);
        if (cabinetOpt.isPresent()) {
            return cabinetOpt.get();
        } else {
            var message = String.format(Constants.NOT_FOUND_CABINET_WITH_ID, cabinetId);
            log.info(message);
            throw new KsuTimetableException(message, 404);
        }
    }
}
