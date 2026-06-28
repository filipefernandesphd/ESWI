package br.edu.padroes.strategy;

/**
 * Estratégia concreta para entrega expressa.
 */
public class FreteExpresso implements EstrategiaFrete {

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * 0.15 + 12.00;
    }

    @Override
    public String getDescricao() {
        return "Frete expresso";
    }
}
