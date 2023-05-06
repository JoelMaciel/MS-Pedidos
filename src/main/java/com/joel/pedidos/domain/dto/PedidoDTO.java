package com.joel.pedidos.domain.dto;

import com.joel.pedidos.domain.enums.Status;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PedidoDTO {

    private Long id;
    @NotNull
    private LocalDateTime dataHora;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Status status;

    @Valid
    private List<ItemDoPedidoDTO> itens = new ArrayList<>();


}
