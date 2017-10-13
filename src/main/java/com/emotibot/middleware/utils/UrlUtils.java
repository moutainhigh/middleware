package com.emotibot.middleware.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlUtils
{
    public static String getUrl(String host, String port, String endPoint, String text)
    {
        return "http://" + host + ":" + port  + endPoint + text;
    }
    
    public static String urlEncode(String text)
    {
        if (StringUtils.isEmpty(text))
        {
            return text;
        }
        try
        {
            return URLEncoder.encode(text, "UTF-8");
        } 
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String urlDecode(String text)
    {
        if (StringUtils.isEmpty(text))
        {
            return text;
        }
        try
        {
            return URLDecoder.decode(text, "UTF-8");
        } 
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
