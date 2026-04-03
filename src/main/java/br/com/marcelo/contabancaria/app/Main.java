package br.com.marcelo.contabancaria.app;

import br.com.marcelo.contabancaria.repository.TransacaoCsvReader;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        TransacaoCsvReader reader = new TransacaoCsvReader();
        try {
            // Caminho do arquivo dentro de resources
            reader.readCsv("/operacoes_100.csv");

        } catch (IOException e) {
            System.err.println("Erro ao executar leitura do CSV: " + e.getMessage());
        }
    }
}