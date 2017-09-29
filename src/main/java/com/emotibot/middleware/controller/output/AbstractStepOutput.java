package com.emotibot.middleware.controller.output;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public abstract class AbstractStepOutput implements StepOutput
{

    @Override
    public void setOuput(Context context, String output)
    {
        context.setValue(ElementConstants.OUTPUT_ELEMENT, output);
    }

}
