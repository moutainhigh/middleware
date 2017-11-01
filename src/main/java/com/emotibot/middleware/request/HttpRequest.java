package com.emotibot.middleware.request;

public class HttpRequest implements Request
{
    private String url = null;
    
    private String body = null;
    
    private HttpRequestType type = null;
    
    private String cookieStr = null;
        
    public HttpRequest()
    {
        
    }
    
    public HttpRequest(String url, String body, HttpRequestType type)
    {
        this.url = url;
        this.body = body;
        this.type = type;
    }
    
    public HttpRequest(String url, String body, HttpRequestType type, String cookieStr)
    {
        this.url = url;
        this.body = body;
        this.type = type;
        this.cookieStr = cookieStr;
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
    
    public String getCookieStr()
    {
        return this.cookieStr;
    }
    
    public void setCookieStr(String cookieStr)
    {
        this.cookieStr = cookieStr;
    }
}
