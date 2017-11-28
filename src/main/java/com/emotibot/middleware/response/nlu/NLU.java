package com.emotibot.middleware.response.nlu;

import java.util.List;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class NLU
{
    @SerializedName("query")
    @Expose
    private String query;
    
    @SerializedName("nlpState")
    @Expose
    private int nlpState;
    
    @SerializedName("stateCode")
    @Expose
    private int stateCode;
    
    @SerializedName("sentenceType")
    @Expose
    private String sentenceType;
    
    @SerializedName("jarVersion")
    @Expose
    private String jarVersion;
    
    @SerializedName("polarity")
    @Expose
    private String polarity;

    @SerializedName("keyword")
    @Expose
    private List<Segment> segment = null;
    
    @SerializedName("namedEntities")
    @Expose
    private String namedEntities;
    
    @SerializedName("namedEntitiesMT")
    @Expose
    private String namedEntitiesMT;
    
    @SerializedName("srl")
    @Expose
    private SRL srl;
    
    public void setQuery(String query)
    {
        this.query = query;
    }
    
    public String getQuery()
    {
        return this.query;
    }
    
    public void setNlpState(int nlpState)
    {
        this.nlpState = nlpState;
    }
    
    public int getNlpState()
    {
        return this.nlpState;
    }
    
    public void setStateCode(int stateCode)
    {
        this.stateCode = stateCode;
    }
    
    public int getStateCode()
    {
        return this.stateCode;
    }
    
    public void setSentenceType(String sentenceType)
    {
        this.sentenceType = sentenceType;
    }
    
    public String getSentenceType()
    {
        return this.sentenceType;
    }
    
    public void setJarVersion(String jarVersion)
    {
        this.jarVersion = jarVersion;
    }
    
    public String getJarVersion()
    {
        return this.jarVersion;
    }
    
    public void setPolarity(String polarity)
    {
        this.query = polarity;
    }
    
    public String getPolarity()
    {
        return this.polarity;
    }
    
    public void setSegment(List<Segment> segment)
    {
        this.segment = segment;
    }
    
    public List<Segment> getSegment()
    {
        return this.segment;
    }
    
    public void setNamedEntities(String namedEntities)
    {
        this.namedEntities = namedEntities;
    }
    
    public String getNamedEntities()
    {
        return this.namedEntities;
    }
    
    public void setNamedEntitiesMT(String namedEntitiesMT)
    {
        this.namedEntitiesMT = namedEntitiesMT;
    }
    
    public String getNamedEntitiesMT()
    {
        return this.namedEntitiesMT;
    }
    
    public void setSrl(SRL srl)
    {
        this.srl = srl;
    }
    
    public SRL getSrl()
    {
        return this.srl;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
