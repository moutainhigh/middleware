package com.emotibot.middleware.response.chatNew;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChatNewResponse extends AbstractResponse
{
    @SerializedName("match_question")
    @Expose
    private String match_question;
    
    @SerializedName("score")
    @Expose
    private int score = Integer.MIN_VALUE;
    
    @SerializedName("answer")
    @Expose
    private String answer;
    
    @SerializedName("source")
    @Expose
    private String source;
    
    public ChatNewResponse()
    {
        super(CommonResponseType.CHATNEW);
    }
    
    public ChatNewResponse(String jsonString)
    {
        super(CommonResponseType.CHATNEW);
        JsonObject jsonObject = (JsonObject) JsonUtils.getObject(jsonString, JsonObject.class);
        if (!jsonObject.has("answer_info"))
        {
            return;
        }
        JsonObject answerInfoObj = jsonObject.getAsJsonObject("answer_info");
        if (answerInfoObj.has("match_question"))
        {
            this.match_question = answerInfoObj.get("match_question").getAsString();
        }
        if (answerInfoObj.has("score"))
        {
            this.score = answerInfoObj.get("score").getAsInt();
        }
        if (answerInfoObj.has("answer"))
        {
            this.answer = answerInfoObj.get("answer").getAsString();
        }
        if (answerInfoObj.has("source"))
        {
            this.source = answerInfoObj.get("source").getAsString();
        }
    }
    
    public String getAnswer()
    {
        return this.answer;
    }
    
    public double getScore()
    {
        return this.score;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }
}
