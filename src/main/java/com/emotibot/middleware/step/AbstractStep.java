package com.emotibot.middleware.step;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.emotibot.middleware.constants.Constants;
import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.response.Response;
import com.emotibot.middleware.response.ResponseType;
import com.emotibot.middleware.task.Task;
import com.emotibot.middleware.task.TaskComparator;

/**
 * 
 * 需要传入一个线程池对象，如果没有，就创建一个(之后要自己关掉的)，用来跑task，这里会将task调用的流程写好，
 * 
 * @author emotibot
 *
 */
public abstract class AbstractStep implements Step
{
    protected ExecutorService executorService;
    protected int timeout = Constants.STEP_TIMEOUT;
    
    public AbstractStep(ExecutorService executorService)
    {
        this.executorService = executorService;
    }
    
    public AbstractStep()
    {
        
    }
    
    @Override
    public void execute(Context context)
    {
        beforeRun(context);
        execute1(context);
        afterRun(context);
    }
    
    public void execute1(Context context)
    {
        List<Task> taskList = context.getTaskList();
        if (taskList == null || taskList.isEmpty())
        {
            return;
        }
        boolean needCloseThreadPool = false;
        if (executorService == null)
        {
            needCloseThreadPool = true;
            executorService = Executors.newFixedThreadPool(Constants.THREAD_POOL_NUM);
        }
        try
        {
            //按照task的优先级排序
            Collections.sort(taskList, new TaskComparator());
            List<Future<Response>> futureList = new ArrayList<Future<Response>>();
            for(Task task : taskList)
            {
                futureList.add(executorService.submit(task));
            }
            
            //开始计时
            long startTime = System.currentTimeMillis();
            for(Future<Response> future : futureList)
            {
                while(true)
                {
                    if (future.isDone() && !future.isCancelled())
                    {
                        Response response = future.get();
                        if (response == null)
                        {
                            break;
                        }
                        ResponseType type = response.getResponseType();
                        context.addOutput(type, response);
                        break;
                    }
                    else
                    {
                        long endTime = System.currentTimeMillis();
                        if (endTime - startTime > timeout)
                        {
                            return;
                        }
                        Thread.sleep(1);
                    }
                }
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (needCloseThreadPool && executorService != null)
            {
                executorService.shutdown();
            }
        }
    }
    
    @Override
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    @Override
    public int getTimeout()
    {
        return this.timeout;
    }
}
