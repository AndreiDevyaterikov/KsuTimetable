package ksutimetable.services;

import ksutimetable.models.ResponseModel;

public interface LoadService {

    /**
     * Метод загрузки данных в БД
     *
     * @return {@link ResponseModel} Результат выполнения загрузки
     * @see ResponseModel
     */
    ResponseModel loadData();
}
