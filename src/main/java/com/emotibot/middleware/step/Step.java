package com.emotibot.middleware.step;

import com.emotibot.middleware.context.Context;

public interface Step
{
    public void beforeRun(Context context);
    
    public void afterRun(Context context);
    
    public void execute(Context context);
        
    public void setTimeout(int timeout);
    
    public int getTimeout();
}
