package com.emotibot.middleware.request;

public class HttpRequest implements Request
{
    private String url = null;
    
    private String body = null;
    
    private HttpRequestType type = null;
        
    public HttpRequest(String url, String body, HttpRequestType type)
    {
        this.url = url;
        this.body = body;
        this.type = type;
    }
    
    public String getUrl()
    {
        return this.url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getBody()
    {
        return this.body;
    }
    
    public void setBody(String body)
    {
        this.body = body;
    }
    
    public HttpRequestType getType()
    {
        return this.type;
    }
    
    public void setType(HttpRequestType type)
    {
        this.type = type;
    }
    
}
