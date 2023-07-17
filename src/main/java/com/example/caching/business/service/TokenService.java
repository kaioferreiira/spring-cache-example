package com.example.caching.business.service;

import com.example.caching.application.cacheConfig.CacheManagerNames;
import com.example.caching.business.service.dto.TokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

		@Cacheable(cacheNames = CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE)
//	@Cacheable(cacheNames = CacheManagerNames.TOKEN_CACHE_CLIENT_EXAMPLE, key = "#autorization")
	public TokenResponse getToken(String autorization) {

		simulateSlowTokenClient();
		String token = obterTokenClient();

		return new TokenResponse("Bearer", token);
	}
	private String obterTokenClient() {
		double random = Math.random();
		return String.valueOf(random).substring(10);
	}

	// Don't do this at home
	private void simulateSlowTokenClient() {
		try {
			long numMillisecondsToSleep = 3000; // 3 segundos
			Thread.sleep(numMillisecondsToSleep);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
