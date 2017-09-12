package com.emotibot.middleware.step;

import java.util.List;

import org.junit.Test;

import com.emotibot.middleware.context.Context;
import com.emotibot.middleware.request.HttpRequest;
import com.emotibot.middleware.request.HttpRequestType;
import com.emotibot.middleware.response.CommonResponseType;
import com.emotibot.middleware.response.Response;
import com.emotibot.middleware.task.restCallTask.IntentTask;

public class StepTest
{

    private static final String intentUrl = "http://172.16.101.61:14399/intent?sentence=";
    private static final String sentence = "我要看周杰伦的电影";
    
    class MyStep extends AbstractStep
    {

        List<Response> responseList = null;
        
        @Override
        public void beforeRun(Context context)
        {
            IntentTask intentTask = new IntentTask();
            HttpRequest request = new HttpRequest(intentUrl + sentence, null, HttpRequestType.GET);
            intentTask.setRequest(request);
            context.addTask(intentTask);
            context.addTask(intentTask);
            context.addTask(intentTask);
            context.addTask(intentTask);
            context.addTask(intentTask);
        }

        @Override
        public void afterRun(Context context)
        {
            responseList = context.getOutputMap().get(CommonResponseType.INTENT);
        }
        
        public List<Response> getResponseList()
        {
            return this.responseList;
        }
        
    }
    
    @Test
    public void test()
    {
        Context context = new Context();
        MyStep myStep = new MyStep();
        myStep.execute(context);
        List<Response> responseList = myStep.getResponseList();
        
        long startTime = System.currentTimeMillis();
        myStep = new MyStep();
        myStep.execute(context);
        long endTime = System.currentTimeMillis();
        System.out.println("用时: " + (endTime - startTime) + "ms");
        System.out.println(responseList);
    }

}
