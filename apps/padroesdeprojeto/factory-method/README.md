# Factory Method

## Problema

Um sistema precisa enviar notificações por canais diferentes, como e-mail e SMS.
Se o código cliente cria diretamente `new NotificacaoEmail()` ou
`new NotificacaoSMS()`, ele passa a conhecer detalhes de criação de cada tipo.
Isso dificulta adicionar novas notificações sem espalhar alterações.

## Ideia do padrão

O Factory Method delega a criação do objeto para um método fábrica. O código
cliente usa a abstração `Notificacao`, enquanto subclasses de
`CriadorNotificacao` decidem qual implementação concreta será criada.

## Participantes no exemplo

| Papel no padrão | Classe |
| --- | --- |
| Produto | `Notificacao` |
| Produtos concretos | `NotificacaoEmail`, `NotificacaoSMS` |
| Criador | `CriadorNotificacao` |
| Criadores concretos | `CriadorNotificacaoEmail`, `CriadorNotificacaoSMS` |
| Cliente | `Main` |

## Como executar

A partir desta pasta:

```bash
javac -d out $(find src/main/java -name "*.java")
java -cp out br.edu.padroes.factorymethod.Main
```

## O que observar

- `Main` não instancia `NotificacaoEmail` nem `NotificacaoSMS` diretamente.
- A classe abstrata `CriadorNotificacao` concentra o fluxo comum de envio.
- Cada criador concreto altera apenas a forma de criar a notificação.
- Para adicionar `NotificacaoPush`, a mudança fica concentrada em novas classes.
