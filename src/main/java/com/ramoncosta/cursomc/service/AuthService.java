package com.ramoncosta.cursomc.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ramoncosta.cursomc.domain.Cliente;
import com.ramoncosta.cursomc.repository.ClienteRepository;
import com.ramoncosta.cursomc.service.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	
	@Autowired
	private EmailService emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email n&atilde;o Encontrado!");
		}
		
		String newPassword = newPassword();
		cliente.setSenha(passwordEncode.encode(newPassword));
		clienteRepository.save(cliente);
		
		emailService.sendNewPasswordEmail(cliente, newPassword);
	}

	private String newPassword() {
		char[] vetor = new char[10];
		for(int i=0; i<10; i++) {
			vetor[i] = randomChar();			
		}
		return new String(vetor);
	}

	private char randomChar() {
		int option = random.nextInt(3);
		
		if(option == 0) {//gera um digito
			return (char) (random.nextInt(10) + 48);
		}
		else if(option == 1) {//gera letra maiuscula
			return (char) (random.nextInt(26) + 65);		
		}else{//gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

	
}
