package br.edu.padroes.factorymethod;

/**
 * Demonstra o uso do Factory Method no envio de notificações.
 */
public class Main {

    public static void main(String[] args) {
        CriadorNotificacao criadorEmail = new CriadorNotificacaoEmail();
        CriadorNotificacao criadorSMS = new CriadorNotificacaoSMS();

        criadorEmail.enviarMensagem(
                "professora@if.edu.br",
                "Seu plano de aula foi gerado.");

        System.out.println();

        criadorSMS.enviarMensagem(
                "(33) 99999-0000",
                "Lembrete: aula de Engenharia de Software às 19h.");
    }
}
