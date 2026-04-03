package br.com.marcelo.contabancaria.util;

import br.com.marcelo.contabancaria.model.Transacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TransacaoKeyBuilder {

    public List<Transacao> read(String caminhoArquivo) throws IOException {
        List<Transacao> trasacao = new ArrayList<>();
         try (InputStream is = getClass().getResourceAsStream(caminhoArquivo);
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {

            String linha;
            while ((linha = br.readLine()) != null) {
                // Apenas transformando em String (imprimindo para provar a leitura)
                // aqui chama o metodo privado para transformar a linha em uma transacao
                System.out.println(linha);

            }

        } catch (IOException | NullPointerException e) {
            System.err.println("Erro ao ler arquivo com BufferedReader: " + e.getMessage());
        }
        return trasacao;
    }
}
