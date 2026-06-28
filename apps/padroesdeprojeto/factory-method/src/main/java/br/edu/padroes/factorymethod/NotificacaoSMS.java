package br.edu.padroes.factorymethod;

/**
 * Produto concreto que representa o envio de notificação por SMS.
 */
public class NotificacaoSMS implements Notificacao {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("[SMS] Para: " + destinatario);
        System.out.println("Mensagem: " + mensagem);
    }
}
