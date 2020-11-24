package com.jonatasferreira.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

public class MockEmailService extends EmailServiceAbstract {
	private static final Logger LOGGER = LoggerFactory.getLogger(MockEmailService.class);

	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOGGER.info("Simulando envio de email");
		LOGGER.info(msg.toString());
		LOGGER.info("Email enviado");
	}
	
}
