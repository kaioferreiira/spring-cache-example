package com.example.caching.application.cacheConfig;

import net.sf.ehcache.config.CacheConfiguration;
import net.sf.ehcache.store.MemoryStoreEvictionPolicy;
import org.springframework.beans.factory.annotation.Qualifier;
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

	@Value("${cache.timeToLiveSeconds.token}")
	private String timeToLiveSecondsToken;

	@Bean
	@Qualifier(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE)
	@Override
	public CacheManager cacheManager() {
		return new EhCacheCacheManager(ehCacheManager(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE,Long.parseLong(timeToLiveSecondsToken)));
	}

	public net.sf.ehcache.CacheManager ehCacheManager(String ehCacheManager, Long timeToLiveSeconds) {

		CacheConfiguration testEhCacheConfig = new CacheConfiguration()
				.eternal(false)                     // if true, timeouts are ignored
//				.timeToIdleSeconds(4)               // time since last accessed before item is marked for removal
				.timeToLiveSeconds(timeToLiveSeconds)               // time since inserted before item is marked for removal
				.maxEntriesLocalHeap(1)            // total items that can be stored in cache, max 200
				.memoryStoreEvictionPolicy(MemoryStoreEvictionPolicy.LRU)   // eviction policy for when items exceed cache. LRU = Least Recently Used
				.name(ehCacheManager);

		net.sf.ehcache.config.Configuration config = new net.sf.ehcache.config.Configuration();
		config.addCache(testEhCacheConfig);

		return net.sf.ehcache.CacheManager.newInstance(config);
	}


}