package com.emotibot.middleware.utils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class RedisUtils
{    
    public static String get(JedisPool jedisPool, String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            jedis.get(key);
            return jedis.get(key);
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    public static boolean set(JedisPool jedisPool, String key, String value)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            jedis.set(key, value);
            return true;
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    public static boolean set(JedisPool jedisPool, String key, String value, int timeout)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            jedis.set(key, value);
            jedis.expire(key.toString(), timeout);
            return true;
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
    
    public static boolean delete(JedisPool jedisPool, String key)
    {
        Jedis jedis = jedisPool.getResource();
        try
        {
            jedis.del(key);
            return true;
        }
        finally
        {
            if (jedis != null)
            {
                jedis.close();
            }
        }
    }
}
