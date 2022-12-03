package ksutimetable.services.impl;

import ksutimetable.entities.Cabinet;
import ksutimetable.repositories.CabinetRepository;
import ksutimetable.services.CabinetService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
