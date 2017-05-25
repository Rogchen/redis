package com.rogchen.common.cache;


import com.rogchen.common.utils.spring.SpringBeanHolder;

/**
 * Class description
 *
 * @author wdm on 2016/9/12
 */
public class DecorateCacheUtil {
    private static SystemCacheManager cacheManager = ((SystemCacheManager) SpringBeanHolder.getBean("ehCacheManagerAdvice"));

    private static final String DECORATE_CACHE = "decorateCache";



    /**
     * 获取DECORATE_CACHE缓存
     * @param key
     * @return
     */
    public static Object get(String key) {
        return cacheManager.get(DECORATE_CACHE, key);
    }

    /**
     * 写入DECORATE_CACHE缓存
     * @param key
     * @return
     */
    public static void put(String key, Object value) {
        cacheManager.put(DECORATE_CACHE, key, value);
    }

    /**
     * 从DECORATE_CACHE缓存中移除
     * @param key
     * @return
     */
    public static void remove(String key) {
        cacheManager.remove(DECORATE_CACHE, key);
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
