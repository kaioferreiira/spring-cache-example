//package com.example.caching.business.service;
//
//import com.example.caching.application.cacheConfig.CacheManagerNames;
//import com.example.caching.business.service.dto.TokenResponse;
//import net.sf.ehcache.Cache;
//import net.sf.ehcache.Element;
//import org.junit.jupiter.api.Test;
//import org.springframework.cache.CacheManager;
//import org.junit.jupiter.api.BeforeEach;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cache.ehcache.EhCacheCacheManager;
//import org.springframework.test.context.TestPropertySource;
//
//import java.util.List;
//import java.util.Objects;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@TestPropertySource(properties = {"spring.cache.type=ehcache"})
//public class TokenServiceTest {
//    private Cache cache;
//
//    @Autowired
//    private CacheManager cacheManager;
//
//    @Autowired
//    private TokenService tokenService;
//
//    @BeforeEach
//    public void setUp() {
//
//        cache = Objects.requireNonNull(((EhCacheCacheManager) cacheManager).getCacheManager())
//                .getCache(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE);
//
//        assertNotNull(cache);
//        assertNull(cache.get(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE));
//    }
//
//    @Test
//    public void testCachePutAndGet() {
//
//        // Chamada inicial ao método getToken()
//        String token = "123";
//        TokenResponse tokenIntegracaoResponse = tokenService.getToken(token);
//        assertNotNull(tokenIntegracaoResponse);
//        assertNotNull(tokenIntegracaoResponse.token());
//
//
//        // Verificar que o valor foi colocado em cache
////        assertNotNull(cache.get(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE));
//        Object objectKey = cache.get(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE).getObjectKey();
//        Object objectValue = cache.get(CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE).getObjectValue();
//
////        assertEquals(tokenIntegracaoResponse.token(), );
//
//
//
//        // Obter valor do cache
////        Cache.ValueWrapper valueWrapper = cache.get(token);
////        assertNotNull(valueWrapper);
////        assertEquals(value, valueWrapper.get());
//
//
//
//        List keys = cache.getKeys();
//
//        for (Object key : keys) {
//		Element element = cache.get(key);
//		if (element != null) {
//		Object value = element.getObjectValue();
//		System.out.println("Chave: " + key + ", Valor: " + value);
//		}
//		}
//
//
//    }
//
//
//}