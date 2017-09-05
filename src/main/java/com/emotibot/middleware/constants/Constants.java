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
}
