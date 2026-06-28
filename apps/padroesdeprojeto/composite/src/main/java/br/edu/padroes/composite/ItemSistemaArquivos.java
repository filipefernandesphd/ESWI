package br.edu.padroes.composite;

/**
 * Componente do Composite: contrato comum para arquivos e pastas.
 */
public interface ItemSistemaArquivos {

    /**
     * @return nome exibido na árvore de arquivos
     */
    String getNome();

    /**
     * Exibe o item usando a indentação informada.
     *
     * @param indentacao espaços usados para representar a hierarquia
     */
    void exibir(String indentacao);
}
