package meuplano.ia;

import meuplano.dominio.PlanoDeAula;

/**
 * Contrato de um serviço de IA capaz de produzir planos de aula.
 *
 * <p>É uma <b>interface</b>: descreve o <i>quê</i> (gerar, melhorar, finalizar)
 * sem amarrar o <i>como</i>. Qualquer provedor de IA (simulado, OpenRouter,
 * Ollama, etc.) entra no sistema apenas implementando este contrato — o resto
 * do código depende desta abstração, e não de uma implementação concreta.</p>
 */
public interface ServicoIA {

    /**
     * Gera um rascunho inicial a partir de uma descrição em linguagem natural.
     *
     * @param descricao o que o professor deseja, em texto livre
     * @return um plano de aula preenchido
     */
    PlanoDeAula gerarRascunho(String descricao);

    /**
     * Aplica instruções de melhoria a um rascunho existente.
     *
     * @param plano      o plano atual
     * @param instrucoes os ajustes solicitados pelo professor
     * @return o plano melhorado
     */
    PlanoDeAula melhorarRascunho(PlanoDeAula plano, String instrucoes);

    /**
     * Produz a versão final, polindo o rascunho revisado.
     *
     * @param plano o plano revisado
     * @return a versão final do plano
     */
    PlanoDeAula gerarVersaoFinal(PlanoDeAula plano);
}
