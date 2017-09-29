package com.emotibot.middleware.controller.element.adjust;

public enum AdjustTarget
{
    TRUE
    {

        @Override
        public boolean getResult(boolean tag)
        {
            return tag ? true : false;
        }
        
    },
    
    FALSE
    {

        @Override
        public boolean getResult(boolean tag)
        {
            return tag ? false : true;
        }
        
    };
    
    public abstract boolean getResult(boolean tag);
}
