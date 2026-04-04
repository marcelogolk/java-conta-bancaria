### 🏦 Processador de Transações Bancárias em Java
##📌 Descrição
Aplicação desenvolvida em Java para processamento de transações bancárias a partir de arquivos CSV.

# Enunciado do Projeto
Crie uma aplicação que receba uma grande quantidade de transações bancárias (agência, conta, banco, titular, saque ou depósito, operador, data e hora e etc.) e organize todas essas operações por titular, ordene todas as operações por data e hora, elimine as operações duplicadas (quando têm o mesmo valor, tipo (saque, deposito), o mesmo operador e a mesma data e hora exata) e no final mostre o saldo final da conta após todas as transações. Todas as contas iniciam devem iniciar zeradas.
Decida qual estrutura de dados utilizar para receber todas as transações, a melhor forma de ordená-las, uma boa estratégia para eliminar as operações duplicadas.
As transações viram de um arquivo .csv com tudo desordenado e várias operações duplicadas.

# O sistema realiza:

Leitura de arquivos CSV
Remoção de transações duplicadas
Agrupamento por conta
Ordenação por data e hora
Cálculo de saldo final por conta

# 🎯 Objetivo
O objetivo do projeto é aplicar conceitos de:
Estruturas de dados
Ordenação
Complexidade de algoritmos (Big O)
Modelagem orientada a objetos

# 📂 Estrutura do Projeto

br.com.marcelo.contabancaria
├── model
├── repository
├── service
├── util
└── app

# ⚙️ Tecnologias utilizadas
Java 21
IntelliJ IDEA
Git / GitHub

# 📥 Entrada de dados
Arquivos CSV

# 🚀 Como executar
Clone o repositório
Execute a classe Main
Escolha o arquivo no menu:
1 - 100 registros
2 - 1000 registros
3 - 10000 registros
...

# 🔄 Pipeline de processamento
CSV → List → Deduplicação → Agrupamento → Ordenação → Cálculo de saldo

# 🧠 Decisões de Projeto
🔹 Leitura do arquivo
Uso de BufferedReader
Leitura sequencial (linha a linha)
🔹 Deduplicação
Uso de HashSet
Chave composta (TransacaoKey)
🔹 Agrupamento
Uso de HashMap
Estrutura: Map<ContaId, List<Transacao>>
🔹 Ordenação
Ordenação in-place
Comparator por dataHora

#🔹 Cálculo de saldo
Uso de BigDecimal
Aplicação de fator (+1 / -1)
📊 Complexidade
Etapa	Complexidade
Leitura	O(n)
Deduplicação	O(n)
Agrupamento	O(n)
Ordenação	O(n log n)
Cálculo de saldo	O(n)

#⚠️ Observações
Datas com Z são convertidas para LocalDateTime
Timezone é desconsiderado
Impressão de grandes volumes pode ser limitada

#📈 Possíveis melhorias
Uso de Stream API
Paralelização
Persistência em banco de dados
Interface gráfica

#👨‍💻 Autor
Marcelo G. Carvalho


