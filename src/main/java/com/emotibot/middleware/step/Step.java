package com.emotibot.middleware.step;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.task.Task;

public interface Step
{
    public void beforeRun();
    
    public void afterRun();
    
    public void execute();
    
    public void addTask(Task task);
    
    public void setTimeout(int timeout);
    
    public int getTimeout();
    
    public void setContext(Context context);
    
    public Context getContext();
}
