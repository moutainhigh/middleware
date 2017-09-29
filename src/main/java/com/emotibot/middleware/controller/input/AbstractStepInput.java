package com.emotibot.middleware.controller.input;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public abstract class AbstractStepInput implements StepInput
{

    @Override
    public String getInput(Context context)
    {
        String input = (String) context.getValue(ElementConstants.INPUT_ELEMENT);
        return input;
    }
    
}
