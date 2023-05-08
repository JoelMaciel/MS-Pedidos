package com.joel.pedidos.config.rabbitmq;

import com.joel.pedidos.api.dtos.PagamentoDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PagamentoListener {

    @RabbitListener(queues = "pagamentos.detalhes-pedido")
    public void recebeMensagem(PagamentoDTO pagamentoDTO) {
        String mensagem = """
                Dados do pagamento: %s
                Número do pedido: %s
                Valor: %s
                Status: %s
                """.formatted(pagamentoDTO.getId(), pagamentoDTO.getPedidoId(), pagamentoDTO.getValor(),
                pagamentoDTO.getStatus());
        System.out.println("Recebi a mensagem" + mensagem);
    }
}
