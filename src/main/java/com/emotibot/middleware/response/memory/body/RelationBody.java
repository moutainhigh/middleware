package com.emotibot.middleware.response.memory.body;

import java.util.List;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelationBody
{
    @SerializedName("relation")
    @Expose
    private String relation;
    
    @SerializedName("entities")
    @Expose
    private List<String> entities;
    
    public void setRelation(String relation)
    {
        this.relation = relation;
    }
    
    public String getRelation()
    {
        return this.relation;
    }
    
    public void setEntities(List<String> entities)
    {
        this.entities = entities;
    }
    
    public List<String> getEntities()
    {
        return this.entities;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
