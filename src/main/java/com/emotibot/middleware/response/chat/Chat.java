package com.emotibot.middleware.response.chat;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chat
{
    @SerializedName("module")
    @Expose
    private String module;
    
    @SerializedName("res")
    @Expose
    private Res res;
    
    public void setModule(String module)
    {
        this.module = module;
    }
    
    public String getModule()
    {
        return this.module;
    }
    
    public void setRes(Res res)
    {
        this.res = res;
    }
    
    public Res getRes()
    {
        return this.res;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
