package com.emotibot.middleware.controller.input;

import com.emotibot.middleware.context.Context;

/**
 * 将输入转换为event
 * 
 * @author emotibot
 *
 */
public interface StepInput
{
    public String getInput(Context context);
    
    public Context execute(String input, Context context);
}
