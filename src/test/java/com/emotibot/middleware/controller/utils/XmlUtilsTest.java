package com.emotibot.middleware.controller.utils;


import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlUtilsTest
{

    private static final String XML_FILE = "/Users/emotibot/Documents/workspace/other/middleware/conf/controller.xml";
    
    @Test
    public void test()
    {
        Document document = XmlUtils.parserXmlFile(XML_FILE);
        NodeList nodeList = document.getElementsByTagName("stepControllerConfig");
        Node node = nodeList.item(0);
        System.out.println(node.getNodeName());
    }

}
