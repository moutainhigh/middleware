package com.emotibot.middleware.response.commonParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonParserStarModule
{
    @SerializedName("name")
    @Expose
    String name = null;
    
    @SerializedName("profession")
    @Expose
    String profession = null;
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void Profession(String profession)
    {
        this.profession = profession;
    }
    
    public String getProfession()
    {
        return this.profession;
    }
}
