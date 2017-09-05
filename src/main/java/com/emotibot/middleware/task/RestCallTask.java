package com.emotibot.middleware.task;

import com.emotibot.middleware.constants.Constants;
import com.emotibot.middleware.request.HttpRequest;
import com.emotibot.middleware.response.HttpResponse;
import com.emotibot.middleware.utils.HttpUtils;

public abstract class RestCallTask extends AbstractTask
{
    private HttpRequest request = null;
    private int timeout = Constants.HTTP_TIMEOUT;
    
    public RestCallTask(String uniqId, HttpRequest request, int timeout)
    {
        super(uniqId);
        this.request = request;
        this.timeout = timeout;
    }
    
    public RestCallTask(HttpRequest request, int timeout)
    {
        super();
        this.request = request;
        this.timeout = timeout;
    }
    
    public RestCallTask()
    {
        super();
    }
    
    public HttpRequest getRequest()
    {
        return this.request;
    }
    
    public void setRequest(HttpRequest request)
    {
        this.request = request;
    }
    
    public int getTimeout()
    {
        return this.timeout;
    }
    
    public void setTimeout(int timeout)
    {
        this.timeout = timeout;
    }
    
    protected HttpResponse restCall()
    {
        return HttpUtils.call(request, timeout);
    }
}
