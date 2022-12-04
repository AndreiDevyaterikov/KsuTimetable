package ksutimetable.services;

import ksutimetable.entities.Cabinet;

import java.util.List;

public interface CabinetService {

    /**
     * Метод сохранения аудитории\кабинетов
     *
     * @param cabinets Список аудитори\кабинетов для сохранения
     */
    void saveCabinets(List<Cabinet> cabinets);

    /**
     * Метод получения кабинета по Id
     *
     * @param cabinetId Id кабинета для поиска кабинета
     * @return {@link Cabinet} Найденный кабинет
     */
    Cabinet getCabinetById(String cabinetId);
}
