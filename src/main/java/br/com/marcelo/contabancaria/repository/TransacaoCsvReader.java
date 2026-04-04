package br.com.marcelo.contabancaria.repository;

import br.com.marcelo.contabancaria.model.Operacao;
import br.com.marcelo.contabancaria.model.Transacao;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


public class TransacaoCsvReader {

    public List<Transacao> readCsv(String caminhoArquivo) throws IOException {
        List<Transacao> transacoes = new ArrayList<>();
        InputStream is = getClass().getResourceAsStream(caminhoArquivo);
        if (is == null) {
            throw new IOException("Arquivo não encontrado: " + caminhoArquivo);
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            String linha;
            bufferedReader.readLine(); // leitura do cabeçalho para ignorar.
            while ((linha = bufferedReader.readLine()) != null) {
                Transacao transacao = parseLinha(linha);
                transacoes.add(transacao);
                System.out.println(transacao); // linha de teste
            }
        }
        return transacoes;
    }
    private Transacao parseLinha(String linha) {
        String[] partes = linha.split(",");

        if (partes.length != 7) {
            throw new IllegalArgumentException("Linha inválida: " + linha);
        }

        String agencia = partes[0];
        String conta = partes[1];
        String banco = partes[2];
        String titular = partes[3];

        Operacao operacao = Operacao.fromTexto(partes[4]);
        LocalDateTime dataHora = LocalDateTime.parse(partes[5]);
        BigDecimal valor = new BigDecimal(partes[6]);

        return new Transacao(
                agencia,
                conta,
                banco,
                titular,
                operacao,
                dataHora,
                valor
        );
    }
}

