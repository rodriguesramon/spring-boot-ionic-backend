package com.ramoncosta.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ramoncosta.cursomc.domain.Cidade;
import com.ramoncosta.cursomc.domain.Cliente;
import com.ramoncosta.cursomc.domain.Endereco;
import com.ramoncosta.cursomc.domain.enums.TipoCliente;
import com.ramoncosta.cursomc.dto.ClienteDTO;
import com.ramoncosta.cursomc.dto.ClienteNewDTO;
import com.ramoncosta.cursomc.repository.ClienteRepository;
import com.ramoncosta.cursomc.repository.EnderecoRepository;
import com.ramoncosta.cursomc.service.exceptions.DataIntegrityException;
import com.ramoncosta.cursomc.service.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id 
				+ ", Tipo: " + Cliente.class.getName()));
	}
	
	@Transactional
	public Cliente insert(Cliente cliente) {
		cliente.setId(null);
		cliente = clienteRepository.save(cliente);
		enderecoRepository.saveAll(cliente.getEnderecos());
		return cliente;
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	private void updateData(Cliente newCliente, Cliente cliente) {
		newCliente.setNome(cliente.getNome());
		newCliente.setEmail(cliente.getEmail());
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);			
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
		}
	}

	public List<Cliente> findAll() {		
		return clienteRepository.findAll();
	}
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente(objDto.getId(), objDto.getNome(), objDto.getEmail(), null, null);
	}
	
	public Cliente fromDTO(ClienteNewDTO clienteDTO) {
		Cliente cliente  = new Cliente(null, clienteDTO.getNome(), clienteDTO.getEmail(), clienteDTO.getCpfOuCnpj(), TipoCliente.toEnum(clienteDTO.getTipo()));
		Cidade cidade = new Cidade(clienteDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, clienteDTO.getLogradouro(), clienteDTO.getNumero(), clienteDTO.getComplemento() ,  clienteDTO.getBairro(), clienteDTO.getCep(), cliente, cidade);
		cliente.getEnderecos().add(endereco);
		cliente.getTelefones().add(clienteDTO.getTelefone1());
		
		if(clienteDTO.getTelefone2() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone2());	
		}
		
		if(clienteDTO.getTelefone3() != null) {
			cliente.getTelefones().add(clienteDTO.getTelefone3());	
		}
		
		return cliente;
	}
}
