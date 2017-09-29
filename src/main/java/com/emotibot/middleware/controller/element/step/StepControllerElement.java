package com.emotibot.middleware.controller.element.step;

import java.util.TreeMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.adjust.AdjustCollectionElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;
import com.emotibot.middleware.controller.element.input.StepInputElement;
import com.emotibot.middleware.controller.element.output.StepOutputElement;

public class StepControllerElement implements ControllerElement
{
    private static final Logger logger = Logger.getLogger(StepControllerElement.class);
    
    private String name;
    private int workThreadNum;
    private ControllerElement stepInput;
    private ControllerElement stepOutput;
    private TreeMap<Integer, ControllerElement> stepCollectionAndAdjustCollectionMap = new TreeMap<Integer, ControllerElement>();
    private ExecutorService executorService;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_CONTROLLER_TAG))
        {
            return false;
        }
        NamedNodeMap attrs = node.getAttributes();
        Node nodeTmp = attrs.getNamedItem(ElementConstants.STEP_CONTROLLER_NAME_ATTR);
        if (nodeTmp == null)
        {
            return false;
        }
        name = nodeTmp.getNodeValue();
        
        nodeTmp = attrs.getNamedItem(ElementConstants.STEP_CONTROLLER_WORKTHREAD_ATTR);
        if (nodeTmp == null)
        {
            return false;
        }
        workThreadNum = Integer.parseInt(nodeTmp.getNodeValue());
        executorService = Executors.newFixedThreadPool(workThreadNum);
        
        NodeList childNodeList = node.getChildNodes();
        for (int i = 0; i < childNodeList.getLength(); i ++)
        {
            Node childNode = childNodeList.item(i);
            if (childNode == null)
            {
                continue;
            }
            switch (childNode.getNodeName())
            {
            case ElementConstants.STEP_INPUT_TAG:
                buildStepInput(node);
                break;
            case ElementConstants.STEP_OUTPUT_TAG:
                buildStepOutput(node);
                break;
            case ElementConstants.STEP_COLLECTION_TAG:
                buildStepCollection(node);
                break;
            case ElementConstants.ADJUST_COLLECTION_TAG:
                buildAdjustCollection(node);
                break;
            default:
                logger.error("Can not load element with tag " + childNode.getNodeName());
                break;
            }
        }
        return true;
    }
    
    private void buildStepInput(Node node)
    {
        if (stepInput != null)
        {
            logger.error("can not create more the one input step in controller");
            return;
        }
        StepInputElement stepInputTmp = new StepInputElement();
        if (stepInputTmp.init(node))
        {
            this.stepInput = stepInputTmp;
        }
        else
        {
            logger.error("fail to init the child node " + node.getNodeName());
        }
    }
    
    private void buildStepOutput(Node node)
    {
        if (stepOutput != null)
        {
            logger.error("can not create more the one input step in controller");
            return;
        }
        StepOutputElement stepOutputTmp = new StepOutputElement();
        if (stepOutputTmp.init(node))
        {
            this.stepOutput = stepOutputTmp;
        }
        else
        {
            logger.error("fail to init the child node " + node.getNodeName());
        }
    }
    
    private void buildStepCollection(Node node)
    {
        StepCollectionElement stepCollectionTmp = new StepCollectionElement();
        stepCollectionTmp.setExecutorService(executorService);
        if (stepCollectionTmp.init(node))
        {
            int index = stepCollectionTmp.getIndex();
            if (stepCollectionAndAdjustCollectionMap.get(index) != null)
            {
                logger.error("index is deplicate for step collection " + index);
                return;
            }
            stepCollectionAndAdjustCollectionMap.put(index, stepCollectionTmp);
        }
        else
        {
            logger.error("fail to init the child node " + node.getNodeName());
        }
    }
    
    private void buildAdjustCollection(Node node)
    {
        AdjustCollectionElement adjustCollectionTmp = new AdjustCollectionElement();
        if (adjustCollectionTmp.init(node))
        {
            int index = adjustCollectionTmp.getIndex();
            if (stepCollectionAndAdjustCollectionMap.get(index) != null)
            {
                logger.error("index is deplicate for adjust collection " + index);
                return;
            }
            stepCollectionAndAdjustCollectionMap.put(index, adjustCollectionTmp);
        }
        else
        {
            logger.error("fail to init the child node " + node.getNodeName());
        }
    }

    @Override
    public Context execute(Context context)
    {
        if (context == null)
        {
            return null;
        }
        if (stepInput != null)
        {
            stepInput.execute(context);
        }
        for(ControllerElement element : stepCollectionAndAdjustCollectionMap.values())
        {
            element.execute(context);
            if (element instanceof AdjustCollectionElement)
            {
                if (getAdjustResult(context))
                {
                    break;
                }
            }
        }
        if (stepOutput != null)
        {
            stepOutput.execute(context);
        }
        return context;
    }
    
    private boolean getAdjustResult(Context context)
    {
        Boolean adjustResult = (Boolean) context.getValue(ElementConstants.ADJUST_RESULT);
        if (adjustResult == null)
        {
            return false;
        }
        return adjustResult;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setExecutorService(ExecutorService executorService)
    {
        this.executorService = executorService;
    }
    
    public ExecutorService getExecutorService()
    {
        return this.executorService;
    }

}
