package com.example.caching.business.service.v2;

import com.example.caching.application.cacheconfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import com.example.caching.business.util.CacheUtil;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceV2 {
    @Cacheable(value = CacheManagerNames.CACHE_NAME_SERVICE_2, key = "#cooperativa")
    public TokenResponse getToken(String cooperativa, String autorization) {

        CacheUtil.slow(3000);

        return new TokenResponse("Bearer", obterTokenClient());
    }

    private String obterTokenClient() {
        double random = Math.random();
        return String.valueOf(random).substring(10);
    }


}
