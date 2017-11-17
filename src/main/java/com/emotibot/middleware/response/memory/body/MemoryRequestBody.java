package com.emotibot.middleware.response.memory.body;

import java.util.List;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MemoryRequestBody
{
    @SerializedName("type")
    @Expose
    private String type;
    
    @SerializedName("operation")
    @Expose
    private String operation;
    
    @SerializedName("userid")
    @Expose
    private String userid;
    
    @SerializedName("invoker")
    @Expose
    private String invoker;
    
    @SerializedName("labels")
    @Expose
    private String labels;
    
    @SerializedName("properties")
    @Expose
    private List<String> properties;
    
    @SerializedName("limit")
    @Expose
    private int limit;
    
    @SerializedName("relations")
    @Expose
    private List<RelationBody> relations;
    
    public void setType(String type)
    {
        this.type = type;
    }
    
    public String getType()
    {
        return this.type;
    }
    
    public void setOperation(String operation)
    {
        this.operation = operation;
    }
    
    public String getOperation()
    {
        return this.operation;
    }
    
    public void setUserid(String userid)
    {
        this.userid = userid;
    }
    
    public String getUserid()
    {
        return this.userid;
    }
    
    public void setInvoker(String invoker)
    {
        this.invoker = invoker;
    }
    
    public String getInvoker()
    {
        return this.invoker;
    }
    
    public void setLabels(String labels)
    {
        this.labels = labels;
    }
    
    public String getLabels()
    {
        return this.labels;
    }
    
    public void setProperties(List<String> properties)
    {
        this.properties = properties;
    }
    
    public List<String> getProperties()
    {
        return this.properties;
    }
    
    public void setLimit(int limit)
    {
        this.limit = limit;
    }
    
    public int getLimit()
    {
        return this.limit;
    }
    
    public void setRelations(List<RelationBody> relations)
    {
        this.relations = relations;
    }
    
    public List<RelationBody> getRelations()
    {
        return this.relations;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
