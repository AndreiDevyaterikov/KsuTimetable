package ksutimetable.services;

import ksutimetable.models.ResponseModel;

public interface BookingService {

    /**
     * Метод бронирования аудитории для занятия
     *
     * @param cabinetId Id аудитории для бронирования
     * @param userId    Id Пользователя, бронирующего аудиторию
     * @return {@link ResponseModel} Результат бронирования
     */
    ResponseModel getCabinetForActivity(String cabinetId, String userId);

    /**
     * Метод возврата аудитории после занятия
     *
     * @param cabinetId Id аудитории для возврата после занятия
     * @return {@link ResponseModel} Результат возврата
     */
    ResponseModel returnCabinetFromActivity(String cabinetId);
}
