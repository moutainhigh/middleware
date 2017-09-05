package com.emotibot.middleware.response;

/**
 * 可以是Rest或者RPC调用的结果，也可以各个module返回结果解析后的结果
 * 
 * @author emotibot
 *
 */

public interface Response
{
    public ResponseType getResponseType();
}
