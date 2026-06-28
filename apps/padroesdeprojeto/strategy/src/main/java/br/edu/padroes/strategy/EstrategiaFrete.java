package br.edu.padroes.strategy;

/**
 * Estratégia do padrão Strategy: contrato para algoritmos de frete.
 */
public interface EstrategiaFrete {

    /**
     * Calcula o frete a partir do valor do pedido.
     *
     * @param valorPedido valor total do pedido
     * @return valor do frete
     */
    double calcular(double valorPedido);

    /**
     * @return descrição curta da estratégia usada
     */
    String getDescricao();
}
