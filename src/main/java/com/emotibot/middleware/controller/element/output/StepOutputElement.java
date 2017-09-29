package com.emotibot.middleware.controller.element.output;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;
import com.emotibot.middleware.controller.output.StepOutput;
import com.emotibot.middleware.controller.utils.ClassUtils;

public class StepOutputElement implements ControllerElement
{

    private String className;
    private StepOutput output;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_OUTPUT_TAG))
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
        
        output = (StepOutput) ClassUtils.getIntence(className);
        if (output == null)
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
        
        if (output != null)
        {
            String result = output.execute(context);
            output.setOuput(context, result);
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
