/**
 * Contrato de exportação de um plano de aula (pós-condição "exportar em PDF").
 *
 * <p>Interface: hoje exportamos como texto no terminal; o exportador de PDF
 * futuro seria apenas outra implementação deste mesmo contrato.</p>
 */
public interface ExportadorPlano {

    /**
     * Exporta o plano de aula.
     *
     * @param plano o plano a exportar
     */
    void exportar(PlanoDeAula plano);
}
