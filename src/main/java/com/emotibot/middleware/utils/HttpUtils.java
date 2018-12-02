package com.emotibot.middleware.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import com.emotibot.middleware.request.HttpRequest;
import com.emotibot.middleware.request.HttpRequestType;
import com.emotibot.middleware.response.HttpResponse;

public class HttpUtils
{
    
    public static HttpResponse call(HttpRequest request, int timeout)
    {
        if (request == null)
        {
            return null;
        }
        
        HttpRequestType type = request.getType();
        switch(type)
        {
        case GET:
            return get(request, timeout);
        case POST:
            return post(request, timeout);
        default:
            return null;     
        }
    }
    
    public static HttpResponse post(HttpRequest request, int timeout) {

        HttpURLConnection conn = null;
        String urlStr = request.getUrl();
        String body = request.getBody();
        HttpResponse response = new HttpResponse();
        

        try{
            URL url = new URL(urlStr);
            conn = (HttpURLConnection)url.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            setHttpHeader(request, conn);
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes("UTF-8"));
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            response.setStateCode(responseCode);
            response.setResponseHeader(conn.getHeaderFields());
            if (conn.getResponseCode() != HttpURLConnection.HTTP_OK)
            {
                response.setResponse("");
            }
            else
            {
                InputStream inputStream = conn.getInputStream();
                byte[] data = readInputStream(inputStream);
                response.setResponse(new String(data, "UTF-8"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
        return response;
    }
    
    public static HttpResponse get(HttpRequest request, int timeout) 
    {
        HttpURLConnection conn = null;
        String urlStr = request.getUrl();
        HttpResponse response = new HttpResponse();
        try
        {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(timeout);
            conn.setReadTimeout(timeout);

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");
            setHttpHeader(request, conn);
            
            int responseCode = conn.getResponseCode();
            response.setStateCode(responseCode);
            response.setResponseHeader(conn.getHeaderFields());
            if (responseCode != HttpURLConnection.HTTP_OK)
            {
                response.setResponse("");
            }
            else
            {
                InputStream inputStream = conn.getInputStream();
                byte[] data = readInputStream(inputStream);
                response.setResponse(new String(data, "UTF-8"));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally 
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
        return response;
    }
        
    private static byte[] readInputStream(InputStream inputStream)
    {
        ByteArrayOutputStream outstream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        try
        {
            while((len = inputStream.read(buffer)) != -1)
            {
                outstream.write(buffer, 0, len);
            }
            inputStream.close();
        }
        catch (Exception e)
        {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw, true));
        }

        return outstream.toByteArray();
    }
    
    private static void setHttpHeader(HttpRequest request, HttpURLConnection conn)
    {
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setRequestProperty("Accept", "application/json");
        String cookieStr = request.getCookieStr();
        if (!StringUtils.isEmpty(cookieStr))
        {
            conn.setRequestProperty("Cookie", cookieStr);
        }
        Map<String, String> headerMap = request.getHeaderMap();
        if (headerMap != null)
        {
            for(Map.Entry<String, String> entry : headerMap.entrySet())
            {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
    }
}
