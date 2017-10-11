package com.emotibot.middleware.constants;

public class Constants
{
    //RestCallTask
    public static final int HTTP_TIMEOUT = 60000;
    
    //ConfigManager
    public static final String CONFIG_FILE_PATH_KEY = "CONFIG_PATH";
    
    //AbstractTask
    public static final int THREAD_POOL_NUM = 5;
    public static final int STEP_TIMEOUT = 500;
    
    //NLU
    public static final String PERSON_START_TAG = "<START:PER>";
    public static final String MOVIE_START_TAG = "<START:MOVIE>";
    public static final String MUSIC_START_TAG = "<START:MUSIC>";
    public static final String END_TAG = "<END>";
    public static final String ATP_TAG = "ATP";
    public static final String PATIENT_TAG = "patient";
    
    //REDIS
    public static final String REDIS_HOST_KEY = "REDIS_HOST";
    public static final String REDIS_PORT_KEY = "REDIS_PORT";
    public static final String REDIS_CONNECTION_TIMEOUT_KEY = "REDIS_CONNECTION_TIMEOUT";
    public static final String REDIS_ITEM_TIMEOUT_KEY = "REDIS_ITEM_TIMEOUT";
    
    public static final int REDIS_CONNECTION_TIMEOUT_DEFAULT = 20;
    public static final int REDIS_ITEM_TIMEOUT_DEFAULT = -1;
    public static final int REDIS_MAX_CONNECTION = 100;
    public static final int REDIS_MAX_IDLE_CONNECTION = 10;
    public static final int REDIS_UNCONNECTION_WAIT_INTERVAL = 5000;
    public static final int REDIS_CONNECTION_WAIT_INTERVAL = 5000;
    
    public static final String REDIS_MONITOR_KEY = "redis_test_key";
    public static final String REDIS_MONITOR_VALUE = "redis_test_value";
    
    //MONGO
    public static final String MONGO_HOST_KEY = "MONGO_HOST";
    public static final String MONGO_PORT_KEY = "MONGO_PORT";
    public static final String MONGO_MONITOR_DATABASE = "mongo_monitor_database";
    public static final String MONGO_MONITOR_COLLECTION = "mongo_monitor_collection";
    
    public static final String MONGO_MONITOR_DOC_KEY = "key";
    public static final String MONGO_MONITOR_DOC_VALUE = "test";
    public static final int MONGO_UNCONNECTION_WAIT_INTERVAL = 5000;
    public static final int MONGO_CONNECTION_WAIT_INTERVAL = 5000;
    
}
