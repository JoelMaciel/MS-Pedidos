package com.joel.pedidos.domain.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class ItemDoPedidoDTO {


    private Long id;
    private Integer quantidade;
    private String descricao;


}
