package com.emotibot.middleware.controller.controller;

/**
 * 
 * 这个是不需要重写的
 * 
 * @author emotibot
 *
 */

public class AbstractStepController implements StepController
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
