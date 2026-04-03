package br.com.marcelo.contabancaria.app;

import br.com.marcelo.contabancaria.model.Transacao;
import br.com.marcelo.contabancaria.repository.TransacaoCsvReader;
import br.com.marcelo.contabancaria.service.TransacaoService;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        TransacaoCsvReader reader = new TransacaoCsvReader();
        TransacaoService service = new TransacaoService();

        try {
            // 1. Leitura do CSV
            List<Transacao> transacoes = reader.readCsv("/operacoes_100.csv");

            // 2. Remoção de duplicadas
            List<Transacao> transacoesSemDuplicadas =
                    service.removerDuplicadas(transacoes);

            // 3. Contadores
            int totalOriginal = transacoes.size();
            int totalSemDuplicadas = transacoesSemDuplicadas.size();
            int totalDuplicadas = totalOriginal - totalSemDuplicadas;

            // 4. Impressões
            System.out.println("=== RESULTADO DA LEITURA ===");
            System.out.println("Total de transações lidas: " + totalOriginal);
            System.out.println("Total sem duplicadas: " + totalSemDuplicadas);
            System.out.println("Total de duplicadas removidas: " + totalDuplicadas);

            // (Opcional) imprimir algumas transações
            System.out.println("\n=== AMOSTRA DAS TRANSAÇÕES SEM DUPLICADAS ===");
            transacoesSemDuplicadas.stream()
                    .limit(10)
                    .forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Erro ao executar leitura do CSV: " + e.getMessage());
        }
    }
}