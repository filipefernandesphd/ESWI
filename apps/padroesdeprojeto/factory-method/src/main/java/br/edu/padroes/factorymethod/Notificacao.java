package br.edu.padroes.factorymethod;

/**
 * Produto do Factory Method: define o contrato comum para qualquer notificação.
 */
public interface Notificacao {

    /**
     * Envia uma mensagem para um destinatário.
     *
     * @param destinatario quem recebe a notificação
     * @param mensagem texto enviado
     */
    void enviar(String destinatario, String mensagem);
}
