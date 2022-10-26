package com.example.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class AppRunner implements CommandLineRunner {

	private static final Logger logger = LoggerFactory.getLogger(AppRunner.class);

	private final TokenClient tokenClient;

	public AppRunner(TokenClient tokenClient) {
		this.tokenClient = tokenClient;
	}

	@Override
	public void run(String... args) throws Exception {

		String chaveAcesso = "22233344421321";
		logger.info(".... Fetching token");

		logger.info("isbn-1 -->" + tokenClient.getToken(chaveAcesso));

		logger.info("isbn-2 -->" + tokenClient.getToken(chaveAcesso));

		slowCall();

		logger.info("isbn-3 -->" + tokenClient.getToken(chaveAcesso));

		slowCall();

		logger.info("isbn-4 -->" + tokenClient.getToken(chaveAcesso));

		logger.info("isbn-5 -->" + tokenClient.getToken(chaveAcesso));

		slowCall();

		logger.info("isbn-6 -->" + tokenClient.getToken(chaveAcesso));

		logger.info("isbn-7 -->" + tokenClient.getToken(chaveAcesso));

		logger.info("isbn-8 -->" + tokenClient.getToken(chaveAcesso));

		logger.info("isbn-9 -->" + tokenClient.getToken(chaveAcesso));
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
