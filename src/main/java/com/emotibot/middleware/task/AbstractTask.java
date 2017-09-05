package com.emotibot.middleware.task;

/*
 * 完成一个
 * 
 */
public abstract class AbstractTask implements Task
{
    private String uniqId;
    private TaskPriorityType priorityType = TaskPriorityType.NORMAL;
    
    public AbstractTask(String uniqId)
    {
        this.uniqId = uniqId;
    }
    
    public AbstractTask()
    {
        this.uniqId = Thread.currentThread().getName();
    }
    
    @Override
    public String getUniqId()
    {
        return this.uniqId;
    }
    
    @Override
    public void setUniqId(String uniqId)
    {
        this.uniqId = uniqId;
    }
    
    @Override
    public TaskPriorityType getPriorityType()
    {
        return this.priorityType;
    }
    
    @Override
    public void setPriorityType(TaskPriorityType priorityType)
    {
        this.priorityType = priorityType;
    }
}
