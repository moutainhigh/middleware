package com.emotibot.middleware.response.emotion;

import java.util.List;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;

public class EmotionResponse extends AbstractResponse
{
    private Emotion emotion;
    
    public EmotionResponse()
    {
        super(CommonResponseType.EMOTION);
    }
    
    public EmotionResponse(String jsonString)
    {
        super(CommonResponseType.EMOTION);
        emotion = (Emotion) JsonUtils.getObject(jsonString, Emotion.class);
    }
    
    public Emotion getEmotion()
    {
        return this.emotion;
    }
    
    public String getLabel()
    {
        if (emotion == null)
        {
            return null;
        }
        List<Prediction> predictions = emotion.getPredictions();
        if (predictions == null || predictions.isEmpty())
        {
            return null;
        }
        Prediction prediction = predictions.get(0);
        return prediction.getLabel();
    }
    
    public int getScore()
    {
        if (emotion == null)
        {
            return Integer.MIN_VALUE;
        }
        List<Prediction> predictions = emotion.getPredictions();
        if (predictions == null || predictions.isEmpty())
        {
            return Integer.MIN_VALUE;
        }
        Prediction prediction = predictions.get(0);
        return prediction.getScore();
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(emotion);
    }
}
