package com.joel.pedidos.domain.models;

import com.joel.pedidos.domain.enums.Status;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.PERSIST)
    private List<ItemDoPedido> itens = new ArrayList<>();


}
