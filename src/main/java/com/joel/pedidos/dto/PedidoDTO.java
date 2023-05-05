package com.joel.pedidos.dto;

import com.joel.pedidos.enums.Status;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoDTO {

    private Long id;
    private LocalDateTime dataHora;
    private Status status;
    private List<ItemDoPedidoDTO> itens = new ArrayList<>();
}
