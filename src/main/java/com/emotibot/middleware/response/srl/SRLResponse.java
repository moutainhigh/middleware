package com.emotibot.middleware.response.srl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.response.nlu.SRL;
import com.emotibot.middleware.response.nlu.SRLElement;
import com.emotibot.middleware.utils.JsonUtils;
import com.emotibot.middleware.utils.StringUtils;

public class SRLResponse extends AbstractResponse
{

    private SRL srl;
    
    public SRLResponse()
    {
        super(CommonResponseType.SRL);
    }
    
    public SRLResponse(String jsonString)
    {
        super(CommonResponseType.SRL);
        srl = (SRL) JsonUtils.getObject(jsonString, SRL.class);
    }

    public SRL getSrl()
    {
        return this.srl;
    }
    
    /**
     * 找到所有的动词，以及该动词对应的内容（ATP, patient）
     * 
     * @return
     */
    public Map<Token, List<Token>> getVerbToWordListMap()
    {
        if (srl == null)
        {
            return null;
        }
        Map<String, List<SRLElement>> srlMap = srl.getSrl();
        if (srlMap == null)
        {
            return null;
        }
        //这里需要一个先后顺序
        
        List<SRLElement> srlEleList = srlMap.get("ATP");
        Map<Token, List<Token>> ret = getVerbToWordListMap(srlEleList);
        
        if (ret != null && !ret.isEmpty())
        {
            return ret;
        }
        
        srlEleList = srlMap.get("patient");
        ret = getVerbToWordListMap(srlEleList);
        if (ret != null && !ret.isEmpty())
        {
            return ret;
        }
        
        srlEleList = srlMap.get("agent");
        ret = getVerbToWordListMap(srlEleList);
        return ret;
    }
    
    private Map<Token, List<Token>> getVerbToWordListMap(List<SRLElement> srlEleList)
    {
        if (srlEleList == null || srlEleList.isEmpty())
        {
            return null;
        }
        Map<Token, List<Token>> ret = new HashMap<Token, List<Token>>();
        for (SRLElement ele : srlEleList)
        {
            Token verbToken = getTokenByIndex(ele.getArg1());
            List<String> wordIndexList = ele.getArg2();
            List<Token> wordList = ret.get(verbToken);
            if (wordList == null)
            {
                wordList = new ArrayList<Token>();
                ret.put(verbToken, wordList);
            }
            for (String index : wordIndexList)
            {
                Token word = getTokenByIndex(index);
                wordList.add(word);
            }
        }
        return ret;
    }
    
    private Token getTokenByIndex(String index)
    {
        if (srl == null || StringUtils.isEmpty(index))
        {
            return null;
        }
        List<Map<String, String>> tokens = srl.getTokens();
        if (tokens == null)
        {
            return null;
        }
        String tokenWord = null;
        for (Map<String, String> tokenMap : tokens)
        {
            if (tokenMap.containsKey(index))
            {
                tokenWord = tokenMap.get(index);
                break;
            }
        }
        if (StringUtils.isEmpty(tokenWord))
        {
            return null;
        }
        
        int tokenIndex = Integer.parseInt(index);
        int tokenIndexInText = 0;
        for (int i = 1; i < tokenIndex; i ++)
        {
            for (Map<String, String> tokenMap : tokens)
            {
                if (tokenMap.containsKey(String.valueOf(i)))
                {
                    String wordTmp = tokenMap.get(String.valueOf(i));
                    tokenIndexInText += wordTmp.length();
                    break;
                }
            }
        }
        
        return new Token(tokenIndexInText, tokenWord);
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(srl);
    }
    
}
