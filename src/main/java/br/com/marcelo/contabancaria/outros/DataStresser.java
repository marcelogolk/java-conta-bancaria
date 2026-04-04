import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.Collectors;

public class DataStresser {
    public static void main(String[] args) throws IOException {
        // AJUSTE O CAMINHO ABAIXO PARA O SEU USUARIO
        String pathBase = "/home/marcelo/arquivos/";

        processar(pathBase + "base_1000_tmp.csv", pathBase + "operacoes_1000.csv", 1010);
        processar(pathBase + "base_10000_tmp.csv", pathBase + "operacoes_10000.csv", 10100);
        processar(pathBase + "base_100000_tmp.csv", pathBase + "operacoes_100000.csv", 101000);
        processar(pathBase + "base_1000000_tmp.csv", pathBase + "operacoes_1000000.csv", 1010000);
    }

    private static void processar(String entrada, String saida, int totalAlvo) throws IOException {
        Path inputPath = Paths.get(entrada);
        if (!Files.exists(inputPath)) {
            System.out.println("Erro: Arquivo não encontrado em " + entrada);
            return;
        }

        List<String> linhas = Files.lines(inputPath).skip(1).collect(Collectors.toList());
        String cabecalho = "AGENCIA,CONTA,BANCO,TITULAR,OPERACAO,DATAHORA,VALOR";

        List<String> listaFinal = new ArrayList<>(linhas);
        Random random = new Random();

        // Injetando duplicatas (aproximadamente 20%)
        while (listaFinal.size() < totalAlvo) {
            String linhaParaDuplicar = linhas.get(random.nextInt(linhas.size()));
            int repeticoes = random.nextInt(5) + 1;
            for (int i = 0; i < repeticoes && listaFinal.size() < totalAlvo; i++) {
                listaFinal.add(linhaParaDuplicar);
            }
        }

        Collections.shuffle(listaFinal);

        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(saida))) {
            writer.write(cabecalho);
            writer.newLine();
            for (String l : listaFinal) {
                writer.write(l);
                writer.newLine();
            }
        }
        System.out.println("Sucesso! Gerado: " + saida + " com " + listaFinal.size() + " linhas.");
    }
}