package com.emotibot.middleware.controller.element.step;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import org.apache.log4j.Logger;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public class StepCollectionElement implements ControllerElement
{
    private static final Logger logger = Logger.getLogger(StepCollectionElement.class);
    
    private int index;
    private ExecutorService executorService;
    private TreeMap<Integer, ControllerElement> stepElementMap = new TreeMap<Integer, ControllerElement>();
    
    @Override
    public boolean init(Node node)
    {
        if (node == null)
        {
            return false;
        }
        if (!node.getNodeName().equals(ElementConstants.STEP_COLLECTION_TAG))
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
        NodeList childNodeList = node.getChildNodes();
        if (childNodeList == null)
        {
            return false;
        }
        
        for (int i = 0; i < childNodeList.getLength(); i ++)
        {
            Node childNode = childNodeList.item(i);
            if (!childNode.getNodeName().equals(ElementConstants.STEP_TAG))
            {
                continue;
            }
            StepElement step = new StepElement();
            step.setExecutorService(executorService);
            if (step.init(childNode))
            {
                int index = step.getIndex();
                if (stepElementMap.get(index) != null)
                {
                    logger.error("replicae step element index " + index);
                    continue;
                }
                stepElementMap.put(index, step);
            }
        }
        return true;
    }

    /**
     * 这里可以对StepElement进行并行处理
     * 
     */
    @Override
    public Context execute(Context context)
    {
        if (context == null)
        {
            return null;
        }
        List<StepElementTask> stepElementTaskList = new ArrayList<StepElementTask>();
        for (ControllerElement element : stepElementMap.values())
        {
            StepElement stepElement = (StepElement) element;
            StepElementTask stepElementTask = new StepElementTask(stepElement, context);
            stepElementTaskList.add(stepElementTask);
        }
        List<Future<Void>> futureList = new ArrayList<Future<Void>>();
        for(StepElementTask stepElementTask : stepElementTaskList)
        {
            futureList.add(executorService.submit(stepElementTask));
        }
        while(!allDone(futureList))
        {
            try
            {
                Thread.sleep(1);
            } 
            catch (InterruptedException e)
            {
                e.printStackTrace();
                return context;
            }
        }
        return context;
    }
    
    private boolean allDone(List<Future<Void>> tasks)
    {
        boolean allOver = true;
        for(Future<Void> task : tasks)
        {
            if(!task.isDone())
            {
                allOver = false;
            }
        }
        return allOver;
    }

    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    
    public void setExecutorService(ExecutorService executorService)
    {
        this.executorService = executorService;
    }
    
    public ExecutorService getExecutorService()
    {
        return this.executorService;
    }
    
    class StepElementTask implements Callable<Void>
    {

        StepElement stepElement;
        Context context;
        
        public StepElementTask(StepElement stepElement, Context context)
        {
            this.stepElement = stepElement;
            this.context = context;
        }

        @Override
        public Void call() throws Exception
        {
            stepElement.execute(context);
            return null;
        }
        
    }
    
}
