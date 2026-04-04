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

    public Map<ContaId, List<Transacao>> agruparPorConta(List<Transacao> transacoes) {
        if (transacoes == null) {
            throw new IllegalArgumentException("A lista de transações não pode ser nula.");
        }
        if (transacoes.isEmpty()) {
            return new HashMap<>();
        }
        Map<ContaId, List<Transacao>> map = new HashMap<>();
        for (Transacao transacao : transacoes) {
            ContaId contaId = new ContaId(
                    transacao.getBanco(),
                    transacao.getAgencia(),
                    transacao.getConta(),
                    transacao.getTitular()
            );
                List<Transacao> listaDaConta = map.get(contaId);
            if (listaDaConta == null) {
                listaDaConta = new ArrayList<>();
                map.put(contaId, listaDaConta);
            }
            listaDaConta.add(transacao);
        }
        return map;
    }

    public void ordenarTransacoesPorDataHora(Map<ContaId, List<Transacao>> agrupadas) {
        if (agrupadas == null) {
            throw new IllegalArgumentException("O mapa de transações agrupadas não pode ser nulo.");
        }
        if (agrupadas.isEmpty()) {
            return;
        }
        for (List<Transacao> listaConta : agrupadas.values()) {
            listaConta.sort(Comparator.comparing(Transacao::getDataHora));
        }
    }
}
