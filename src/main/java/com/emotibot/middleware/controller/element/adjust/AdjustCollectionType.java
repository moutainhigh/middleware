package com.emotibot.middleware.controller.element.adjust;

import java.util.List;

import org.apache.log4j.Logger;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.controller.element.ControllerElement;
import com.emotibot.middleware.controller.element.constants.ElementConstants;

public enum AdjustCollectionType
{
    OR 
    {

        @Override
        public boolean getResult(Context context, List<ControllerElement> adjustElementList)
        {
            for (ControllerElement adjustElement : adjustElementList)
            {
                adjustElement.execute(context);
                boolean adjustResult = getAdjustResult(context);
                if (adjustResult)
                {
                    return true;
                }
            }
            return false;
        }
        
    }, 
    
    AND
    {

        @Override
        public boolean getResult(Context context, List<ControllerElement> adjustElementList)
        {
            for (ControllerElement adjustElement : adjustElementList)
            {
                adjustElement.execute(context);
                boolean adjustResult = getAdjustResult(context);
                if (!adjustResult)
                {
                    return false;
                }
            }
            return true;
        }
        
    };
    
    private static final Logger logger = Logger.getLogger(AdjustCollectionType.class);
    
    public abstract boolean getResult(Context context, List<ControllerElement> adjustElementList);
    
    private static boolean getAdjustResult(Context context)
    {
        Boolean adjustResult = (Boolean) context.getValue(ElementConstants.ADJUST_RESULT);
        if (adjustResult == null)
        {
            logger.error("can not get adjust result from context");
            return false;
        }
        return adjustResult;
    }
}
