package com.company.ProjectManager.desirealizer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonSerializer<T> {

    public T createObject(String json, Class clazz) {
        T serializedObject = null;
        try {
            serializedObject = new ObjectMapper().readerFor(clazz).readValue(json);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return serializedObject;
    }

    public String toJson(T object) {
        String result = null;
        try {
            result = new ObjectMapper().writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return result;
    }

//    public String toJsonArray(T[] objects) {
//        StringBuilder result = new StringBuilder("[");
//        for (var object: objects) {
//            try {
//                result = result.append(new ObjectMapper().writeValueAsString(object));
//
//            } catch (JsonProcessingException e) {
//                e.printStackTrace();
//            }
//        }
//        result.append("]");
//        return result;
//    }
}
