package br.edu.padroes.strategy;

/**
 * Contexto do Strategy: usa uma estratégia de frete sem conhecer sua regra.
 */
public class CalculadoraFrete {

    private EstrategiaFrete estrategia;

    public CalculadoraFrete(EstrategiaFrete estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaFrete estrategia) {
        this.estrategia = estrategia;
    }

    public double calcular(double valorPedido) {
        return estrategia.calcular(valorPedido);
    }

    public String getDescricaoEstrategia() {
        return estrategia.getDescricao();
    }
}
