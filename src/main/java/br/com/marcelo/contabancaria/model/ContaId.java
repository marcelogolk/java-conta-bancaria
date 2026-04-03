package br.com.marcelo.contabancaria.model;

import java.util.Objects;

public class ContaId {

    private final String banco;
    private final String agencia;
    private final String conta;
    private final String titular;

    public ContaId(String banco, String agencia, String conta, String titular) {
        this.banco = Objects.requireNonNull(banco, "banco não pode ser nulo");
        this.agencia = Objects.requireNonNull(agencia, "agencia não pode ser nula");
        this.conta = Objects.requireNonNull(conta, "conta não pode ser nula");
        this.titular = Objects.requireNonNull(titular, "titular não pode ser nulo");
    }

    public String getBanco() {
        return banco;
    }

    public String getAgencia() {
        return agencia;
    }

    public String getConta() {
        return conta;
    }

    public String getTitular() {
        return titular;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ContaId contaId)) return false;
        return Objects.equals(banco, contaId.banco)
                && Objects.equals(agencia, contaId.agencia)
                && Objects.equals(conta, contaId.conta)
                && Objects.equals(titular, contaId.titular);
    }

    @Override
    public int hashCode() {
        return Objects.hash(banco, agencia, conta, titular);
    }

    @Override
    public String toString() {
        return "ContaId{" +
                "banco='" + banco + '\'' +
                ", agencia='" + agencia + '\'' +
                ", conta='" + conta + '\'' +
                ", titular='" + titular + '\'' +
                '}';
    }
}