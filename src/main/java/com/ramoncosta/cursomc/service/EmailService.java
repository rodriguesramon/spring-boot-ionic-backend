package com.ramoncosta.cursomc.service;

import org.springframework.mail.SimpleMailMessage;
import com.ramoncosta.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage message);
}
