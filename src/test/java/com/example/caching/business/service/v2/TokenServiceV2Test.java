package com.example.caching.business.service.v2;

import com.example.caching.application.cacheconfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import com.example.caching.business.util.GenericParams;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.test.context.TestPropertySource;

import java.util.Objects;

import static com.example.caching.business.util.CacheUtil.slow;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(properties = {"spring.cache.type=caffeine"})
public class TokenServiceV2Test extends GenericParams {

    private Cache cacheService2;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    private TokenServiceV2 service2;

    @BeforeEach
    public void setUp() {
        cacheService2 = Objects.requireNonNull((cacheManager
                .getCache(CacheManagerNames.CACHE_NAME_SERVICE_2)));
        assertNotNull(service2);
    }

    @Test
    public void testEhCacheService2_RetornaMesmoTokenCooperativa515_After106Segundos() {

        cacheService2.clear();

        TokenResponse tokenIntegracaoResponse = service2.getToken(API_KEY, SECRET_KEY);
        assertNotNull(tokenIntegracaoResponse);
        assertNotNull(tokenIntegracaoResponse.token());

        slow(6000);

        TokenResponse tokenIntegracaoResponse2 = service2.getToken(API_KEY, SECRET_KEY);

        assertEquals(tokenIntegracaoResponse.token(), tokenIntegracaoResponse2.token());
    }

    @Test
    public void testEhCacheService2_AdicionarTokenPara515eValidarSeExiste590_EntaoRetornarNaoExiste() {

        cacheService2.clear();

        TokenResponse tokenIntegracaoResponse = service2.getToken(API_KEY, SECRET_KEY);
        assertNotNull(tokenIntegracaoResponse);
        assertNotNull(tokenIntegracaoResponse.token());

        slow(1000);

        service2.getToken(API_KEY, SECRET_KEY);

        assertNull(cacheService2.get(API_KEY_2), "validar 590 não existente! ");
    }

    @Test
    public void testEhCacheService2_AdicionarTokenPara515590566_EntaoRetornarTodosCacheExistente() {

        cacheService2.clear();

        TokenResponse callToken515first = service2.getToken(API_KEY, SECRET_KEY);
        TokenResponse callToken590first = service2.getToken(API_KEY_2, SECRET_KEY_2);
        TokenResponse callToken566first = service2.getToken(API_KEY_3, SECRET_KEY_3);

        slow(2000);

        TokenResponse callToken515Second = service2.getToken(API_KEY, SECRET_KEY);
        TokenResponse callToken590Second = service2.getToken(API_KEY_2, SECRET_KEY_2);
        TokenResponse callToken566Second = service2.getToken(API_KEY_3, SECRET_KEY_3);

        assertNotNull(callToken515first);
        assertNotNull(callToken590first);
        assertNotNull(callToken566first);
        assertNotNull(callToken515Second);
        assertNotNull(callToken590Second);
        assertNotNull(callToken566Second);
        assertEquals(callToken515first.token(), callToken515Second.token());
        assertEquals(callToken590first.token(), callToken590Second.token());
        assertEquals(callToken566first.token(), callToken566Second.token());

    }

    @Test
    public void testEhCacheService2_AdicionarTokenPara515590566_EntaoRetornarNenhum() {

        cacheService2.clear();

        TokenResponse callToken515first = service2.getToken(API_KEY, SECRET_KEY);
        TokenResponse callToken590first = service2.getToken(API_KEY_2, SECRET_KEY_2);
        TokenResponse callToken566first = service2.getToken(API_KEY_3, SECRET_KEY_3);
        TokenResponse callToken515Second = service2.getToken(API_KEY, SECRET_KEY);
        TokenResponse callToken590Second = service2.getToken(API_KEY_2, SECRET_KEY_2);
        TokenResponse callToken566Second = service2.getToken(API_KEY_3, SECRET_KEY_3);

        assertNotNull(callToken515first);
        assertNotNull(callToken590first);
        assertNotNull(callToken566first);
        assertNotNull(callToken515Second);
        assertNotNull(callToken590Second);
        assertNotNull(callToken566Second);

        slow(20000);

        assertNull(cacheService2.get(API_KEY));
        assertNull(cacheService2.get(API_KEY_2));
        assertNull(cacheService2.get(API_KEY_3));
    }

}