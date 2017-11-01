package com.emotibot.middleware.response;

import java.util.List;
import java.util.Map;

public class HttpResponse extends AbstractResponse
{    
    private String response = null;
    private int stateCode = -1;
    private Map<String, List<String>> responseHeader = null;
    
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
    
    public Map<String, List<String>> getResponseHeader()
    {
        return this.responseHeader;
    }
    
    public void setResponseHeader(Map<String, List<String>> responseHeader)
    {
        this.responseHeader = responseHeader;
    }
    
    @Override
    public String toString()
    {
        return "Return code is: " + stateCode + "; Return data is: " +  response;
    }
}
