# MeuPlano.AI — Código de Design

Refatoração **orientada a objetos** do código de [análise](../analysis/). O
**comportamento do fluxo (UC01) é o mesmo** — o que muda é a estrutura.

## Estrutura em packages

As classes estão organizadas por responsabilidade:

```text
meuplano/
├── MeuPlanoApp.java            # ponto de entrada (monta e injeta)
├── controller/
│   └── GerarPlanoController.java
├── dominio/
│   └── PlanoDeAula.java        # entidade encapsulada
├── ia/
│   ├── ServicoIA.java          # interface
│   ├── ServicoIABase.java      # classe abstrata (Template Method)
│   ├── ServicoIASimulado.java  # implementação
│   └── ServicoIAOpenRouter.java
├── persistencia/
│   ├── PlanoRepository.java    # interface
│   └── RepositorioEmMemoria.java
├── exportacao/
│   ├── ExportadorPlano.java    # interface
│   └── ExportadorTexto.java
└── ui/
    └── ConsoleUI.java
```

## Como compilar e rodar

```bash
cd apps/meuplano.ai/design
javac $(find meuplano -name '*.java')
java meuplano.MeuPlanoApp
```

## O que mudou da análise para o design

| Análise | Design |
|---|---|
| `PlanoDeAula` com campos públicos | Entidade **encapsulada** (campos privados, getters/setters, Javadoc) |
| `ServicoIA` classe única e fixa | **Interface** `ServicoIA` + **classe abstrata** `ServicoIABase` (Template Method) |
| Uma IA só | Duas implementações (`ServicoIASimulado`, `ServicoIAOpenRouter`) via **polimorfismo** |
| Fluxo + IA + I/O acoplados no `MeuPlanoApp` | Responsabilidades **separadas**: `GerarPlanoController` (orquestra), `ConsoleUI` (I/O) |
| Sem persistência/exportação | `PlanoRepository`/`RepositorioEmMemoria` e `ExportadorPlano`/`ExportadorTexto` (interfaces) |
| `main` faz tudo | `main` só **monta e injeta** as dependências no controller |

## Prova do polimorfismo

Em `MeuPlanoApp.main`, trocar a IA é trocar **uma linha**:

```java
ServicoIA ia = new ServicoIASimulado();      // ou:
ServicoIA ia = new ServicoIAOpenRouter();
```

O `GerarPlanoController` não muda — ele depende da interface `ServicoIA`, não de
uma implementação concreta.

## Boas práticas aplicadas

- **Encapsulamento** na entidade `PlanoDeAula`.
- **Interface + polimorfismo** no serviço de IA (conectar várias IAs sem mexer no resto).
- **Herança + classe abstrata** com *Template Method* (`ServicoIABase.chamarModelo`).
- **Separação de responsabilidades** (entidade ≠ orquestração ≠ entrada/saída ≠ persistência).
- **Baixo acoplamento** via injeção de dependência no construtor do controller.
- **Javadoc** nas classes e métodos públicos.

As implementações de IA continuam **simuladas/offline**. Uma IA real (Ollama,
OpenRouter, ...) entra apenas como mais uma subclasse de `ServicoIABase`.
