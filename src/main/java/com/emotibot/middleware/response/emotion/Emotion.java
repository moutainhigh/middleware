package com.emotibot.middleware.response.emotion;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Emotion
{
    @SerializedName("version")
    @Expose
    private String version;
    
    @SerializedName("type")
    @Expose
    private String type;
    
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions;
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getVersion()
    {
        return this.version;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public void setPredictions(List<Prediction> predictions)
    {
        this.predictions = predictions;
    }
    
    public List<Prediction> getPredictions()
    {
        return this.predictions;
    }
}
