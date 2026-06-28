# Composite

## Problema

Um sistema precisa representar arquivos e pastas. Arquivos são itens simples.
Pastas são itens compostos, pois podem conter arquivos e outras pastas. Sem um
contrato comum, o código cliente precisa tratar cada caso separadamente.

## Ideia do padrão

O Composite permite tratar objetos simples e compostos de forma uniforme. No
exemplo, `Arquivo` e `Pasta` implementam `ItemSistemaArquivos`, então o cliente
chama `exibir()` sem perguntar se o item é arquivo ou pasta.

## Participantes no exemplo

| Papel no padrão | Classe |
| --- | --- |
| Componente | `ItemSistemaArquivos` |
| Folha | `Arquivo` |
| Composto | `Pasta` |
| Cliente | `Main` |

## Como executar

A partir desta pasta:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.padroes.composite.Main
```

## O que observar

- `Pasta` guarda uma lista de `ItemSistemaArquivos`.
- A lista pode receber tanto `Arquivo` quanto outra `Pasta`.
- `Main` monta uma árvore e chama `exibir()` no item raiz.
- A chamada percorre a estrutura inteira de forma recursiva.
