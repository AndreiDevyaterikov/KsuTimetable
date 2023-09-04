package ksutimetable.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import ksutimetable.constants.Constants;
import ksutimetable.exceptions.KsuTimetableException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class MapperService {

    /**
     * Метод мапинга ответа запроса в коллекцию нужного типа
     *
     * @param typeClass Тип коллекции, в которую надо смапить
     * @param response  Ответ запроса, который нужно смапить
     * @return {@link List<T>} Коллекция нужного типа
     */
    public static <T> List<T> mapResponseToList(Class<T> typeClass, String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, typeClass);
        try {
            return objectMapper.readValue(response, listType);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new KsuTimetableException(Constants.INTERNAL_ERROR, 500);
        }
    }

    /**
     * Метод мапинга коллекции в JSON
     *
     * @param list Коллекция для мапинга в JSON
     * @return {@link String} Коллекия в виде JSON
     */
    public static <T> String mapListToJsonString(List<T> list) {
        try {
            return new ObjectMapper().writeValueAsString(list);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new KsuTimetableException(Constants.INTERNAL_ERROR, 500);
        }
    }

    public static <T> String mapModelToJsonString(Object model) {
        try{
            return new ObjectMapper().writeValueAsString(model);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            throw new KsuTimetableException(Constants.INTERNAL_ERROR, 500);
        }
    }
}
