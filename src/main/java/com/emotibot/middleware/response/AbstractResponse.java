package com.emotibot.middleware.response;

public class AbstractResponse implements Response
{
    private final ResponseType type;

    public AbstractResponse(ResponseType type)
    {
        this.type = type;
    }
    
    @Override
    public ResponseType getResponseType()
    {
        return type;
    }
}
