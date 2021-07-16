package jmu.Hong.util;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JsonUtil {

    public static String getJson(Object object) throws JsonProcessingException {
        return getJson(object, "yyyy-MM-dd HH:mm:ss SSS");
    }

    public static String getJson(Object object, String dataFormat) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        SimpleDateFormat sdf = new SimpleDateFormat(dataFormat);

        mapper.setDateFormat(sdf);

        try {
            return mapper.writeValueAsString(object);
        }
        catch (JsonProcessingException e){
            e.printStackTrace();
        }

        return null;
    }
}
