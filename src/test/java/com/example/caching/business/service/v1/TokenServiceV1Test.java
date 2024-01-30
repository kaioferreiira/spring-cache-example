package com.example.caching.business.service.v1;

import com.example.caching.application.cacheconfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import com.example.caching.business.service.v1.TokenServiceV1;
import com.example.caching.business.util.GenericParams;
import net.sf.ehcache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.CacheManager;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.cache.type=ehcache"})
public class TokenServiceV1Test extends GenericParams {
    private Cache cacheService1;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TokenServiceV1 service1;

    @BeforeEach
    public void setUp() {
        cacheService1 = Objects.requireNonNull(((EhCacheCacheManager) cacheManager).getCacheManager())
                .getCache(CacheManagerNames.CACHE_NAME_SERVICE_1);
    }

    @Test
    public void testEhCacheService1_RetornaMesmoToken_After10Segundos() {

        cacheService1.getCacheManager().clearAll();

        TokenResponse tokenIntegracaoResponse = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse);
        assertNotNull(tokenIntegracaoResponse.token());

        TokenResponse elemetCache123Now = (TokenResponse) cacheService1.get(CHAVE_DE_ACESSO_COOP_515).getObjectValue();
        threadSleep(10000);
        TokenResponse elemetCache123After = (TokenResponse) cacheService1.get(CHAVE_DE_ACESSO_COOP_515).getObjectValue();

        assertEquals(elemetCache123Now.token(), elemetCache123After.token());
    }

    @Test
    public void testEhCacheService1_RetornaVazioDoCache_AposExpirar20Segundos() {

        cacheService1.getCacheManager().clearAll();

        TokenResponse tokenIntegracaoResponse = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse);
        assertNotNull(tokenIntegracaoResponse.token());

        TokenResponse elemetCache123Now = (TokenResponse) cacheService1.get(CHAVE_DE_ACESSO_COOP_515).getObjectValue();
        threadSleep(10000);
        TokenResponse elemetCache123After = (TokenResponse) cacheService1.get(CHAVE_DE_ACESSO_COOP_515).getObjectValue();

        assertEquals(elemetCache123Now.token(), elemetCache123After.token());

        threadSleep(20000);
        assertNull(cacheService1.get(CHAVE_DE_ACESSO_COOP_515));
    }


    private void threadSleep(long numMillisecondsToSleep) {

        try {
            Thread.sleep(numMillisecondsToSleep);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}