# Strategy

## Problema

Um sistema de pedidos precisa calcular frete de formas diferentes: normal,
expresso ou retirada na loja. Uma solução comum é concentrar tudo em uma cadeia
de `if/else`. Com o tempo, cada nova regra aumenta o acoplamento e dificulta
testes e manutenção.

## Ideia do padrão

O Strategy encapsula cada algoritmo em uma classe separada. A
`CalculadoraFrete` recebe uma `EstrategiaFrete` e delega o cálculo para ela. A
estratégia pode ser trocada sem alterar a calculadora.

## Participantes no exemplo

| Papel no padrão | Classe |
| --- | --- |
| Estratégia | `EstrategiaFrete` |
| Estratégias concretas | `FreteNormal`, `FreteExpresso`, `FreteRetiradaNaLoja` |
| Contexto | `CalculadoraFrete` |
| Cliente | `Main` |

## Como executar

A partir desta pasta:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.padroes.strategy.Main
```

## O que observar

- `CalculadoraFrete` não conhece os detalhes de cada cálculo.
- Cada estratégia implementa uma regra de frete.
- `Main` troca a estratégia em tempo de execução.
- Para adicionar `FreteInternacional`, basta criar outra implementação.
