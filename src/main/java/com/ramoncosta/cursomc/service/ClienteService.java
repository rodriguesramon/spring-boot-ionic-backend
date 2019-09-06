package com.ramoncosta.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ramoncosta.cursomc.domain.Cliente;
import com.ramoncosta.cursomc.repository.ClienteRepository;
import com.ramoncosta.cursomc.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = repository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Cliente.class.getName()));
	}
}
