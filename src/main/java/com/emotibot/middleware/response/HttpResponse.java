package com.emotibot.middleware.response;

public class HttpResponse extends AbstractResponse
{    
    private String response = null;
    private int stateCode = -1;
    
    public HttpResponse(String response, int stateCode)
    {
        super(CommonResponseType.HTTP);
        this.response = response;
        this.stateCode = stateCode;
    }
    
    public HttpResponse()
    {
        super(CommonResponseType.HTTP);
    }
    
    public String getResponse()
    {
        return response;
    }
    
    public void setResponse(String response)
    {
        this.response = response;
    }
    
    public int getStateCode()
    {
        return stateCode;
    }
    
    public void setStateCode(int stateCode)
    {
        this.stateCode = stateCode;
    }
    
    @Override
    public String toString()
    {
        return "Return code is: " + stateCode + "; Return data is: " +  response;
    }
}
