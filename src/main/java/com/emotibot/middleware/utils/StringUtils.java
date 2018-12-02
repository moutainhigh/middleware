package com.emotibot.middleware.utils;

import java.util.List;

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
    
    /**
     * 判断是否不包含中文字母
     * 
     * @param chat
     * @return
     */
    public static boolean isAllEnglishChat(String str)
    {
        char[] arrays = str.trim().toCharArray();
        for (int i = 0; i < arrays.length; i ++)
        {
            String str1 = String.valueOf(arrays[i]);
            if(str1.matches("[\u4e00-\u9fa5]+"))
            {
                return false;
            }
        }
        return true;
    }
    
    public static String getFileName(String path)
    {
        int index1 = path.lastIndexOf("/");
        int index2 = path.lastIndexOf(".");
        if (index1 < 0 || index2 < 0 || index1 > index2)
        {
            return null;
        }
        return path.substring(index1 + 1, index2);
    }
    
    public static String buildString(List lists, String space, String split) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (Object list : lists) {
            sb.append(String.valueOf(list)).append(space).append(split).append(space);
        }
        String result = sb.toString();
        return result.substring(0, result.length() - space.length() * 2 - split.length());
    }
}
