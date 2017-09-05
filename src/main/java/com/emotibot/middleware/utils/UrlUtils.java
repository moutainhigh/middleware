package com.emotibot.middleware.utils;

public class UrlUtils
{
    public static String getUrl(String host, String port, String endPoint, String text)
    {
        return "http://" + host + ":" + port  + endPoint + text;
    }
}
