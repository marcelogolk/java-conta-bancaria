package br.com.marcelo.contabancaria.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Transacao {
    private final String agencia;
    private final String conta;
    private final String banco;
    private final String titular;
    private final Operacao operacao;
    private final LocalDateTime dataHora;
    private final BigDecimal valor;

    public Transacao(String agencia,
                     String conta,
                     String banco,
                     String titular,
                     Operacao operacao,
                     LocalDateTime dataHora,
                     BigDecimal valor) {

        this.agencia = Objects.requireNonNull(agencia, "agencia não pode ser nula");
        this.conta = Objects.requireNonNull(conta, "conta não pode ser nula");
        this.banco = Objects.requireNonNull(banco, "banco não pode ser nulo");
        this.titular = Objects.requireNonNull(titular, "titular não pode ser nulo");
        this.operacao = Objects.requireNonNull(operacao, "operacao não pode ser nula");
        this.dataHora = Objects.requireNonNull(dataHora, "dataHora não pode ser nula");
        this.valor = Objects.requireNonNull(valor, "valor não pode ser nulo");
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getBanco() {
        return banco;
    }

    public String getTitular() {
        return titular;
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
        if (!(o instanceof Transacao transacao)) return false;
        return Objects.equals(agencia, transacao.agencia)
                && Objects.equals(conta, transacao.conta)
                && Objects.equals(banco, transacao.banco)
                && Objects.equals(titular, transacao.titular)
                && operacao == transacao.operacao
                && Objects.equals(dataHora, transacao.dataHora)
                && Objects.equals(valor, transacao.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(agencia, conta, banco, titular, operacao, dataHora, valor);
    }

    @Override
    public String toString() {
        return "Transacao{" +
                "agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", banco='" + banco + '\'' +
                ", titular='" + titular + '\'' +
                ", operacao=" + operacao +
                ", dataHora=" + dataHora +
                ", valor=" + valor +
                '}';
    }

    public String toCsvLine() {
        return String.join(",",
                agencia,
                conta,
                banco,
                titular,
                operacao.name(),
                dataHora.toString(),
                valor.toPlainString()
        );
    }
}