package com.emotibot.middleware.controller.utils;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class XmlUtils
{
    private static DocumentBuilder builder = null;
    
    static 
    {
        try
        {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            builder = factory.newDocumentBuilder();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public static Document parserXmlFile(String path)
    {
        if (path == null)
        {
            return null;
        }
        try
        {
            Document doc = builder.parse(path);
            return doc;
        } 
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
