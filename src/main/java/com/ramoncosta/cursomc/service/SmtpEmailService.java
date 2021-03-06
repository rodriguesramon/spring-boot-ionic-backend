package com.ramoncosta.cursomc.service;

import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage message) {
		LOG.info("Enviando Email...");
		mailSender.send(message);
		LOG.info("Email Enviado!");
		
	}	

	@Override
	public void sendHtmlEmail(MimeMessage message) {
		LOG.info("Enviando Email...");
		javaMailSender.send(message);
		LOG.info("Email Enviado!");		
	}

}
