package com.emotibot.middleware.response.nlu;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SRLElement
{
    @SerializedName("arg1")
    @Expose
    private String arg1;
    
    @SerializedName("pred")
    @Expose
    private String pred;
    
    @SerializedName("arg2")
    @Expose
    private List<String> arg2;
    
    public void setArg1(String arg1)
    {  
       this.arg1 = arg1; 
    }
    
    public String getArg1()
    {
        return this.arg1;
    }
    
    public void setPred(String pred)
    {  
       this.pred = pred; 
    }
    
    public String getPred()
    {
        return this.pred;
    }
    
    public void setArg2(List<String> arg2)
    {  
       this.arg2 = arg2; 
    }
    
    public List<String> getArg2()
    {
        return this.arg2;
    }
}
