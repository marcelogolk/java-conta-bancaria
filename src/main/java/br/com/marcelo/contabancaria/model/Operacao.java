package br.com.marcelo.contabancaria.model;

public enum Operacao {
    SAQUE("Saque", -1),
    DEPOSITO("Depósito", 1);

    private final String descricao;
    private final int fator;

    Operacao(String descricao, int fator) {
        this.descricao = descricao;
        this.fator = fator;
    }

    public String getDescricao() {
        return descricao;
    }

    public int getFator() {
        return fator;
    }

    public static Operacao fromTexto(String texto) {
        if (texto == null || texto.isBlank()) {
            throw new IllegalArgumentException("Operação inválida: valor vazio");
        }

        String valorNormalizado = texto.trim().toUpperCase();

        return switch (valorNormalizado) {
            case "SAQUE" -> SAQUE;
            case "DEPOSITO", "DEPÓSITO" -> DEPOSITO;
            default -> throw new IllegalArgumentException("Operação desconhecida: " + texto);
        };
    }
}