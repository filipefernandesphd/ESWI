/**
 * Implementação de {@link ExportadorPlano} que exporta o plano como texto.
 *
 * <p>É o lugar onde, no futuro, entraria um exportador de PDF — bastaria criar
 * outra classe que implemente {@link ExportadorPlano}.</p>
 */
public class ExportadorTexto implements ExportadorPlano {

    @Override
    public void exportar(PlanoDeAula plano) {
        System.out.println("[Exportado como TEXTO]");
        System.out.println(plano.comoRelatorio());
    }
}
