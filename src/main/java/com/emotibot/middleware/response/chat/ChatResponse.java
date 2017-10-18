package com.emotibot.middleware.response.chat;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;

public class ChatResponse extends AbstractResponse
{

    private Chat chat;
    
    public ChatResponse()
    {
        super(CommonResponseType.CHAT);
    }
    
    public ChatResponse(String jsonString)
    {
        super(CommonResponseType.CHAT);
        chat = (Chat) JsonUtils.getObject(jsonString, Chat.class);
    }

    public Chat getChat()
    {
        return this.chat;
    }
    
    @Override
    public String toString()
    {
        return chat.toString();
    }
    
    public String getAnswer()
    {
        if (chat == null)
        {
            return null;
        }
        Res res = chat.getRes();
        
        if (res == null)
        {
            return null;
        }
        return res.getAnswer();
    }
    
    public int getScore()
    {
        if (chat == null)
        {
            return Integer.MIN_VALUE;
        }
        Res res = chat.getRes();
        
        if (res == null)
        {
            return Integer.MIN_VALUE;
        }
        return res.getScore();
    }
    
}
