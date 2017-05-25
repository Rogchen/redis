package com.rogchen.common.cache;

/**
 * Class description
 *
 * @author yuchx on 2016/11/9
 */
public class AreaCacheUtil {

    private static final String AREA_CACHE = "areaCache";

    /**
     * 获取AREA_CACHE缓存
     * 
     * @param key
     * @return
     */
    public static Object get (String key) {
        return CacheUtil.get(AREA_CACHE, key);
    }

    /**
     * 写入AREA_CACHE缓存
     * 
     * @param key
     * @return
     */
    public static void put (String key, Object value) {
        CacheUtil.put(AREA_CACHE, key, value);
    }

    /**
     * 从AREA_CACHE缓存中移除
     * 
     * @param key
     * @return
     */
    public static void remove (String key) {
        CacheUtil.remove(AREA_CACHE, key);
    }
}
