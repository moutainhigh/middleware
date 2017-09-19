package com.emotibot.middleware.response.commonParser;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonParser
{
    @SerializedName("status_code")
    @Expose
    private int status_code = -1;
    
    @SerializedName("status_message")
    @Expose
    private String status_message = null;
    
    @SerializedName("movie_name_module")
    @Expose
    private CommonParserVideoModule movie_name_module = null;
    
    @SerializedName("teleplay_name_module")
    @Expose
    private CommonParserVideoModule teleplay_name_module = null;
    
    @SerializedName("star_name_module")
    @Expose
    private List<CommonParserStarModule> star_name_module = null;
    
    @SerializedName("volume_module")
    @Expose
    private CommonParserVolumeModule volume_module = null;
    
    public void setStatus_code(int status_code)
    {
        this.status_code = status_code;
    }
    
    public int getStatus_code()
    {
        return this.status_code;
    }
    
    public void setStatus_message(String status_message)
    {
        this.status_message = status_message;
    }
    
    public String getStatus_message()
    {
        return this.status_message;
    }
    
    public void setMovie_name_module(CommonParserVideoModule movie_name_module)
    {
        this.movie_name_module = movie_name_module;
    }
    
    public CommonParserVideoModule getMovie_name_module()
    {
        return this.movie_name_module;
    }
    
    public void setTeleplay_name_module(CommonParserVideoModule teleplay_name_module)
    {
        this.teleplay_name_module = teleplay_name_module;
    }
    
    public CommonParserVideoModule getTeleplay_name_module()
    {
        return this.teleplay_name_module;
    }
    
    public void setStar_name_module(List<CommonParserStarModule> star_name_module)
    {
        this.star_name_module = star_name_module;
    }
    
    public List<CommonParserStarModule> getStar_name_module()
    {
        return this.star_name_module;
    }
    
    public void setVolume_module(CommonParserVolumeModule volume_module)
    {
        this.volume_module = volume_module;
    }
    
    public CommonParserVolumeModule getVolume_module()
    {
        return this.volume_module;
    }
}
