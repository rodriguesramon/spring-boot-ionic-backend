package com.ramoncosta.cursomc.service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.SimpleMailMessage;
import com.ramoncosta.cursomc.domain.Pedido;

public interface EmailService {
	void sendOrderConfirmationEmail(Pedido pedido);
	
	void sendEmail(SimpleMailMessage message);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage message);
	
	
}
