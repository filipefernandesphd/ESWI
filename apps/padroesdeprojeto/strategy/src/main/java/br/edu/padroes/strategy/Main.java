package br.edu.padroes.strategy;

/**
 * Demonstra a troca de estratégias de frete em tempo de execução.
 */
public class Main {

    public static void main(String[] args) {
        double valorPedido = 200.00;
        CalculadoraFrete calculadora = new CalculadoraFrete(new FreteNormal());

        exibirFrete(calculadora, valorPedido);

        calculadora.setEstrategia(new FreteExpresso());
        exibirFrete(calculadora, valorPedido);

        calculadora.setEstrategia(new FreteRetiradaNaLoja());
        exibirFrete(calculadora, valorPedido);
    }

    private static void exibirFrete(CalculadoraFrete calculadora, double valorPedido) {
        double frete = calculadora.calcular(valorPedido);

        System.out.printf("%s: R$ %.2f%n", calculadora.getDescricaoEstrategia(), frete);
    }
}
