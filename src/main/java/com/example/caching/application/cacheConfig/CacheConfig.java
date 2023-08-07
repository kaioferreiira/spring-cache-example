package com.example.caching.application.cacheConfig;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {

    @Value("${cache.token1.timeToLiveSeconds}")
    private long timeToLiveSecondsToken1;

    @Value("${cache.token1.maxElementEntries}")
    private int maxElementEntriesoken1;

    @Value("${cache.token2.timeToLiveSeconds}")
    private long timeToLiveSecondsToken2;

    @Value("${cache.token2.maxElementEntries}")
    private int maxElementEntriesToken2;


    @Bean
    public CaffeineCacheManager cacheManager() {

        CaffeineCacheManager cacheManager = new CaffeineCacheManager();
        cacheManager.registerCustomCache(CacheManagerNames.TOKEN_1, caffeineCacheBuilder(timeToLiveSecondsToken1, maxElementEntriesoken1));
        cacheManager.registerCustomCache(CacheManagerNames.TOKEN_2, caffeineCacheBuilder(timeToLiveSecondsToken2, maxElementEntriesToken2));

        return cacheManager;
    }

    Cache<Object, Object> caffeineCacheBuilder(Long duration, int maxElements ) {

        Cache<Object, Object> build = Caffeine.newBuilder()
                .maximumSize(maxElements) //  Define o tamanho máximo do cache, ou seja, o número máximo de entradas que o cache pode armazenar. Uma vez que o limite de tamanho seja atingido, as entradas mais antigas podem ser removidas com base nas políticas de evicção.
                .expireAfterAccess(duration, TimeUnit.SECONDS)
                .weakKeys()
                .recordStats()
                .build();

        return build;
    }







//    @Bean
//    @Override
//    public CacheManager cacheManager() {
//        return new EhCacheCacheManager(ehCacheManager());
//    }
//
//    @Bean
//    public net.sf.ehcache.CacheManager ehCacheManager() {
//
//        buildCacheConfiguration(CacheManagerNames.TOKEN_1, timeToLiveSecondsToken1, maxElementEntriesoken1);
//
//        buildCacheConfiguration(CacheManagerNames.TOKEN_2, timeToLiveSecondsToken2, maxElementEntriesToken2);
//
//        return net.sf.ehcache.CacheManager.newInstance(CONFIGURATION);
//    }
//
//    private static void buildCacheConfiguration(String nameCache, long timeToLiveSeconds, int maxElementEntries) {
//
//        var cacheConfiguration = new CacheConfiguration()
//                .eternal(false)                     // if true, timeouts are ignored
////				.timeToIdleSeconds(4)               // time since last accessed before item is marked for removal
//                .timeToLiveSeconds(timeToLiveSeconds)               // time since inserted before item is marked for removal
//                .maxEntriesLocalHeap(maxElementEntries)            // total items that can be stored in cache, max 200
//                .memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)   // eviction policy for when items exceed cache. LRU = Least Recently Used
//                .name(nameCache);
//
//        CONFIGURATION.addCache(cacheConfiguration);
//    }


}