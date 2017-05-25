package com.rogchen.common.cache;


import com.rogchen.common.utils.spring.SpringBeanHolder;

/**
 * Class description
 *
 * @author yuchx on 2016/6/6
 */
public class CacheUtil {
    private static SystemCacheManager cacheManager = ((SystemCacheManager) SpringBeanHolder.getBean("ehCacheManagerAdvice"));

    private static final String SYS_CACHE = "sysCache";



    /**
     * 获取SYS_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return cacheManager.get(SYS_CACHE, key);
    }

    /**
     * 写入SYS_CACHE缓存
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        cacheManager.put(SYS_CACHE, key, value);
    }

    /**
     * 从SYS_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        cacheManager.remove(SYS_CACHE, key);
    }

    /**
     * 获取缓存
     * @param cacheName
     * @param key
     * @return
     */
    public static Object get(String cacheName, String key) {
        return cacheManager.get(cacheName, key);
    }

    /**
     * 写入缓存
     * @param cacheName
     * @param key
     * @param value
     */
    public static void put(String cacheName, String key, Object value) {
        cacheManager.put(cacheName, key, value);
    }

    /**
     * 从缓存中移除
     * @param cacheName
     * @param key
     */
    public static void remove(String cacheName, String key) {
        cacheManager.remove(cacheName, key);
    }

}
