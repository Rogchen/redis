package com.rogchen.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author yuchx on 2016/7/25
 */
public class RedisCacheUtil {
    private static final Logger logger    = LoggerFactory.getLogger(RedisCacheUtil.class);

    private static JedisPool jedisPool = null;

    /*@Value("#{redisProperties['redis.host']}")
    private static String       redisHost;

    @Value("#{redisProperties['redis.port']}")
    private static Integer      redisPort;

    @Value("#{redisProperties['redis.maxTotal']}")
    private static Integer      redisMaxTotal;

    @Value("#{redisProperties['redis.maxIdle']}")
    private static Integer      redisMaxIdle;

    @Value("#{redisProperties['redis.maxWaitMillis']}")
    private static Integer      redisMaxWaitMillis;

    @Value("#{redisProperties['redis.timeout']}")
    private static Integer      redisTimeout;

    @Value("#{redisProperties['redis.testOnBorrow']}")
    private static Boolean      redisTestOnBorrow;*/

    /*static {
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxTotal(redisMaxTotal);
            // config.setMaxActive(MAX_ACTIVE);
            config.setMaxIdle(redisMaxIdle);
            // config.setMaxWait(MAX_WAIT);
            config.setTestOnBorrow(redisTestOnBorrow);
            jedisPool = new JedisPool(config, redisHost, redisPort, redisTimeout);
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("启动 jedisPool 异常:" + e.getMessage());
        }
    }*/

    public synchronized static Jedis getJedis () {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            }
            else {
                logger.error("jedisPool 未启动");
                return null;
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            logger.error("jedisPool 未启动");
            return null;
        }
    }

    public static void set (String key, String value) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().set(key, value);
    }

    public static String get (String key) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().get(key);
    }

    public static void append (String key, String value) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().append(key, value);
    }

    public static void del (String key) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().del(key);
    }

    // 进行加1操作
    public static void incr (String key) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().incr(key);
    }

    // 存入 map
    public static void hmset (String key, Map<String, String> dataMap) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().hmset(key, dataMap);
    }

    // 删除map中的某个键值
    public static void hdel (String key, String... fields) {
        Assert.notNull(key, "缓存key不能为空");
        getJedis().hdel(key, fields);
    }

    // 第一个参数是存入redis中map对象的key，后面跟的是放入map中的对象的key，后面的key可以跟多个，是可变参数
    public List<String> hmget (String key, String... fields) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().hmget(key, fields);
    }

    // 返回key的键中存放的值的个数
    public Long hlen (String key) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().hlen(key);
    }

    // 是否存在key的记录
    public Boolean exists (String key) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().exists(key);
    }

    // 返回map对象中的所有key
    public Set<String> hkeys(String key) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().hkeys(key);
    }

    // 返回map对象中的所有value
    public List<String> hvals(String key) {
        Assert.notNull(key, "缓存key不能为空");
        return getJedis().hvals(key);
    }

}
