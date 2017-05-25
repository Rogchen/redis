package com.rogchen.common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.util.Assert;

public class EhCacheManagerAdvice extends EhCacheCacheManager implements SystemCacheManager {

    private static final Logger logger = LoggerFactory.getLogger(EhCacheManagerAdvice.class);

    @Override
    public void put(String group, String key, Object value) {
        Assert.notNull(key, "缓存key不能为空，缓存" + group);
        Cache cache = getEhCache(group);

        if(cache == null){
            return ;
        }

        cache.put(key, value);
        //logger.info("缓存新增修改操作：缓存={}，key={},value={}", new Object[]{group, key, value});
    }

    @Override
    public Object get(String group, String key) {
        Assert.notNull(key, "缓存key不能为空，缓存" + group);
        Cache cache = getEhCache(group);

        if(cache == null){
            return null;
        }

        Cache.ValueWrapper valueWrapper = cache.get(key);
        if(valueWrapper == null){
            return null;
        }

        return valueWrapper.get();
    }

    @Override
    public void remove(String group, String key) {
        Assert.notNull(key, "缓存key不能为空，缓存" + group);
        Cache cache = getEhCache(group);

        if(cache == null){
            return ;
        }

        cache.evict(key);
        logger.info("缓存移除操作：缓存={}，key={}", new Object[]{group, key});
    }

    @Override
    public void update(String group, String key, Object value) {
        //
    }

    @Override
    public void refresh(String group) {
        Cache cache = getEhCache(group);

        if(cache == null){
            return ;
        }

        cache.clear();
        logger.warn("清除缓存信息，缓存名称{}", group);
    }

    private Cache getEhCache(String group) {
        Assert.hasText(group, "缓存的名称不能为空！");
        Cache cache = super.getCache(group);
        Assert.notNull(cache, "在Ehcache中找不到缓存名称为" + group);
        return cache;
    }
}
