package com.emotibot.middleware.task.restCallTask;

import java.net.HttpURLConnection;

import com.emotibot.middleware.response.HttpResponse;
import com.emotibot.middleware.response.Response;
import com.emotibot.middleware.response.emotion.EmotionResponse;
import com.emotibot.middleware.task.RestCallTask;

public class EmotionTask extends RestCallTask
{

    @Override
    public Response call() throws Exception
    {
        HttpResponse response = restCall();
        int stateCode = response.getStateCode();
        if (stateCode != HttpURLConnection.HTTP_OK)
        {
            return null;
        }
        String responseStr = response.getResponse();
        return new EmotionResponse(responseStr);
    }

}
