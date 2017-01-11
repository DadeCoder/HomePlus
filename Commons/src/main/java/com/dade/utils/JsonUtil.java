package com.dade.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Dade on 2017/1/11.
 */
public class JsonUtil {

    public static String parseObjectToJson(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            handleException("parse object to json is failed",e);
            return "";
        }
    }

    public static String parseObjectToJsonWithPretty(Object object){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            handleException("parse object to json with pretty is failed",e);
            return "";
        }
    }

    public static <T> T parseJsonToObject(String content,Class<T> clazz){
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.readValue(content, clazz);
        } catch (IOException e) {
            handleException("parse json to object is failed",e);
            return null;
        }
    }

    private static void handleException(String message,Exception e){
        String errorMessage = "["+JsonUtil.class.getName()+"] -> "+message;
        //TODO add Log here
    }

}
