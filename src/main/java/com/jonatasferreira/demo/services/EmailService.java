package com.jonatasferreira.demo.services;

import org.springframework.mail.SimpleMailMessage;

import com.jonatasferreira.demo.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido pedido);
	void sendEmail(SimpleMailMessage msg);
}
