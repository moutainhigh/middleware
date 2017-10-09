package com.emotibot.middleware.step;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.response.Response;
import com.emotibot.middleware.response.ResponseType;
import com.emotibot.middleware.task.Task;

public interface Step
{
    public void beforeRun(Context context);
    
    public void afterRun(Context context);
    
    public void execute(Context context);
        
    public void setTimeout(int timeout);
    
    public int getTimeout();
    
    public void setExecutorService(ExecutorService executorService);
    
    public ExecutorService getExecutorService();
    
    public void addOutput(Context context, ResponseType type, Response response);
    
    public Map<ResponseType, List<Response>> getOutputMap(Context context);
    
    public void clearOutputMap(Context context);  
    
    public String getNameSpace();
    
    public void addTask(Context context, Task task);
    
    public void clearTaskList(Context context);
}
