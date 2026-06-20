# MeuPlano.AI — Código de Análise

Implementação **de análise** do fluxo principal do UC01 (Gerar Plano de Aula).
Foco no **domínio**, da forma mais simples possível.

## Características desta fase

- Java puro, interação via **terminal** (`Scanner` / `System.out`).
- A IA é **simulada** (`ServicoIA` preenche o plano por template).
- **Sem** interface, **sem** classe abstrata, **sem** herança/polimorfismo.
- Tudo acoplado em `MeuPlanoApp` — é o ponto de partida que a fase de
  [design](../design/) vai refatorar.

## Classes

| Classe | Responsabilidade |
|---|---|
| `PlanoDeAula` | Campos do plano + `comoRelatorio()` |
| `ServicoIA` | IA simulada: `gerarRascunho`, `melhorarRascunho`, `gerarVersaoFinal` |
| `MeuPlanoApp` | `main` + `executarFluxo()` com o UC01 no terminal |

## Como compilar e rodar

```bash
cd apps/meuplano.ai/analysis
javac *.java
java MeuPlanoApp
```
