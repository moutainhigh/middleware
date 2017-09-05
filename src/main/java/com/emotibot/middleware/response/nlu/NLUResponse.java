package com.emotibot.middleware.response.nlu;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import com.emotibot.middleware.constants.Constants;
import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.ResponseType;
import com.emotibot.middleware.utils.JsonUtils;
import com.emotibot.middleware.utils.StringUtils;
import com.google.gson.reflect.TypeToken;

public class NLUResponse extends AbstractResponse
{
    private List<NLU> nluList = null;
    
    public NLUResponse()
    {
        super(ResponseType.NLU);
    }
    
    @SuppressWarnings("unchecked")
    public NLUResponse(String jsonString)
    {
        super(ResponseType.NLU);
        Type resultType = new TypeToken<List<NLU>>(){}.getType();
        nluList = (List<NLU>) JsonUtils.getObject(jsonString, resultType);
    }
    
    public NLU getNLU()
    {
        if (nluList == null || nluList.isEmpty())
        {
            return null;
        }
        return nluList.get(0);
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(nluList.get(0));
    }
    
    public String getMovie()
    {
        NLU nlu = getNLU();
        if (nlu == null)
        {
            return null;
        }
        String namedEntities = nlu.getNamedEntities();
        String movie = parser(namedEntities, Constants.MOVIE_START_TAG);
        if (!StringUtils.isEmpty(movie))
        {
            return movie;
        }
        String namedEntitiesMT = nlu.getNamedEntitiesMT();
        movie = parser(namedEntitiesMT, Constants.MOVIE_START_TAG);
        return movie;
    }
    
    public String getMusic()
    {
        NLU nlu = getNLU();
        if (nlu == null)
        {
            return null;
        }
        String namedEntities = nlu.getNamedEntities();
        String music = parser(namedEntities, Constants.MUSIC_START_TAG);
        if (!StringUtils.isEmpty(music))
        {
            return music;
        }
        String namedEntitiesMT = nlu.getNamedEntitiesMT();
        music = parser(namedEntitiesMT, Constants.MUSIC_START_TAG);
        return music;
    }
    
    public String getStar()
    {
        NLU nlu = getNLU();
        if (nlu == null)
        {
            return null;
        }
        String namedEntities = nlu.getNamedEntities();
        return parser(namedEntities, Constants.PERSON_START_TAG);
    }
    
    public String getNameEntityBySRL()
    {
        NLU nlu = getNLU();
        if (nlu == null)
        {
            return null;
        }
        SRL srl = nlu.getSrl();
        if (srl == null)
        {
            return null;
        }
        int state = srl.getStatus();
        if (state != 0)
        {
            return null;
        }
        Map<String, List<SRLElement>> srlElementMap = srl.getSrl();
        if (srlElementMap == null || srlElementMap.isEmpty())
        {
            return nlu.getQuery();
        }
        List<SRLElement> srlElementList = srlElementMap.get(Constants.ATP_TAG);
        if (srlElementList == null || srlElementList.isEmpty())
        {
            return nlu.getQuery();
        }
        return parserNameEntityBySRL(srlElementList);
    }
    
    private String parser(String namedEntities, String startTag)
    {
        if (namedEntities == null || namedEntities.trim().isEmpty())
        {
            return null;
        }
        int startIndex = namedEntities.indexOf(startTag);
        if (startIndex == -1)
        {
            return null;
        }
        String tmp = namedEntities.substring(startIndex);
        int endIndex = tmp.indexOf(Constants.END_TAG);
        if (endIndex == -1)
        {
            return null;
        }
        return tmp.substring(0, endIndex);
    }
    
    /**
     * 具体逻辑如下：
     * 1. 找到最后一个元素，获取pred字段
     * 2. 从前往后，查找与最后一个元素一样的pred的元素
     * 3. 将所有pred字段一样的token连在一起
     * 
     * 这样是因为电影名称一般是位于最后一个ATP中，但是有时一个电影名称会被拆成两个元素
     * 
     * http://dev1.emotibot.com:13901/?f=srl&q=%E6%88%91%E6%83%B3%E7%9C%8B%E5%B0%8F%E8%AF%9D%E8%A5%BF%E6%B8%B8
     * 
     * 小话西游会被拆成小话和西游两个
     * 
     * @param srlElementList
     * @return
     */
    private String parserNameEntityBySRL(List<SRLElement> srlElementList)
    {
        List<Map<String, String>> tokens = this.getNLU().getSrl().getTokens();
        SRLElement srlElement = srlElementList.get(srlElementList.size() - 1);
        String pred = srlElement.getPred();
        //TODO
        StringBuilder sb = new StringBuilder();
        for(SRLElement element : srlElementList)
        {
            if (element.getPred().equals(pred))
            {
                List<String> arg2 = element.getArg2();                
                for (String token : arg2)
                {
                    for(Map<String, String> tokenMap : tokens)
                    {
                        if (tokenMap.containsKey(token))
                        {
                            sb.append(tokenMap.get(token));
                            break;
                        }
                    }
                }
            }
        }
        return sb.toString();
    }
}
