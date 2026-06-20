package meuplano.dominio;

/**
 * Entidade de domínio que representa um plano de aula.
 *
 * <p>Versão de DESIGN: agora encapsulada — os campos são privados e o acesso
 * acontece por getters/setters. Isso protege o estado da entidade e deixa
 * explícito quem pode lê-la e alterá-la, ao contrário da fase de análise, em
 * que os campos eram públicos.</p>
 */
public class PlanoDeAula {

    private String tema;
    private String nivel;
    private String objetivos;
    private String conteudos;
    private String metodologia;
    private String avaliacao;
    private String duracao;

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(String objetivos) {
        this.objetivos = objetivos;
    }

    public String getConteudos() {
        return conteudos;
    }

    public void setConteudos(String conteudos) {
        this.conteudos = conteudos;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(String avaliacao) {
        this.avaliacao = avaliacao;
    }

    public String getDuracao() {
        return duracao;
    }

    public void setDuracao(String duracao) {
        this.duracao = duracao;
    }

    /**
     * Devolve o plano como texto formatado, pronto para exibição.
     *
     * @return o relatório do plano de aula em múltiplas linhas
     */
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
