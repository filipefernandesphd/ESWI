package br.edu.padroes.strategy;

/**
 * Estratégia concreta para entrega normal.
 */
public class FreteNormal implements EstrategiaFrete {

    @Override
    public double calcular(double valorPedido) {
        return valorPedido * 0.08;
    }

    @Override
    public String getDescricao() {
        return "Frete normal";
    }
}
