package com.emotibot.middleware.utils;

public class StringUtils
{
    public static boolean isEmpty(String str)
    {
        if (str == null || str.trim().isEmpty())
        {
            return true;
        }
        return false;
    }
}
