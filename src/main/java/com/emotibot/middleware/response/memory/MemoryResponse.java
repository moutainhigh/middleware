package com.emotibot.middleware.response.memory;

import java.lang.reflect.Type;
import java.util.List;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;
import com.emotibot.middleware.utils.StringUtils;
import com.google.gson.reflect.TypeToken;

public class MemoryResponse extends AbstractResponse
{
    private List<Memory> memoryList;
    
    public MemoryResponse()
    {
        super(CommonResponseType.MEMORY);
    }
    
    @SuppressWarnings("unchecked")
    public MemoryResponse(String jsonString)
    {
        super(CommonResponseType.MEMORY);
        Type resultType = new TypeToken<List<Memory>>(){}.getType();
        memoryList = (List<Memory>) JsonUtils.getObject(jsonString, resultType);
    }
    
    public List<String> getItems(String relation, String entity)
    {
        if (StringUtils.isEmpty(entity) || StringUtils.isEmpty(relation))
        {
            return null;
        }
        
        if(memoryList == null || memoryList.isEmpty())
        {
            return null;
        }
        for (Memory memory : memoryList)
        {
            Result result = memory.getResult();
            if (result == null)
            {
                continue;
            }
            List<Relations> relationsList = result.getRelation();
            if (relationsList == null || relationsList.isEmpty())
            {
                continue;
            }
            for (Relations relations : relationsList)
            {
                if (relation.equals(relations.getRelation()) && entity.equals(relations.getEntity()))
                {
                    return relations.getValue();
                }
            }
        }
        return null;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(memoryList);
    }
}
