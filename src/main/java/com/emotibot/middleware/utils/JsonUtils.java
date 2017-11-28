package com.emotibot.middleware.utils;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonUtils
{
    private static Gson gson = new GsonBuilder().disableHtmlEscaping().create();
    //private static Gson gson = new Gson();
    
    public static String getJsonStr(Object obj)
    {
        return gson.toJson(obj);
    }
    
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static Object getObject(String jsonStr, Class clazz)
    {
        return gson.fromJson(jsonStr, clazz);
    }
    
    public static Object getObject(String jsonStr, Type type)
    {
        return gson.fromJson(jsonStr, type);
    }
}
