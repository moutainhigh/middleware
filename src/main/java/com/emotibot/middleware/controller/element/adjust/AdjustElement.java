package com.emotibot.middleware.controller.element.adjust;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public class AdjustElement implements ControllerElement
{

    private String key;
    private AdjustType type;
    private AdjustTarget target;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.ADJUST_TAG))
        {
            return false;
        }
        NamedNodeMap attrs = node.getAttributes();
        Node tmpNode = attrs.getNamedItem(ElementConstants.ADJUST_KEY_ATTR);
        if (tmpNode == null)
        {
            return false;
        }
        key = tmpNode.getNodeValue();
        
        tmpNode = attrs.getNamedItem(ElementConstants.ADJUST_TYPE_ATTR);
        if (tmpNode == null)
        {
            return false;
        }
        String typeStr = tmpNode.getNodeValue();
        if (!buildType(typeStr))
        {
            return false;
        }
        
        tmpNode = attrs.getNamedItem(ElementConstants.ADJUST_TARGET_ATTR);
        if (tmpNode == null)
        {
            return false;
        }
        String targetStr = tmpNode.getNodeValue();
        if (!buildTarget(targetStr))
        {
            return false;
        }
        return true;
    }

    private boolean buildType(String typeStr)
    {
        switch (typeStr)
        {
        case ElementConstants.ADJUST_TYPE_BOOLEAN:
            type = AdjustType.BOOLEAN;
            break;
        case ElementConstants.ADJUST_TYPE_STRING:
            type = AdjustType.STRING;
            break;
        case ElementConstants.ADJUST_TYPE_COLLECTION:
            type = AdjustType.COLLECTION;
            break;
        default:
            return false;
        }
        return true;
    }
    
    private boolean buildTarget(String targetStr)
    {
        switch (targetStr)
        {
        case ElementConstants.ADJUST_TARGET_TRUE:
            target = AdjustTarget.TRUE;
            break;
        case ElementConstants.ADJUST_TARGET_FALSE:
            target = AdjustTarget.FALSE;
            break;
        default:
            return false;
        }
        return true;
    }
    
    @Override
    public Context execute(Context context)
    {
        Object obj = context.getValue(key);
        boolean ret = type.getResult(obj);
        ret = target.getResult(ret);
        context.setValue(ElementConstants.ADJUST_RESULT, ret);
        return context;
    }

}
