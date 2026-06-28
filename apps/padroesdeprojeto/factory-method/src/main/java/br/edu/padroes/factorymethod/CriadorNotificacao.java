package br.edu.padroes.factorymethod;

/**
 * Criador do Factory Method: define o fluxo comum e delega a criação do produto.
 */
public abstract class CriadorNotificacao {

    /**
     * Método fábrica. Cada subclasse decide qual notificação concreta criar.
     *
     * @return uma notificação pronta para uso
     */
    protected abstract Notificacao criarNotificacao();

    /**
     * Operação de alto nível usada pelo cliente.
     */
    public void enviarMensagem(String destinatario, String mensagem) {
        Notificacao notificacao = criarNotificacao();
        notificacao.enviar(destinatario, mensagem);
    }
}
