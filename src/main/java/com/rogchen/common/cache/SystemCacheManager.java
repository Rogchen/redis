package com.rogchen.common.cache;

import org.springframework.cache.CacheManager;

public interface SystemCacheManager extends CacheManager {

    /**
     * 加入到指定的缓存
     *
     * @param group
     * @param key
     * @param value
     */
    public void put(String group, String key, Object value);

    /**
     * 从指定的缓存中获取值
     *
     * @param group
     * @param key
     * @return
     */
    public Object get(String group, String key);

    /**
     * 从指定的缓存中删除
     *
     * @param group
     * @param key
     */
    public void remove(String group, String key);

    /**
     * 更新指定缓存值
     *
     * @param group
     * @param key
     * @param value
     */
    public void update(String group, String key, Object value);

    /**
     * 刷新缓存，相当于清空缓存
     *
     * @param group
     */
    public void refresh(String group);
}
