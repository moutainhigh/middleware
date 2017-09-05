package com.emotibot.middleware.response.intent;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Intent
{
    @SerializedName("version")
    @Expose
    private String version;
    
    @SerializedName("predictions")
    @Expose
    private List<Prediction> predictions;
    
    @SerializedName("updated_date")
    @Expose
    private String updated_date;
    
    @SerializedName("type")
    @Expose
    private String type;
    
    public Intent()
    {
        
    }
    
    public void setVersion(String version)
    {
        this.version = version;
    }
    
    public String getVersion()
    {
        return this.version;
    }
    
    public void setPredictions(List<Prediction> predictions)
    {
        this.predictions = predictions;
    }
    
    public List<Prediction> getPredictions()
    {
        return this.predictions;
    }
    
    public void setUpdated_date(String updated_date)
    {
        this.updated_date = updated_date;
    }
    
    public String Updated_date()
    {
        return this.updated_date;
    }
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
}
