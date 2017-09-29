package com.emotibot.middleware.controller.controller;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.constants.ElementConstants;
import com.emotibot.middleware.controller.element.step.StepControllerElement;

public class StepControllerConfig
{
    
    Map<String, StepControllerElement> stepControllerMap = new HashMap<String, StepControllerElement>();
    
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_CONTROLLER_CONFIG_TAG))
        {
            return false;
        }
        NodeList childNodeList = node.getChildNodes();
        for (int i = 0; i < childNodeList.getLength(); i ++)
        {
            Node childNode = childNodeList.item(i);
            if (!childNode.getNodeName().equals(ElementConstants.STEP_CONTROLLER_TAG))
            {
                continue;
            }
            StepControllerElement stepControllerElement = new StepControllerElement();
            if (stepControllerElement.init(childNode))
            {
                stepControllerMap.put(stepControllerElement.getName(), stepControllerElement);
            }
        }
        return true;
    }
    
    public String execute(String request, String stepControllerName)
    {
        Context context = new Context();
        context.setValue(ElementConstants.INPUT_ELEMENT, request);
        StepControllerElement stepControllerElement = stepControllerMap.get(stepControllerName);
        if (stepControllerElement == null)
        {
            return null;
        }
        stepControllerElement.execute(context);
        return (String) context.getValue(ElementConstants.OUTPUT_ELEMENT);
    }

}
