package com.example.caching;

import com.example.caching.business.service.TokenService1;
import com.example.caching.business.service.TokenService2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
@Slf4j
public class Application implements CommandLineRunner {

	@Autowired
	private CacheManager cacheManager;

	public static final String CHAVE_DE_ACESSO = "123";
	private final TokenService1 tokenService1;
	private final TokenService2 tokenService2;

	public Application(TokenService1 tokenService1, TokenService2 tokenService2) {
		this.tokenService1 = tokenService1;
		this.tokenService2 = tokenService2;
	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}


	@Override
	public void run(String... args) throws Exception {

		log.info("Fetching token");

		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		slowCall();
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		slowCall();
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));


		slowCall();

		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		slowCall();
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		slowCall();
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));

		slowCall();

		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));

		log.info("Get-token-1 -->" + tokenService1.getToken(CHAVE_DE_ACESSO));
		log.info("Get-token-2 -->" + tokenService2.getToken(CHAVE_DE_ACESSO));


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
