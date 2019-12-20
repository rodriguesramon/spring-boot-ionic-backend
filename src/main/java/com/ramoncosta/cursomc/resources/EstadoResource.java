package com.ramoncosta.cursomc.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ramoncosta.cursomc.domain.Cidade;
import com.ramoncosta.cursomc.domain.Estado;
import com.ramoncosta.cursomc.dto.CidadeDTO;
import com.ramoncosta.cursomc.dto.EstadoDTO;
import com.ramoncosta.cursomc.service.CidadeService;
import com.ramoncosta.cursomc.service.EstadoService;

@RestController
@RequestMapping(value="/estados")
public class EstadoResource {
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private CidadeService cidadeService;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<EstadoDTO>> findAll(){
		List<Estado> listaEstado = estadoService.findAll();
		List<EstadoDTO> listaEstadoDTO = listaEstado
				.stream()
				.map(estado -> new EstadoDTO(estado))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaEstadoDTO);
	}
	
	@RequestMapping(value="{estadoId}/cidades", method=RequestMethod.GET)
	public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Integer estadoId){
		List<Cidade> listaCidade= cidadeService.findByEstado(estadoId);
		List<CidadeDTO> listaCidadeDTO = listaCidade
				.stream()
				.map(cidade -> new CidadeDTO(cidade))
				.collect(Collectors.toList());
		return ResponseEntity.ok().body(listaCidadeDTO);
	}
	
}
