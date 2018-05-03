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
    
    public static String castDoubleStringToInt(String str)
    {
        try
        {
            Double doubleValue = Double.parseDouble(str);
            if (doubleValue % 1 == 0)
            {
                Integer intValue = Integer.parseInt(new java.text.DecimalFormat("0").format(doubleValue));
                return intValue.toString();
            }
            else
            {
                return str;
            }
        }
        catch(Exception e)
        {
            return str;
        }
    }
    
    public static String unicode2String(String unicode)
    {  
        if (StringUtils.isEmpty(unicode))
        {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        int i = -1;
        int pos = 0;

        while ((i = unicode.indexOf("\\u", pos)) != -1)
        {
            sb.append(unicode.substring(pos, i));
            if (i + 5 < unicode.length()) {
                pos = i + 6;
                sb.append((char) Integer.parseInt(unicode.substring(i + 2, i + 6), 16));
            }
        }
        sb.append(unicode.substring(pos));

        return sb.toString();
    }
}
