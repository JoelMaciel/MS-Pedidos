package com.joel.pedidos.domain.repositories;

import com.joel.pedidos.domain.enums.Status;
import com.joel.pedidos.domain.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Transactional
    @Modifying
    @Query("update Pedido p set p.status = :status where p =:pedido")
    void atualizarStatus(Status status, Pedido pedido);

    @Query(value = "select p from Pedido p LEFT JOIN FETCH p.itens where p.id = :id")
    Pedido porIdComItens(Long id);
}
