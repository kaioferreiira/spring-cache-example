package com.example.caching.business.service.v1;

import com.example.caching.application.cacheconfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import com.example.caching.business.util.CacheUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceV1 {
    @Cacheable(CacheManagerNames.CACHE_NAME_SERVICE_1)
    public TokenResponse getToken(String autorization) {

        CacheUtil.slow(3000);

        return new TokenResponse("Bearer", obterTokenClient());
    }

    private String obterTokenClient() {
        double random = Math.random();
        return String.valueOf(random).substring(10);
    }

}
