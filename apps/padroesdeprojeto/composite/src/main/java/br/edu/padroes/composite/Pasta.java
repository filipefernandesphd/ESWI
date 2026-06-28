package br.edu.padroes.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * Composto do Composite: representa uma pasta que contém outros itens.
 */
public class Pasta implements ItemSistemaArquivos {

    private final String nome;
    private final List<ItemSistemaArquivos> itens = new ArrayList<>();

    public Pasta(String nome) {
        this.nome = nome;
    }

    public void adicionar(ItemSistemaArquivos item) {
        itens.add(item);
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public void exibir(String indentacao) {
        System.out.println(indentacao + "+ " + nome + "/");

        for (ItemSistemaArquivos item : itens) {
            item.exibir(indentacao + "  ");
        }
    }
}
