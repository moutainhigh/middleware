package com.emotibot.middleware.response.nlu;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Segment
{
    @SerializedName("word")
    @Expose
    private String word;
    @SerializedName("pos")
    @Expose
    private String pos;
    @SerializedName("orgWord")
    @Expose
    private String orgWord;
    @SerializedName("levelInfo")
    @Expose
    private String levelInfo;
    @SerializedName("flag")
    @Expose
    private int flag;

    public String getWord() 
    {
        return word;
    }

    public void setWord(String word) 
    {
        this.word = word;
    }

    public String getPos() 
    {
        return pos;
    }

    public void setPos(String pos) 
    {
        this.pos = pos;
    }

    public String getOrgWord() 
    {
        return orgWord;
    }

    public void setOrgWord(String orgWord) 
    {
        this.orgWord = orgWord;
    }

    public String getLevelInfo() 
    {
        return levelInfo;
    }

    public void setLevelInfo(String levelInfo) 
    {
        this.levelInfo = levelInfo;
    }

    public int getFlag() 
    {
        return flag;
    }

    public void setFlag(int flag) 
    {
        this.flag = flag;
    }
}
