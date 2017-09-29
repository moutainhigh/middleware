package com.emotibot.middleware.controller.output;

import com.emotibot.middleware.context.Context;

public interface StepOutput
{
    public void setOuput(Context context, String output);
    
    public String execute(Context context);
}
