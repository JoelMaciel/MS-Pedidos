package com.joel.pedidos.api.dtos;

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
