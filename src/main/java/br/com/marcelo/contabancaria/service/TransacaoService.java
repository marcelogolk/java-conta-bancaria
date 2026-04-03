package br.com.marcelo.contabancaria.service;

import br.com.marcelo.contabancaria.model.ContaId;
import br.com.marcelo.contabancaria.model.Transacao;
import br.com.marcelo.contabancaria.util.TransacaoKey;
import br.com.marcelo.contabancaria.util.TransacaoKeyBuilder;

import java.util.*;

public class TransacaoService {

    public List<Transacao> removerDuplicadas(List<Transacao> transacoes) {
        if (transacoes == null) {
            throw new IllegalArgumentException("A lista de transações não pode ser nula.");
        }

        if (transacoes.isEmpty()) {
            return new ArrayList<>();
        }

        TransacaoKeyBuilder transacaoKeyBuilder = new TransacaoKeyBuilder();
        Set<TransacaoKey> setTransacaoKeys = new HashSet<>();
        List<Transacao> resultado = new ArrayList<>();

        for (Transacao transacao : transacoes) {
            if (setTransacaoKeys.add(transacaoKeyBuilder.build(transacao))) {
                resultado.add(transacao);
            }
        }

        return resultado;
    }

    public Map<ContaId, List<Transacao>> agruparPorConta(List<Transacao> transacoes){
        if (transacoes == null) {
            throw new IllegalArgumentException("A lista de transações não pode ser nula.");
        }
        if (transacoes.isEmpty()) {
            return new HashMap<>();
        }
        Map<ContaId, List<Transacao>> map = new HashMap<>();
        ContaId contaId = null;
        for (Transacao transacao : transacoes) {
            contaId = new ContaId(transacao.getBanco(),
                    transacao.getAgencia(),
                    transacao.getConta(),
                    transacao.getTitular()
            );
            map.put(contaId, transacoes);
//        pegar ou criar lista da conta no mapa
//        adicionar transacao nessa lista
        }
        return map;
    }


    public void ordenarTransações(){}
    public void calcularSaldoFinal(){}

}
