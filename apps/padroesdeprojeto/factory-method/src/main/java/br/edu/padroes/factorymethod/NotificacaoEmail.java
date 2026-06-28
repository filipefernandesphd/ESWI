package br.edu.padroes.factorymethod;

/**
 * Produto concreto que representa o envio de notificação por e-mail.
 */
public class NotificacaoEmail implements Notificacao {

    @Override
    public void enviar(String destinatario, String mensagem) {
        System.out.println("[E-MAIL] Para: " + destinatario);
        System.out.println("Mensagem: " + mensagem);
    }
}
