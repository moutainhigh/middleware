package com.emotibot.middleware.singleton;

import org.apache.log4j.Logger;

import com.emotibot.middleware.conf.ConfigManager;
import com.emotibot.middleware.constants.Constants;
import com.emotibot.middleware.utils.StringUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

public enum RedisSingleton 
{
    INSTANCE();

    private static Logger logger = Logger.getLogger(RedisSingleton.class);
    private RedisClient redisClient;

    RedisSingleton()
    {
        redisClient = new RedisClient();
    }

    public RedisClient getRedisClient() 
    {
        return redisClient;
    }

    public class RedisClient 
    {
        private JedisPool jedisPool;
        private boolean isReady = false;
        
        private String redisHost;
        private int redisPort;
        private int redisConnectionTimeout;
        private int redisItemTimeout;
        
        private Thread monitorThread;

        private RedisClient()
        {
            init();
            buildJedisPool();
            monitorThread = new Thread(new Runnable() {

                @Override
                public void run()
                {
                    monitorRedis();
                }
                
            });
            monitorThread.start();
        }

        public boolean isReady()
        {
            return this.isReady;
        }
        
        private void init() 
        {
            redisHost = ConfigManager.INSTANCE.getPropertyString(Constants.REDIS_HOST_KEY);
            if (StringUtils.isEmpty(redisHost))
            {
                logger.error("Can not read redis host");
                return;
            }
            
            String redisPortTmp = ConfigManager.INSTANCE.getPropertyString(Constants.REDIS_PORT_KEY);
            if (StringUtils.isEmpty(redisPortTmp))
            {
                logger.error("Can not read redis port");
                return;
            }
            redisPort = Integer.parseInt(redisPortTmp);
            
            redisConnectionTimeout = Constants.REDIS_CONNECTION_TIMEOUT_DEFAULT;
            String redisConnectionTimeoutTmp = ConfigManager.INSTANCE.getPropertyString(Constants.REDIS_CONNECTION_TIMEOUT_KEY);
            if (!StringUtils.isEmpty(redisConnectionTimeoutTmp))
            {
                redisConnectionTimeout = Integer.parseInt(redisConnectionTimeoutTmp);
            }
            
            redisItemTimeout = Constants.REDIS_ITEM_TIMEOUT_DEFAULT;
            String redisItemTimeoutTmp = ConfigManager.INSTANCE.getPropertyString(Constants.REDIS_ITEM_TIMEOUT_KEY);
            if (!StringUtils.isEmpty(redisItemTimeoutTmp))
            {
                redisItemTimeout = Integer.parseInt(redisItemTimeoutTmp);
            }
        }

        private void buildJedisPool() 
        {
            JedisPoolConfig jpc = new JedisPoolConfig();
            jpc.setMaxTotal(Constants.REDIS_MAX_CONNECTION);
            jpc.setMaxIdle(Constants.REDIS_MAX_IDLE_CONNECTION);
            jpc.setMaxWaitMillis(redisConnectionTimeout);

            jedisPool = new JedisPool(jpc, redisHost, redisPort);
        }
        
        private void closeJedisPoll()
        {
            if (jedisPool != null)
            {
                jedisPool.close();
            }
        }

        public boolean put(String key, String value) 
        {
            if (!isReady)
            {
                return false;
            }
            Jedis jedis = jedisPool.getResource();
            try
            {
                jedis.set(key, value);
                if (isRemoveTimeoutItem()) 
                {
                    jedis.expire(key.toString(), redisItemTimeout);
                }
                return true;
            }
            finally
            {
                jedis.close();
            }
        }

        public String get(String key) 
        {
            if (!isReady)
            {
                return null;
            }
            Jedis jedis = jedisPool.getResource();
            try
            {
                return jedis.get(key);
            }
            finally
            {
                jedis.close();
            }
        }
        
        public boolean delete(String key)
        {
            if (!isReady)
            {
                return false;
            }
            Jedis jedis = jedisPool.getResource();
            try
            {
                jedis.del(key);
                return true;
            }
            finally
            {
                jedis.close();
            }
        }

        public boolean isRemoveTimeoutItem() 
        {
            return redisItemTimeout >= 0;
        }
        
        private void monitorRedis()
        {
            while(true)
            {
                try
                {
                    this.put(Constants.REDIS_MONITOR_KEY, Constants.REDIS_MONITOR_VALUE);
                    isReady = true;
                    try
                    {
                        Thread.sleep(Constants.REDIS_CONNECTION_WAIT_INTERVAL);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        return;
                    }
                }
                catch (JedisConnectionException jedisConnectionException)
                {
                    jedisConnectionException.printStackTrace();
                    this.isReady = false;
                    closeJedisPoll();
                    jedisPool = null;
                    try
                    {
                        Thread.sleep(Constants.REDIS_UNCONNECTION_WAIT_INTERVAL);
                    } 
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                        return;
                    }
                    buildJedisPool();
                }
            }
        }
    }
}
