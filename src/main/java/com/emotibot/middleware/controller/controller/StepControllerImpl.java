package com.emotibot.middleware.controller.controller;

/**
 * 
 * Controller会将输入的json写入到context中一个特定的字段中，input会默认从该字段中读取context，在input的实现中操作该字段
 * 
 * @author emotibot
 *
 */

public class StepControllerImpl implements StepController
{

    private String controllerName;
    
    @Override
    public void loadConfig(String filePath)
    {
        // TODO Auto-generated method stub
        
    }

    @Override
    public String getControllerName()
    {
        return this.controllerName;
    }

    @Override
    public void execute(String jsonStr)
    {
        // TODO Auto-generated method stub
        
    }
    
}
