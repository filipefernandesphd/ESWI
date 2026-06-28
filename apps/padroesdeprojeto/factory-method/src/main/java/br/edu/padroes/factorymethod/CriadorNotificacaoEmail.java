package br.edu.padroes.factorymethod;

/**
 * Criador concreto que fabrica notificações por e-mail.
 */
public class CriadorNotificacaoEmail extends CriadorNotificacao {

    @Override
    protected Notificacao criarNotificacao() {
        return new NotificacaoEmail();
    }
}
