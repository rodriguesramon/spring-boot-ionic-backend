package com.ramoncosta.cursomc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ramoncosta.cursomc.domain.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

	@Transactional(readOnly = true)
	@Query("SELECT cidade FROM Cidade cidade WHERE cidade.estado.id = :estado_id ORDER BY cidade.nome")
	public List<Cidade> findCidades(Integer estado_id);
	
}
