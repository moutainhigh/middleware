package com.emotibot.middleware.response.commonParser;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommonParserVolumeModule
{
    @SerializedName("on_off")
    @Expose
    private String on_off;
    
    @SerializedName("volume")
    @Expose
    private int volume = Integer.MIN_VALUE;
    
    @SerializedName("volume_incremental")
    @Expose
    private int volume_incremental = Integer.MIN_VALUE;
    
    public void setOn_off(String on_off)
    {
        this.on_off = on_off;
    }
    
    public String getOn_off()
    {
        return this.on_off;
    }
    
    public void setVolume(int volume)
    {
        this.volume = volume;
    }
    
    public int getVolume()
    {
        return this.volume;
    }
    
    public void setVolume_incremental(int volume_incremental)
    {
        this.volume_incremental = volume_incremental;
    }
    
    public int getVolume_incremental()
    {
        return this.volume_incremental;
    }
}
