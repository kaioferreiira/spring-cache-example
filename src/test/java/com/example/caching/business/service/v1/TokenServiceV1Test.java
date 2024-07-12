package com.example.caching.business.service.v1;

import com.example.caching.application.cacheconfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import com.example.caching.business.util.CacheUtil;
import com.example.caching.business.util.GenericParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.cache.type=caffeine"})
public class TokenServiceV1Test extends GenericParams {

    private Cache cacheService1;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TokenServiceV1 service1;

    @BeforeEach
    public void setUp() {
        cacheService1 = Objects.requireNonNull((cacheManager
                .getCache(CacheManagerNames.CACHE_NAME_SERVICE_1)));
        assertNotNull(service1);
    }

    @Test
    public void testEhCacheService1_RetornaMesmoToken_After10Segundos() {

        cacheService1.clear();

        TokenResponse tokenIntegracaoResponse1 = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse1);
        assertNotNull(tokenIntegracaoResponse1.token());

        TokenResponse tokenIntegracaoResponse2 = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse2);
        assertNotNull(tokenIntegracaoResponse2.token());

        assertEquals(tokenIntegracaoResponse1.token(), tokenIntegracaoResponse2.token());

        CacheUtil.slow(10000);

        TokenResponse tokenIntegracaoResponse3 = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse3);
        assertNotNull(tokenIntegracaoResponse3.token());

        assertEquals(tokenIntegracaoResponse1.token(), tokenIntegracaoResponse3.token());

    }

    @Test
    public void testEhCacheService1_RetornaVazioDoCache_AposExpirar20Segundos() {

        cacheService1.clear();

        TokenResponse tokenIntegracaoResponse1 = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse1);
        assertNotNull(tokenIntegracaoResponse1.token());

        TokenResponse tokenIntegracaoResponse2 = service1.getToken(CHAVE_DE_ACESSO_COOP_515);
        assertNotNull(tokenIntegracaoResponse2);
        assertNotNull(tokenIntegracaoResponse2.token());

        assertEquals(tokenIntegracaoResponse1.token(), tokenIntegracaoResponse2.token());

        CacheUtil.slow(20000);

        Cache.ValueWrapper actual = cacheService1.get(CHAVE_DE_ACESSO_COOP_515);
        assertNull(actual);
    }



}