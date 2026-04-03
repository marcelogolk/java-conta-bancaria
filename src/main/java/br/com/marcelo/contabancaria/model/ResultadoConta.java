package br.com.marcelo.contabancaria.model;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class ResultadoConta {

    private final ContaId contaId;
    private final List<Transacao> transacoes;
    private final BigDecimal saldoFinal;

    public ResultadoConta(ContaId contaId, List<Transacao> transacoes, BigDecimal saldoFinal) {
        this.contaId = Objects.requireNonNull(contaId, "contaId não pode ser nulo");
        this.transacoes = Objects.requireNonNull(transacoes, "transacoes não podem ser nulas");
        this.saldoFinal = Objects.requireNonNull(saldoFinal, "saldoFinal não pode ser nulo");
    }

    public ContaId getContaId() {
        return contaId;
    }

    public List<Transacao> getTransacoes() {
        return transacoes;
    }

    public BigDecimal getSaldoFinal() {
        return saldoFinal;
    }

    @Override
    public String toString() {
        return "ResultadoConta{" +
                "contaId=" + contaId +
                ", transacoes=" + transacoes +
                ", saldoFinal=" + saldoFinal +
                '}';
    }
}