package br.edu.padroes.composite;

/**
 * Demonstra o uso do Composite em uma estrutura de arquivos e pastas.
 */
public class Main {

    public static void main(String[] args) {
        Pasta projeto = new Pasta("projeto-padroes");
        Pasta src = new Pasta("src");
        Pasta docs = new Pasta("docs");

        src.adicionar(new Arquivo("Main.java"));
        src.adicionar(new Arquivo("Notificacao.java"));

        docs.adicionar(new Arquivo("diagrama-classes.mmd"));
        docs.adicionar(new Arquivo("atividade.md"));

        projeto.adicionar(new Arquivo("README.md"));
        projeto.adicionar(src);
        projeto.adicionar(docs);

        projeto.exibir("");
    }
}
