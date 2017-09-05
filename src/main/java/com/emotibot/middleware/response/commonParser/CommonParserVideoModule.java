package com.emotibot.middleware.response.commonParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonParserVideoModule
{
    @SerializedName("name")
    @Expose
    String name = null;
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
}
