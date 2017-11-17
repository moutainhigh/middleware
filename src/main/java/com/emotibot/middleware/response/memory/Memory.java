package com.emotibot.middleware.response.memory;

import java.util.Map;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Memory
{
    @SerializedName("result")
    @Expose
    private Result result;
    
    @SerializedName("userid")
    @Expose
    private String userid;
    
    @SerializedName("properties")
    @Expose
    private Map<String, String> properties;
    
    public void setResult(Result result)
    {
        this.result = result;
    }
    
    public Result getResult()
    {
        return this.result;
    }
    
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    
    public String getUserid()
    {
        return this.userid;
    }
    
    public void setProperties(Map<String, String> properties)
    {
        this.properties = properties;
    }
    
    public Map<String, String> getProperties()
    {
        return this.properties;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
