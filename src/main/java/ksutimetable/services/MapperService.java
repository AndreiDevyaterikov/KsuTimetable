package ksutimetable.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MapperService {

    public  <T> List<T> mapResponseToList (Class<T> typeClass, String response){

        //Мапим тело ответа в лист нужной сущности(typeClass)
        ObjectMapper objectMapper = new ObjectMapper();
        CollectionType listType = objectMapper.getTypeFactory().constructCollectionType(ArrayList.class, typeClass);
        List<T> list;
        try {
            list = objectMapper.readValue(response, listType);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public  <T> String mapListToJsonString(List<T> list){
        ObjectMapper mapper = new ObjectMapper();
        String jsonString;

        try {
            jsonString = mapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonString;
    }
}
