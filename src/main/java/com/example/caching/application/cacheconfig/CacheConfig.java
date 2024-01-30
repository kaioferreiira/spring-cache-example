package com.example.caching.application.cacheconfig;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {
    private static final net.sf.ehcache.config.Configuration CONFIGURATION = new net.sf.ehcache.config.Configuration();

    @Value("${cache.token1.timeToLiveSeconds}")
    private long timeToLiveSecondsToken1;

    @Value("${cache.token1.maxElementEntries}")
    private int maxElementEntriesoken1;

    @Value("${cache.token2.timeToLiveSeconds}")
    private long timeToLiveSecondsToken2;

    @Value("${cache.token2.maxElementEntries}")
    private int maxElementEntriesToken2;

    @Bean
    @Override
    public CacheManager cacheManager() {
        return new EhCacheCacheManager(ehCacheManager());
    }

    @Bean
    public net.sf.ehcache.CacheManager ehCacheManager() {

        buildCacheConfiguration(CacheManagerNames.CACHE_NAME_SERVICE_1, timeToLiveSecondsToken1, maxElementEntriesoken1);

        buildCacheConfiguration(CacheManagerNames.CACHE_NAME_SERVICE_2, timeToLiveSecondsToken2, maxElementEntriesToken2);

        return net.sf.ehcache.CacheManager.newInstance(CONFIGURATION);
    }

    private static void buildCacheConfiguration(String nameCache, long timeToLiveSeconds, int maxElementEntries) {

        var cacheConfiguration = new CacheConfiguration()
                .eternal(false)                     // if true, timeouts are ignored
//				.timeToIdleSeconds(4)               // time since last accessed before item is marked for removal
                .timeToLiveSeconds(timeToLiveSeconds)               // time since inserted before item is marked for removal
                .maxEntriesLocalHeap(maxElementEntries)            // total items that can be stored in cache, max 200
                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)   // eviction policy for when items exceed cache. LRU = Least Recently Used
                .name(nameCache);

        CONFIGURATION.addCache(cacheConfiguration);
    }


}