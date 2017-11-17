package com.emotibot.middleware.response.memory;

import java.util.List;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result
{
    @SerializedName("relations")
    @Expose
    private List<Relations> relations;
    
    public void setRelation(List<Relations> relations)
    {
        this.relations = relations;
    }
    
    public List<Relations> getRelation()
    {
        return this.relations;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
