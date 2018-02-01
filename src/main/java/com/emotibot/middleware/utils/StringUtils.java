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
    
    public static String replaceFirst(String srcStr, String findStr, String replaceStr)
    {
        int index = srcStr.indexOf(findStr);
        if (index < 0)
        {
            return srcStr;
        }
        return srcStr.substring(0, index) + replaceStr + srcStr.substring(index + findStr.length());
    }
}
