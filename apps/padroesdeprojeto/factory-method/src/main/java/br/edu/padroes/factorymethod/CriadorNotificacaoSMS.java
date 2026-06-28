package br.edu.padroes.factorymethod;

/**
 * Criador concreto que fabrica notificações por SMS.
 */
public class CriadorNotificacaoSMS extends CriadorNotificacao {

    @Override
    protected Notificacao criarNotificacao() {
        return new NotificacaoSMS();
    }
}
