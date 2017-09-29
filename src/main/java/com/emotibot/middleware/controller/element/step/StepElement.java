package com.emotibot.middleware.controller.element.step;

import java.util.concurrent.ExecutorService;

import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;
import com.emotibot.middleware.controller.utils.ClassUtils;
import com.emotibot.middleware.step.Step;

public class StepElement implements ControllerElement
{

    private static final Logger logger = Logger.getLogger(StepElement.class);
    
    private String className;
    private int index;
    private int timeout;
    private ExecutorService executorService;
    
    private Step step;
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_TAG))
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
        
        tmpNode = attrs.getNamedItem(ElementConstants.STEP_TIMEOUT_ATTR);
        if (tmpNode == null)
        {
            return false;
        }
        timeout = Integer.parseInt(tmpNode.getNodeValue());
        
        tmpNode = attrs.getNamedItem(ElementConstants.INDEX);
        if (tmpNode == null)
        {
            return false;
        }
        index = Integer.parseInt(tmpNode.getNodeValue());
        
        step = (Step) ClassUtils.getIntence(className);
        if (step == null)
        {
            return false;
        }
        step.setExecutorService(executorService);
        step.setTimeout(timeout);
        return true;
    }

    @Override
    public Context execute(Context context)
    {
        long startTime = System.currentTimeMillis();
        step.clearOutputMap(context);
        step.execute(context);
        long endTime = System.currentTimeMillis();
        logger.debug(this.getClass().getName() + ": [" + (endTime - startTime) + "]ms");
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
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    public int getTimeout()
    {
        return this.timeout;
    }
    
    public void setStep(Step step)
    {
        this.step = step;
    }
    
    public Step getStep()
    {
        return this.step;
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
