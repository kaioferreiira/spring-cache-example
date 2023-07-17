package com.example.caching;

import com.example.caching.business.service.TokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class Application implements CommandLineRunner {

	@Autowired
	private CacheManager cacheManager;

	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	public static final String CHAVE_DE_ACESSO = "123";
	private final TokenService tokenService;

	public Application(TokenService tokenService) {
		this.tokenService = tokenService;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		logger.info(".... Fetching token");

		logger.info("Get-token-1 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		logger.info("Get-token-2 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		slowCall();

		logger.info("Get-token-3 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		slowCall();

		logger.info("Get-token-4 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		logger.info("Get-token-5 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		slowCall();

		logger.info("Get-token-6 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		logger.info("Get-token-7 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		logger.info("Get-token-8 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

		logger.info("Get-token-9 -->" + tokenService.getToken(CHAVE_DE_ACESSO));

	}


	// Don't do this at home
	private void slowCall() {
		try {
			long time = 4000; //4 segundos
			Thread.sleep(time);
		} catch (InterruptedException e) {
			throw new IllegalStateException(e);
		}
	}

}
