package com.emotibot.middleware.response.chat;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Res
{
    @SerializedName("match_question")
    @Expose
    private String match_question;
    
    @SerializedName("answer")
    @Expose
    private String answer;
    
    @SerializedName("score")
    @Expose
    private int score;
    
    @SerializedName("source")
    @Expose
    private String source;
    
    public void setMatch_question(String match_question)
    {
        this.match_question = match_question;
    }
    
    public String getMatch_question()
    {
        return this.match_question;
    }
    
    public void setAnswer(String answer)
    {
        this.answer = answer;
    }
    
    public String getAnswer()
    {
        return this.answer;
    }
    
    public void setScore(int score)
    {
        this.score = score;
    }
    
    public int getScore()
    {
        return this.score;
    }
    
    public void setSource(String source)
    {
        this.source = source;
    }
    
    public String getSource()
    {
        return this.source;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
