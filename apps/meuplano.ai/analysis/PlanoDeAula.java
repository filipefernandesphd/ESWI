/**
 * Plano de aula gerado pelo MeuPlano.AI.
 *
 * Código de ANÁLISE: privilegiamos clareza sobre design. Por isso os campos são
 * públicos e a classe é só um "saco de dados" do domínio com um método que sabe
 * se imprimir como relatório. Nada de encapsulamento, herança ou interfaces aqui.
 */
public class PlanoDeAula {
    public String tema;
    public String nivel;
    public String objetivos;
    public String conteudos;
    public String metodologia;
    public String avaliacao;
    public String duracao;

    /** Devolve o plano como texto formatado, pronto para exibir no terminal. */
    public String comoRelatorio() {
        StringBuilder sb = new StringBuilder();
        sb.append("==================================================\n");
        sb.append("  PLANO DE AULA\n");
        sb.append("==================================================\n");
        sb.append("Tema.........: ").append(tema).append("\n");
        sb.append("Nível........: ").append(nivel).append("\n");
        sb.append("Duração......: ").append(duracao).append("\n");
        sb.append("--------------------------------------------------\n");
        sb.append("Objetivos....: ").append(objetivos).append("\n");
        sb.append("Conteúdos....: ").append(conteudos).append("\n");
        sb.append("Metodologia..: ").append(metodologia).append("\n");
        sb.append("Avaliação....: ").append(avaliacao).append("\n");
        sb.append("==================================================\n");
        return sb.toString();
    }
}
