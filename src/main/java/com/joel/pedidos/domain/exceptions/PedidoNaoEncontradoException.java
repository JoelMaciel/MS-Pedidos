package com.joel.pedidos.domain.exceptions;

public class PedidoNaoEncontradoException extends RuntimeException{

    public PedidoNaoEncontradoException() {
    }

    public PedidoNaoEncontradoException(String message) {
        super(message);
    }
}
