package com.ramoncosta.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ramoncosta.cursomc.domain.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{

}
