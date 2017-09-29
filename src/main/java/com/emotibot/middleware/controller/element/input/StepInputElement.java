package com.emotibot.middleware.controller.element.input;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;
import com.emotibot.middleware.controller.input.StepInput;
import com.emotibot.middleware.controller.utils.ClassUtils;

public class StepInputElement implements ControllerElement
{

    private String className;
    private StepInput input;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_INPUT_TAG))
        {
            return false;
        }
        NamedNodeMap attrs = node.getAttributes();
        Node tmpNode = attrs.getNamedItem(ElementConstants.CLASSNAME);
        if (tmpNode == null)
        {
            return false;
        }
        className = tmpNode.getNodeValue();
        
        input = (StepInput) ClassUtils.getIntence(className);
        if (input == null)
        {
            return false;
        }
        return true;
    }

    @Override
    public Context execute(Context context)
    {
        if (context == null)
        {
            return null;
        }
        
        if (input != null)
        {
            String result = input.getInput(context);
            input.execute(result, context);
        }
        return context;
    }
    
    public void setClassName(String className)
    {
        this.className = className;
    }
    
    public String getClassName()
    {
        return this.className;
    }

}
