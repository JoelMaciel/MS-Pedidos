package com.joel.pedidos.api.controllers;

import com.joel.pedidos.domain.dto.PedidoDTO;
import com.joel.pedidos.domain.dto.StatusDTO;
import com.joel.pedidos.domain.services.PedidoService;
import com.sun.istack.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    private final PedidoService pedidoService;

    @GetMapping
    public List<PedidoDTO> listarTodos() {
        return pedidoService.obterTodos();
    }

    @GetMapping("/{id}")
    public PedidoDTO buscarPorId(@PathVariable @NotNull Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoDTO cadastrar(@RequestBody @Valid PedidoDTO pedidoDTO, UriComponentsBuilder uriBuilder) {
        PedidoDTO pedido = pedidoService.criar(pedidoDTO);
        URI endereco = uriBuilder.path("/pedidos/{id}").buildAndExpand(pedido.getId()).toUri();
        return pedido;
    }

    @PutMapping("/{id}/status")
    public PedidoDTO atualizarStatus(@PathVariable Long id, @RequestBody @Valid StatusDTO statusDTO) {
        return pedidoService.atualizarStatus(id, statusDTO);
    }

    @PutMapping("/{id}/pago")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void aprovarPagamento(@PathVariable @NotNull Long id) {
        pedidoService.aprovarPagamentoPedido(id);
    }
}
