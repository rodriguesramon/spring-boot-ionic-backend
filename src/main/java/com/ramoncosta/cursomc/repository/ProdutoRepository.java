package com.ramoncosta.cursomc.repository;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ramoncosta.cursomc.domain.Categoria;
import com.ramoncosta.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{
	
	@Query("SELECT DISTINCT produto FROM Produto produto INNER JOIN produto.categorias categoria WHERE produto.nome LIKE %:nome% AND categoria IN :categorias ")
	Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
	
	//Poderiamos ter também a @Query, porém a prioridade seria a @Query, aí teria que colocar os @Param
	
	@Transactional(readOnly=true)
	@Query("SELECT DISTINCT produto FROM Produto produto INNER JOIN produto.categorias categoria WHERE produto.nome LIKE %:nome% AND categoria IN :categorias ")
	Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
