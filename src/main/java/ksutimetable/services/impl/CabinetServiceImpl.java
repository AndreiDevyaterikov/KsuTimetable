package ksutimetable.services.impl;

import ksutimetable.entities.Cabinet;
import ksutimetable.exceptions.KsuTimetableException;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.CabinetService;
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
    public void saveCabinets(String cabinets) {
        cabinetRepository.saveCabinets(cabinets);
    }

    @Override
    public List<Cabinet> getCabinets() {
        return cabinetRepository.findAll();
    }

    @Override
    public Cabinet getCabinetById(String cabinetId) {
        var cabinetOpt = cabinetRepository.findById(cabinetId);
        if(cabinetOpt.isPresent()){
            return cabinetOpt.get();
        } else {
            var message = String.format("Not found cabinet with id %s", cabinetId);
            log.info(message);
            throw new KsuTimetableException(message, 404);
        }
    }
}
