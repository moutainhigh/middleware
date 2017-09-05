package com.emotibot.middleware.response.intent;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.ResponseType;
import com.emotibot.middleware.utils.JsonUtils;

public class IntentResponse extends AbstractResponse
{
    private Intent intent = null;
    
    public IntentResponse()
    {
        super(ResponseType.INTENT);
    }
    
    public IntentResponse(String jsonString)
    {
        super(ResponseType.INTENT);
        intent = (Intent) JsonUtils.getObject(jsonString, Intent.class);
    }
    
    public Intent getIntent()
    {
        return intent;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(intent);
    }
}
