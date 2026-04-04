package br.com.marcelo.contabancaria.service;
import br.com.marcelo.contabancaria.model.ContaId;
import br.com.marcelo.contabancaria.model.ResultadoConta;
import br.com.marcelo.contabancaria.model.Transacao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SaldoService {
    public List<ResultadoConta> gerarResultados(Map<ContaId, List<Transacao>> agrupadas) {
        if (agrupadas == null) {
            throw new IllegalArgumentException("O mapa de transações agrupadas não pode ser nulo.");
        }
        if (agrupadas.isEmpty()) {
            return new ArrayList<>();
        }
        List<ResultadoConta> resultadoContas = new ArrayList<>();
        for (Map.Entry<ContaId, List<Transacao>> agrupada : agrupadas.entrySet()) {
            BigDecimal saldo = BigDecimal.ZERO;
            ContaId contaId = agrupada.getKey();
            List<Transacao> transacoes = agrupada.getValue();
           for (Transacao transacao : transacoes) {
               BigDecimal valorAjustado = transacao.getValor()
                  .multiply(BigDecimal.valueOf(transacao.getOperacao().getFator()));

               saldo = saldo.add(valorAjustado);
           }
           ResultadoConta resultadoConta = new ResultadoConta(contaId, transacoes, saldo);
           resultadoContas.add(resultadoConta);
        }
        return resultadoContas;
    }
}