package com.emotibot.middleware.controller.element.adjust;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public class AdjustCollectionElement implements ControllerElement
{

    private List<ControllerElement> adjustElementList = new ArrayList<ControllerElement>();
    private int index;
    private AdjustCollectionType type;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.ADJUST_COLLECTION_TAG))
        {
            return false;
        }
        NamedNodeMap attrs = node.getAttributes();
        Node tmpNode = attrs.getNamedItem(ElementConstants.INDEX);
        if (tmpNode == null)
        {
            return false;
        }
        index = Integer.parseInt(tmpNode.getNodeValue());
        
        tmpNode = attrs.getNamedItem(ElementConstants.ADJUST_COLLECTION_TYPE_ATTR);
        if (tmpNode == null)
        {
            return false;
        }
        String typeStr = tmpNode.getNodeValue();
        if (typeStr.equals(ElementConstants.ADJUST_COLLECTION_TYPE_OR))
        {
            type = AdjustCollectionType.OR;
        }
        else if (typeStr.equals(ElementConstants.ADJUST_COLLECTION_TYPE_AND))
        {
            type = AdjustCollectionType.AND;
        }
        else
        {
            return false;
        }
        
        NodeList childNodeList = node.getChildNodes();
        if (childNodeList == null)
        {
            return false;
        }
        for (int i = 0; i < childNodeList.getLength(); i ++)
        {
            Node childNode = childNodeList.item(i);
            switch(childNode.getNodeName())
            {
            case ElementConstants.ADJUST_COLLECTION_TAG:
                buildAdjustCollection(childNode);
                break;
                
            case ElementConstants.ADJUST_TAG:
                buildAdjust(childNode);
                break;
                
            case ElementConstants.TEXT_ELEMENT_TAG:
                continue;
                
            default:
                break;
            }
        }
        return true;
    }
    
    private void buildAdjustCollection(Node node)
    {
        AdjustCollectionElement adjustCollection = new AdjustCollectionElement();
        if (adjustCollection.init(node))
        {
            adjustElementList.add(adjustCollection);
        }
    }
    
    private void buildAdjust(Node node)
    {
        AdjustElement adjust = new AdjustElement();
        if (adjust.init(node))
        {
            adjustElementList.add(adjust);
        }
    }

    @Override
    public Context execute(Context context)
    {
        boolean result = type.getResult(context, adjustElementList);
        context.setValue(ElementConstants.ADJUST_RESULT, result);
        return context;
    }
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    
}
