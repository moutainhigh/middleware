package com.emotibot.middleware.response.intent;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;

public class IntentResponse extends AbstractResponse
{
    private Intent intent = null;
    
    public IntentResponse()
    {
        super(CommonResponseType.INTENT);
    }
    
    public IntentResponse(String jsonString)
    {
        super(CommonResponseType.INTENT);
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
