package com.emotibot.middleware.response.memory;

import java.util.List;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relations
{
    @SerializedName("value")
    @Expose
    private List<String> value;
    
    @SerializedName("entity")
    @Expose
    private String entity;
    
    @SerializedName("relation")
    @Expose
    private String relation;
    
    public void setValue(List<String> value)
    {
        this.value = value;
    }
    
    public List<String> getValue()
    {
        return this.value;
    }
    
    public void setEntity(String entity)
    {
        this.entity = entity;
    }
    
    public String getEntity()
    {
        return this.entity;
    }
    
    public void setRelation(String relation)
    {
        this.relation = relation;
    }
    
    public String getRelation()
    {
        return this.relation;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
