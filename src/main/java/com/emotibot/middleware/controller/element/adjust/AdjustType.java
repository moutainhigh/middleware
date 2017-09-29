package com.emotibot.middleware.controller.element.adjust;

import java.util.Collection;

public enum AdjustType
{
    BOOLEAN
    {

        @Override
        public boolean getResult(Object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj instanceof Boolean))
            {
                return false;
            }
            Boolean ret = (Boolean) obj;
            return ret;
        }
        
    }, 
    
    STRING
    {

        @Override
        public boolean getResult(Object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj instanceof String))
            {
                return false;
            }
            String ret = (String) obj;
            if (ret.isEmpty())
            {
                return false;
            }
            return true;
        }
        
    }, 
    
    COLLECTION
    {

        @SuppressWarnings("rawtypes")
        @Override
        public boolean getResult(Object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (!(obj instanceof Collection))
            {
                return false;
            }
            Collection ret = (Collection) obj;
            if (ret.isEmpty())
            {
                return false;
            }
            return true;
        }
        
    };
    
    public abstract boolean getResult(Object obj);
    
}
