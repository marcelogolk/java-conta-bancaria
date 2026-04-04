package br.com.marcelo.contabancaria.app;

import br.com.marcelo.contabancaria.model.ContaId;
import br.com.marcelo.contabancaria.model.ResultadoConta;
import br.com.marcelo.contabancaria.model.Transacao;
import br.com.marcelo.contabancaria.repository.TransacaoCsvReader;
import br.com.marcelo.contabancaria.service.SaldoService;
import br.com.marcelo.contabancaria.service.TransacaoService;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TransacaoCsvReader reader = new TransacaoCsvReader();
        TransacaoService transacaoService = new TransacaoService();
        SaldoService saldoService = new SaldoService();

        int opcao;

        do {
            System.out.println("\n=== MENU ===");
            System.out.println("1 - Processar arquivo com 100 operações");
            System.out.println("2 - Processar arquivo com 1000 operações");
            System.out.println("3 - Processar arquivo com 10000 operações");
            System.out.println("4 - Processar arquivo com 100000 operações");
            System.out.println("5 - Processar arquivo com 100000 operações");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            String caminhoArquivo = switch (opcao) {
                case 1 -> "/operacoes_100.csv";
                case 2 -> "/operacoes_1000.csv";
                case 3 -> "/operacoes_10000.csv";
                case 4 -> "/operacoes_100000.csv";
                case 5 -> "/operacoes_1000000.csv";
                case 0 -> null;
                default -> "";
            };

            if (opcao == 0) {
                System.out.println("Encerrando o programa.");
                break;
            }

            if (caminhoArquivo.isEmpty()) {
                System.out.println("Opção inválida.");
                continue;
            }

            try {
                executarProcessamento(
                        caminhoArquivo,
                        reader,
                        transacaoService,
                        saldoService
                );
            } catch (IOException e) {
                System.err.println("Erro ao executar leitura do CSV: " + e.getMessage());
            }

        } while (true);

        scanner.close();
    }

    private static void executarProcessamento(String caminhoArquivo,
                                              TransacaoCsvReader reader,
                                              TransacaoService transacaoService,
                                              SaldoService saldoService) throws IOException {

        List<Transacao> transacoes = reader.readCsv(caminhoArquivo);
        List<Transacao> transacoesSemDuplicadas = transacaoService.removerDuplicadas(transacoes);
        Map<ContaId, List<Transacao>> agrupadas = transacaoService.agruparPorConta(transacoesSemDuplicadas);
        transacaoService.ordenarTransacoesPorDataHora(agrupadas);
        List<ResultadoConta> resultados = saldoService.gerarResultados(agrupadas);

        int totalOriginal = transacoes.size();
        int totalSemDuplicadas = transacoesSemDuplicadas.size();
        int totalDuplicadas = totalOriginal - totalSemDuplicadas;

        System.out.println("\n=== RESUMO DO PROCESSAMENTO ===");
        System.out.println("Arquivo processado: " + caminhoArquivo);
        System.out.println("Total de transações lidas: " + totalOriginal);
        System.out.println("Total sem duplicadas: " + totalSemDuplicadas);
        System.out.println("Total de duplicadas removidas: " + totalDuplicadas);
        System.out.println("Total de contas processadas: " + agrupadas.size());

        System.out.println("\n=== RESULTADOS POR CONTA ===");
        for (ResultadoConta resultado : resultados) {
            System.out.println(resultado);
        }
    }
}