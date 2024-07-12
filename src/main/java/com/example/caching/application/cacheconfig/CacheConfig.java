package com.example.caching.application.cacheconfig;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

    @Value("${cache.token1.timeToLiveSeconds}")
    private long timeToLiveSecondsToken1;

    @Value("${cache.token1.maxElementEntries}")
    private int maxElementEntriesoken1;

    @Value("${cache.token2.timeToLiveSeconds}")
    private long timeToLiveSecondsToken2;

    @Value("${cache.token2.maxElementEntries}")
    private int maxElementEntriesToken2;

    @Bean
    public CacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();

        cacheManager.registerCustomCache(CacheManagerNames.CACHE_NAME_SERVICE_1,
                newCacheBuilder(timeToLiveSecondsToken1, maxElementEntriesoken1, TimeUnit.SECONDS));

        cacheManager.registerCustomCache(CacheManagerNames.CACHE_NAME_SERVICE_2,
                newCacheBuilder(timeToLiveSecondsToken2, maxElementEntriesToken2, TimeUnit.SECONDS));


        return cacheManager;
    }

    Cache<Object, Object> newCacheBuilder(Long duration, int maxElements, TimeUnit timeUnit) {
        return Caffeine.newBuilder()
                .maximumSize(maxElements)
                .expireAfterWrite(duration, timeUnit)
                .build();
    }

}
