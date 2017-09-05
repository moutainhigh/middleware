package com.emotibot.middleware.context;

import java.util.HashMap;
import java.util.Map;

public class Context
{
    private Map<String, Object> contextMap = new HashMap<String, Object>();
    
    public Object getValue(String key)
    {
        return contextMap.get(key);
    }
    
    public void setValue(String key, Object value)
    {
        contextMap.put(key, value);
    }
}
