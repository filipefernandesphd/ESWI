package meuplano.ia;

import meuplano.dominio.PlanoDeAula;

/**
 * Base abstrata para serviços de IA.
 *
 * <p>Concentra a lógica <b>comum</b> a todos os provedores: montar o prompt e
 * transformar a resposta do modelo num {@link PlanoDeAula}. O ponto que varia
 * entre provedores — a chamada ao modelo em si — fica num método protegido e
 * abstrato, {@link #chamarModelo(String)}, que cada subclasse implementa.</p>
 *
 * <p>Esse é o padrão <i>Template Method</i>: o algoritmo (gerar/melhorar/finalizar)
 * é fixo aqui; apenas o passo específico do modelo é delegado às subclasses.</p>
 */
public abstract class ServicoIABase implements ServicoIA {

    /**
     * Chama o modelo de IA com um prompt e devolve a resposta em texto.
     *
     * <p>É o único ponto que cada provedor precisa implementar. Numa integração
     * real (Ollama, OpenRouter, ...), aqui estaria a chamada HTTP ao modelo.</p>
     *
     * @param prompt o texto enviado ao modelo
     * @return a resposta textual do modelo
     */
    protected abstract String chamarModelo(String prompt);

    @Override
    public PlanoDeAula gerarRascunho(String descricao) {
        String resposta = chamarModelo("Gerar plano de aula sobre: " + descricao);

        PlanoDeAula plano = new PlanoDeAula();
        plano.setTema(descricao);
        plano.setNivel("Ensino Superior");
        plano.setDuracao("2 horas/aula");
        plano.setObjetivos("Compreender os conceitos centrais de \"" + descricao + "\".");
        plano.setConteudos("Introdução, fundamentos e exemplos de \"" + descricao + "\".");
        plano.setMetodologia("Aula expositiva dialogada com exercícios práticos. " + resposta);
        plano.setAvaliacao("Lista de exercícios e participação em aula.");
        return plano;
    }

    @Override
    public PlanoDeAula melhorarRascunho(PlanoDeAula plano, String instrucoes) {
        String resposta = chamarModelo("Melhorar plano aplicando: " + instrucoes);
        plano.setObjetivos(plano.getObjetivos() + " (revisado conforme: " + instrucoes + ")");
        plano.setMetodologia(plano.getMetodologia() + " Ajuste: " + instrucoes + ". " + resposta);
        return plano;
    }

    @Override
    public PlanoDeAula gerarVersaoFinal(PlanoDeAula plano) {
        String resposta = chamarModelo("Finalizar e revisar o plano de aula");
        plano.setConteudos(plano.getConteudos() + " Sequência didática do básico ao avançado.");
        plano.setAvaliacao(plano.getAvaliacao() + " Inclui rubrica de correção. " + resposta);
        return plano;
    }
}
