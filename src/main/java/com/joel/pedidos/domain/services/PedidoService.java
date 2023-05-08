package com.joel.pedidos.domain.services;

import com.joel.pedidos.api.dtos.PedidoDTO;
import com.joel.pedidos.api.dtos.StatusDTO;
import com.joel.pedidos.domain.enums.Status;
import com.joel.pedidos.domain.exceptions.PedidoNaoEncontradoException;
import com.joel.pedidos.domain.models.Pedido;
import com.joel.pedidos.domain.repositories.PedidoRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoService {

    public static final String MSG_PEDIDO_NAO_ENCONTRADO = "Pedido nao encontrado";
    private final PedidoRepository pedidoRepository;
    private final ModelMapper modelMapper;

    public List<PedidoDTO> obterTodos() {
        return pedidoRepository.findAll().stream()
                .map(p -> modelMapper.map(p, PedidoDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = buscarOuFalhar(id);
        return modelMapper.map(pedido, PedidoDTO.class);
    }


    public PedidoDTO criar(PedidoDTO pedidoDTO) {
        Pedido pedido = modelMapper.map(pedidoDTO, Pedido.class);

        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus(Status.REALIZADO);
        pedido.getItens().forEach(item -> item.setPedido(pedido));
        Pedido novoPedido = pedidoRepository.save(pedido);

        return modelMapper.map(pedido, PedidoDTO.class);
    }


    public PedidoDTO atualizarStatus(Long id, StatusDTO status) {
        buscarOuFalhar(id);
        Pedido pedido = pedidoRepository.porIdComItens(id);
        pedido.setStatus(status.getStatus());
        pedidoRepository.atualizarStatus(status.getStatus(), pedido);
        return modelMapper.map(pedido, PedidoDTO.class);

    }

    public void aprovarPagamentoPedido(Long id) {
        buscarPorId(id);
        Pedido pedido = pedidoRepository.porIdComItens(id);
        pedido.setStatus(Status.PAGO);
        pedidoRepository.atualizarStatus(Status.PAGO, pedido);
    }

    public Pedido buscarOuFalhar(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new PedidoNaoEncontradoException(MSG_PEDIDO_NAO_ENCONTRADO));
    }
}
