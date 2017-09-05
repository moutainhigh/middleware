package com.emotibot.middleware.response.intent;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction
{
    @SerializedName("label")
    @Expose
    private String label;
    
    @SerializedName("score")
    @Expose
    private String score;
    
    public Prediction()
    {
        
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public void setScore(String score)
    {
        this.score = score;
    }

    public String getScore()
    {
        return this.score;
    }
}
