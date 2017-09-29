package com.emotibot.middleware.controller.utils;

import java.lang.reflect.Constructor;


public class ClassUtils
{
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static Object getIntence(String className)
    {
        try
        {
            Class clazz =  Class.forName(className);
            Constructor constructor = clazz.getConstructor();
            Object ret = constructor.newInstance();
            return ret;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
