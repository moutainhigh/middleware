package com.emotibot.middleware.controller.controller;

/**
 * 
 * 读取配置文件（json），生成相应的对象：输入单元，执行单元（多个），判断单元（多个），输出单元（包含异常输出）
 * 
 * 同时会将每个单元的配置文件给到该单元，由该单元来生成相应的step对象或者判断逻辑，将其组合成list，逐一执行，
 * 如果中途退出，就转到输出单元。
 * 
 * 这里的输入和输出单元都可以通过配置来做，输入单元的输入是jsonString，我们可以配置读取哪些字段到context中
 * 输出单元，是将context中的内容映射成一个jsonString输出
 * 
 * 执行单元，要生成各个step对象，并进行调用
 * 
 * 判断单元，判断context中元素是否满足输出条件
 * 
 * 这个可以作为一个service来提供服务（不同的配置可以作为不同的service）
 * 
 * StepController只生成模块
 * 
 * @author emotibot
 *
 */

public interface StepController
{
    public void loadConfig(String filePath);
    
    public String getControllerName();
    
    public void execute(String jsonStr);
}
