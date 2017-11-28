package com.emotibot.middleware.response.nlu;

import java.util.List;
import java.util.Map;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SRL
{
    @SerializedName("status")
    @Expose
    private int status = -1;
    
    @SerializedName("lang")
    @Expose
    private String lang;
    
    @SerializedName("sentence")
    @Expose
    private String sentence;
    
    @SerializedName("srl")
    @Expose
    private Map<String, List<SRLElement>> srl;
    
    @SerializedName("tokens")
    @Expose
    private List<Map<String, String>> tokens;
    
    public void setStatus(int status)
    {
        this.status = status;
    }
    
    public int getStatus()
    {
        return this.status;
    }
    
    public void setLang(String lang)
    {
        this.lang = lang;
    }
    
    public String getLang()
    {
        return this.lang;
    }
    
    public void setSentence(String sentence)
    {
        this.sentence = sentence;
    }
    
    public String getSentence()
    {
        return this.sentence;
    }
    
    public void setSrl(Map<String, List<SRLElement>> srl)
    {
        this.srl = srl;
    }
    
    public Map<String, List<SRLElement>> getSrl()
    {
        return this.srl;
    }
    
    public void setTokens(List<Map<String, String>> tokens)
    {
        this.tokens = tokens;
    }
    
    public List<Map<String, String>> getTokens()
    {
        return this.tokens;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
