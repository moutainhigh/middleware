package com.emotibot.middleware.step;

import java.util.List;

import org.junit.Test;

import com.emotibot.middleware.request.HttpRequest;
import com.emotibot.middleware.request.HttpRequestType;
import com.emotibot.middleware.response.Response;
import com.emotibot.middleware.response.ResponseType;
import com.emotibot.middleware.task.restCallTask.IntentTask;

public class StepTest
{

    private static final String intentUrl = "http://172.16.101.61:14399/intent?sentence=";
    private static final String sentence = "我要看周杰伦的电影";
    
    class MyStep extends AbstractStep
    {

        List<Response> responseList = null;
        
        @Override
        public void beforeRun()
        {
            IntentTask intentTask = new IntentTask();
            HttpRequest request = new HttpRequest(intentUrl + sentence, null, HttpRequestType.GET);
            intentTask.setRequest(request);
            this.addTask(intentTask);
            this.addTask(intentTask);
            this.addTask(intentTask);
            this.addTask(intentTask);
            this.addTask(intentTask);
        }

        @Override
        public void afterRun()
        {
            responseList = this.outputMap.get(ResponseType.INTENT);
        }
        
        public List<Response> getResponseList()
        {
            return this.responseList;
        }
        
    }
    
    @Test
    public void test()
    {
        MyStep myStep = new MyStep();
        myStep.execute();
        List<Response> responseList = myStep.getResponseList();
        
        long startTime = System.currentTimeMillis();
        myStep = new MyStep();
        myStep.execute();
        long endTime = System.currentTimeMillis();
        System.out.println("用时: " + (endTime - startTime) + "ms");
        System.out.println(responseList);
    }

}
