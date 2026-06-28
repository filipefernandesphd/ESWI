package br.edu.padroes.strategy;

/**
 * Estratégia concreta para pedidos retirados na loja.
 */
public class FreteRetiradaNaLoja implements EstrategiaFrete {

    @Override
    public double calcular(double valorPedido) {
        return 0.0;
    }

    @Override
    public String getDescricao() {
        return "Retirada na loja";
    }
}
