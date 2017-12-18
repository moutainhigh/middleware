package com.emotibot.middleware.response.srl;

import com.emotibot.middleware.utils.JsonUtils;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token implements Comparable<Token>
{
    @SerializedName("index")
    @Expose
    private int index;
    
    @SerializedName("word")
    @Expose
    private String word;
    
    public Token()
    {
        
    }
    
    public Token(int index, String word)
    {
        this.index = index;
        this.word = word;
    }
    
    public void setIndex(int index)
    {
        this.index = index;
    }
    
    public int getIndex()
    {
        return this.index;
    }
    
    public void setWord(String word)
    {
        this.word = word;
    }
    
    public String getWord()
    {
        return this.word;
    }
    
    @Override
    public int hashCode()
    {
        return JsonUtils.getJsonStr(this).hashCode();
    }
    
    @Override
    public boolean equals(Object other)
    {
        if (other == null || !(other instanceof Token))
        {
            return false;
        }
        Token otherToken = (Token) other;
        
        if (this.toString().equals(otherToken.toString()))
        {
            return true;
        }
        return false;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(this);
    }

    /**
     * 倒序
     */
    @Override
    public int compareTo(Token other)
    {
        if (index > other.index)
        {
            return 1;
        }
        else if (index < other.index)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
