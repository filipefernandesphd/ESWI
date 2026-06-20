/**
 * Serviço de IA do MeuPlano.AI — versão de ANÁLISE.
 *
 * É uma classe SIMPLES (não uma interface). A "IA" é SIMULADA: cada método
 * devolve um {@link PlanoDeAula} preenchido por template a partir do texto
 * recebido. O objetivo da fase de análise é fazer o domínio funcionar, não
 * conectar um modelo real. Trocar isto por uma IA de verdade é assunto da
 * fase de design.
 */
public class ServicoIA {

    /** Gera um rascunho inicial a partir da descrição em linguagem natural. */
    public PlanoDeAula gerarRascunho(String descricao) {
        PlanoDeAula plano = new PlanoDeAula();
        plano.tema = descricao;
        plano.nivel = "Ensino Superior";
        plano.duracao = "2 horas/aula";
        plano.objetivos = "Compreender os conceitos centrais de \"" + descricao + "\".";
        plano.conteudos = "Introdução, fundamentos e exemplos de \"" + descricao + "\".";
        plano.metodologia = "Aula expositiva dialogada com exercícios práticos.";
        plano.avaliacao = "Lista de exercícios e participação em aula.";
        return plano;
    }

    /** Aplica as instruções do professor ao rascunho e devolve uma versão melhorada. */
    public PlanoDeAula melhorarRascunho(PlanoDeAula plano, String instrucoes) {
        plano.metodologia = plano.metodologia
                + " Ajuste solicitado: " + instrucoes + ".";
        plano.objetivos = plano.objetivos
                + " (revisado conforme: " + instrucoes + ")";
        return plano;
    }

    /** Produz a versão final, "polindo" os campos do rascunho revisado. */
    public PlanoDeAula gerarVersaoFinal(PlanoDeAula plano) {
        plano.avaliacao = plano.avaliacao
                + " Inclui rubrica de correção e critérios claros.";
        plano.conteudos = plano.conteudos
                + " Sequência didática organizada do básico ao avançado.";
        return plano;
    }
}
