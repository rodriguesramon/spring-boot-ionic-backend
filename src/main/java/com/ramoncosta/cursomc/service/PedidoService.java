package com.ramoncosta.cursomc.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ramoncosta.cursomc.domain.Pedido;
import com.ramoncosta.cursomc.repository.PedidoRepository;
import com.ramoncosta.cursomc.service.exceptions.ObjectNotFoundException;


@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repository;
	
	public Pedido find(Integer id) {
		Optional<Pedido> pedido = repository.findById(id);
		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Pedido.class.getName()));
	}
}
