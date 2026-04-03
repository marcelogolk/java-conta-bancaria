package br.com.marcelo.contabancaria.util;

import br.com.marcelo.contabancaria.model.ContaId;
import br.com.marcelo.contabancaria.model.Transacao;
import java.util.Objects;

public class TransacaoKeyBuilder {

    public TransacaoKey build(Transacao transacao) {
        Objects.requireNonNull(transacao, "transacao não pode ser nula");

        ContaId contaId = new ContaId(
                transacao.getBanco(),
                transacao.getAgencia(),
                transacao.getConta(),
                transacao.getTitular()
        );

        return new TransacaoKey(
                contaId,
                transacao.getOperacao(),
                transacao.getDataHora(),
                transacao.getValor()
        );
    }
}