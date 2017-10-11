package com.emotibot.middleware.singleton;

import org.apache.log4j.Logger;
import org.bson.Document;

import com.emotibot.middleware.conf.ConfigManager;
import com.emotibot.middleware.constants.Constants;
import com.emotibot.middleware.utils.MongoUtils;
import com.emotibot.middleware.utils.StringUtils;
import com.google.gson.JsonObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoTimeoutException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public enum MongoSingleton
{
    INSTANCE();
    
    private static Logger logger = Logger.getLogger(MongoSingleton.class);
    private MongoAgent mongoAgent;
    
    MongoSingleton()
    {
        mongoAgent = new MongoAgent();
    }
    
    public MongoAgent getMongoAgent()
    {
        return this.mongoAgent;
    }
    
    public class MongoAgent
    {
        private String mongoHost;
        private int mongoPort;
        private MongoClient mongoClient;
        private MongoCollection<Document> mongoTestCollection;
        private JsonObject testObj;
        
        private boolean isReady = false;
        private Thread monitorThread;
        
        private MongoAgent()
        {
            init();
            buildMongoAgent();
            monitorThread = new Thread(new Runnable(){

                @Override
                public void run()
                {
                    monitorMongo();
                }
                
            });
            monitorThread.start();
        }
        
        private void init()
        {
            mongoHost = ConfigManager.INSTANCE.getPropertyString(Constants.MONGO_HOST_KEY);
            if (StringUtils.isEmpty(mongoHost))
            {
                logger.error("can not read mongo host");
                return;
            }
            
            String mongoPortTmp = ConfigManager.INSTANCE.getPropertyString(Constants.MONGO_PORT_KEY);
            if (StringUtils.isEmpty(mongoPortTmp))
            {
                logger.error("can not read mongo port");
                return;
            }
            mongoPort = Integer.parseInt(mongoPortTmp);
        }
        
        private void buildMongoAgent()
        {
            mongoClient = MongoUtils.createMongoClient(mongoHost, mongoPort);
            MongoDatabase mongoDatabase = MongoUtils.getOrCreateMongoDatabase(mongoClient, Constants.MONGO_MONITOR_DATABASE);
            mongoTestCollection = MongoUtils.getOrCreateCollection(mongoDatabase, Constants.MONGO_MONITOR_COLLECTION);
            testObj = new JsonObject();
            testObj.addProperty(Constants.MONGO_MONITOR_DOC_KEY, Constants.MONGO_MONITOR_DOC_VALUE);
        }
        
        private void monitorMongo()
        {
            while(true)
            {
                try
                {
                    MongoUtils.insertJsonObject(mongoTestCollection, testObj);
                    MongoUtils.deleteJsonObject(mongoTestCollection, Constants.MONGO_MONITOR_DOC_KEY, Constants.MONGO_MONITOR_DOC_VALUE);
                    isReady = true;
                    try
                    {
                        Thread.sleep(Constants.MONGO_CONNECTION_WAIT_INTERVAL);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        return;
                    }
                }
                catch(MongoTimeoutException e)
                {
                    isReady = false;
                    try
                    {
                        Thread.sleep(Constants.MONGO_UNCONNECTION_WAIT_INTERVAL);
                    } 
                    catch (InterruptedException e1)
                    {
                        e.printStackTrace();
                        return;
                    }
                }
            }
        }
        
        public MongoClient getMongoClient()
        {
            return this.mongoClient;
        }
        
        public boolean isReady()
        {
            return this.isReady;
        }
    }
}
