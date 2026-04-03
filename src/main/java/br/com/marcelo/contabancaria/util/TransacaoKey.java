package br.com.marcelo.contabancaria.util;

import br.com.marcelo.contabancaria.model.ContaId;
import br.com.marcelo.contabancaria.model.Operacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class TransacaoKey {

    private final ContaId contaId;
    private final Operacao operacao;
    private final LocalDateTime dataHora;
    private final BigDecimal valor;

    public TransacaoKey(ContaId contaId,
                        Operacao operacao,
                        LocalDateTime dataHora,
                        BigDecimal valor) {
        this.contaId = Objects.requireNonNull(contaId, "contaId não pode ser nulo");
        this.operacao = Objects.requireNonNull(operacao, "operacao não pode ser nula");
        this.dataHora = Objects.requireNonNull(dataHora, "dataHora não pode ser nula");
        this.valor = Objects.requireNonNull(valor, "valor não pode ser nulo");
    }

    public ContaId getContaId() {
        return contaId;
    }

    public Operacao getOperacao() {
        return operacao;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TransacaoKey transacaoKey)) return false;
        return Objects.equals(contaId, transacaoKey.contaId)
                && operacao == transacaoKey.operacao
                && Objects.equals(dataHora, transacaoKey.dataHora)
                && Objects.equals(valor, transacaoKey.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contaId, operacao, dataHora, valor);
    }

    @Override
    public String toString() {
        return "TransacaoKey{" +
                "contaId=" + contaId +
                ", operacao=" + operacao +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                '}';
    }
}