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
    
    public static int appearNumber(String srcStr, String findStr)
    {
        int count = 0;
        int index = 0;
        while ((index = srcStr.indexOf(findStr, index)) != -1) 
        {
            index = index + findStr.length();
            count++;
        }
        return count;
    }
}
