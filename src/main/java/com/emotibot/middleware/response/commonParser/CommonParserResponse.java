package com.emotibot.middleware.response.commonParser;

import com.emotibot.middleware.response.AbstractResponse;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.utils.JsonUtils;

public class CommonParserResponse extends AbstractResponse
{

    private CommonParser commonParser = null;
    
    public CommonParserResponse()
    {
        super(CommonResponseType.COMMON_PARSER);
    }
    
    public CommonParserResponse(String jsonString)
    {
        super(CommonResponseType.COMMON_PARSER);
        commonParser = (CommonParser) JsonUtils.getObject(jsonString, CommonParser.class);
    }

    public CommonParser getCommonParser()
    {
        return this.commonParser;
    }
    
    @Override
    public String toString()
    {
        return JsonUtils.getJsonStr(commonParser);
    }
    
    public String getMovie()
    {
        if (commonParser == null)
        {
            return null;
        }
        if (commonParser.getMovie_name_module() == null)
        {
            return null;
        }
        return commonParser.getMovie_name_module().getName();
    }
    
    public String getTeleplay()
    {
        if (commonParser == null)
        {
            return null;
        }
        if (commonParser.getTeleplay_name_module() == null)
        {
            return null;
        }
        return commonParser.getTeleplay_name_module().getName();
    }
    
    public String getStar()
    {
        if (commonParser == null)
        {
            return null;
        }
        if (commonParser.getStar_name_module() == null || commonParser.getStar_name_module().isEmpty())
        {
            return null;
        }
        CommonParserStarModule starModule = commonParser.getStar_name_module().get(0);
        if (starModule == null)
        {
            return null;
        }
        if (starModule.getProfession().trim().equals(""))
        {
            return null;
        }
        return starModule.getName();
    }
}
