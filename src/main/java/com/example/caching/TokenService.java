package com.example.caching;

import com.example.caching.cache_config.CacheManagerNames;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class TokenService implements TokenClient {

	@Override
//	@Cacheable(cacheNames = CacheManagerNames.TOKEN_CACHE)
	@Cacheable(cacheNames = CacheManagerNames.TOKEN_CACHE, key = "#autorization")
	public TokenResponse getToken(String autorization) {

		simulateSlowTokenClient();
		String token = token();

		return new TokenResponse(token, "token");
	}

	private String token() {
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
