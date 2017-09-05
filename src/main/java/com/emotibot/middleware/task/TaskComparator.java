package com.emotibot.middleware.task;

import java.util.Comparator;

public class TaskComparator implements Comparator<Task>
{
    @Override
    public int compare(Task task1, Task task2)
    {
        TaskPriorityType type1 = task1.getPriorityType();
        TaskPriorityType type2 = task2.getPriorityType();
        if(type1.ordinal() < type2.ordinal())
        {
            return -1;
        }
        else if (type1.ordinal() > type2.ordinal())
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }
}
