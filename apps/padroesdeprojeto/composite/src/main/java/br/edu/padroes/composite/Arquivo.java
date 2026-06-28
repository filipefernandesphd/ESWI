package br.edu.padroes.composite;

/**
 * Folha do Composite: representa um item simples, sem filhos.
 */
public class Arquivo implements ItemSistemaArquivos {

    private final String nome;

    public Arquivo(String nome) {
        this.nome = nome;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "- " + nome);
    }
}
