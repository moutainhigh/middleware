package com.emotibot.middleware.task;

import java.util.concurrent.Callable;

import com.emotibot.middleware.response.Response;

public interface Task extends Callable<Response>
{
    /**
     * 获取该task的uniq id，默认为线程名称
     * 
     * @return
     */
    public String getUniqId();
    
    public void setUniqId(String uniqId);
    
    public TaskPriorityType getPriorityType();
    
    public void setPriorityType(TaskPriorityType priorityType);
}
