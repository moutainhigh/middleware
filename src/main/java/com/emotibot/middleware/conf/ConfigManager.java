package com.emotibot.middleware.conf;

import java.io.FileInputStream;
import java.util.Properties;

import com.emotibot.middleware.constants.Constants;

/**
 * 从环境变量中读取配置文件所在的位置，之后加载配置文件
 * 
 * 优先输出配置文件中的内容，如果找不到，输出环境变量中的内容
 * 
 * 返回值提供boolean，int，string类型
 * 
 * @author emotibot
 *
 */
public enum ConfigManager
{
    INSTANCE();
    
    private final Properties properties;
    
    ConfigManager()
    {
        properties = new Properties();
        String configFilePath = getPropertyFromSystemEnv(Constants.CONFIG_FILE_PATH_KEY);
        if (configFilePath == null || configFilePath.trim().isEmpty())
        {
            return;
        }
        try
        {
            properties.load(new FileInputStream(configFilePath));
        } 
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    
    public String getPropertyString(String key)
    {
        return getProperty1(key);
    }
    
    public boolean getPropertyBoolean(String key)
    {
        String ret = getProperty1(key);
        return Boolean.parseBoolean(ret);
    }
    
    public int getPropertyInt(String key)
    {
        String ret = getProperty1(key);
        return Integer.parseInt(ret);
    }
    
    private String getProperty1(String key)
    {
        String ret = getPropertyFromPropertyFile(key);
        if (ret == null || ret.trim().isEmpty())
        {
            ret = getPropertyFromSystemEnv(key);
        }
        return ret;
    }
    
    private String getPropertyFromSystemEnv(String key)
    {
        return System.getProperty(key);
    }
    
    private String getPropertyFromPropertyFile(String key)
    {
        return properties.getProperty(key);
    }
}
