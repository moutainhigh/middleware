package com.emotibot.middleware.controller.controller;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emotibot.middleware.controller.utils.XmlUtils;

/**
 * 
 * Controller会将输入的json写入到context中一个特定的字段中，input会默认从该字段中读取context，在input的实现中操作该字段
 * 
 * @author emotibot
 *
 */

public class StepControllerImpl implements StepController
{
    
    private StepControllerConfig stepControllerConfig;
    
    public StepControllerImpl()
    {
        
    }
    
    @Override
    public void loadConfig(String filePath)
    {
        Document document = XmlUtils.parserXmlFile(filePath);
        if (document == null)
        {
            return;
        }
        NodeList nodeList = document.getChildNodes();
        if (nodeList == null || nodeList.getLength() == 0)
        {
            return;
        }
        Node node = nodeList.item(0);
        stepControllerConfig = new StepControllerConfig();
        stepControllerConfig.init(node);
    }

    @Override
    public String execute(String controllerName, String jsonStr)
    {
        if (stepControllerConfig != null)
        {
            return stepControllerConfig.execute(jsonStr, controllerName);
        }
        return null;
    }
    
}
