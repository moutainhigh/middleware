package com.emotibot.middleware.response.emotion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Prediction
{
    @SerializedName("action")
    @Expose
    private String action;
    
    @SerializedName("label")
    @Expose
    private String label;
    
    @SerializedName("score")
    @Expose
    private int score = Integer.MIN_VALUE;
    
    public void setAction(String action)
    {
        this.action = action;
    }
    
    public String getAction()
    {
        return this.action;
    }
    
    public void setLabel(String label)
    {
        this.label = label;
    }
    
    public String getLabel()
    {
        return this.label;
    }
    
    public void setScore(int score)
    {
        this.score = score;
    }
    
    public int getScore()
    {
        return this.score;
    }
}
