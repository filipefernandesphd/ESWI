/**
 * Orquestra o caso de uso UC01 — Gerar Plano de Aula.
 *
 * <p>Depende apenas de <b>abstrações</b> ({@link ServicoIA},
 * {@link PlanoRepository}, {@link ExportadorPlano}) e da {@link ConsoleUI}.
 * Por isso tem baixo acoplamento: trocar a IA, a persistência ou a forma de
 * exportar não exige mudar esta classe. As dependências são recebidas pelo
 * construtor (injeção de dependência).</p>
 */
public class GerarPlanoController {

    private final ServicoIA ia;
    private final PlanoRepository repositorio;
    private final ExportadorPlano exportador;
    private final ConsoleUI ui;

    public GerarPlanoController(ServicoIA ia,
                                PlanoRepository repositorio,
                                ExportadorPlano exportador,
                                ConsoleUI ui) {
        this.ia = ia;
        this.repositorio = repositorio;
        this.exportador = exportador;
        this.ui = ui;
    }

    /** Executa o fluxo principal do UC01 do início ao fim. */
    public void executarFluxo() {
        ui.exibir("==================================================");
        ui.exibir("  MeuPlano.AI — Gerador de Planos de Aula");
        ui.exibir("==================================================");

        String descricao = ui.lerLinha("\nDescreva o plano de aula que deseja gerar:");
        PlanoDeAula plano = gerarRascunho(descricao);
        ui.exibir("\n--- Rascunho gerado (revise os campos) ---");
        ui.exibir(plano.comoRelatorio());

        while (ui.lerLinha("Deseja enviar instruções para melhorar? (s/n)")
                .trim().equalsIgnoreCase("s")) {
            String instrucoes = ui.lerLinha("Quais ajustes a IA deve aplicar?");
            plano = melhorar(plano, instrucoes);
            ui.exibir("\n--- Rascunho atualizado ---");
            ui.exibir(plano.comoRelatorio());
        }

        plano = finalizar(plano);
        ui.exibir("\n>>> VERSÃO FINAL DO PLANO DE AULA <<<");
        ui.exibir(plano.comoRelatorio());

        // Pós-condições do UC01: salvar e exportar.
        salvar(plano);
        exportar(plano);
    }

    public PlanoDeAula gerarRascunho(String descricao) {
        return ia.gerarRascunho(descricao);
    }

    public PlanoDeAula melhorar(PlanoDeAula plano, String instrucoes) {
        return ia.melhorarRascunho(plano, instrucoes);
    }

    public PlanoDeAula finalizar(PlanoDeAula plano) {
        return ia.gerarVersaoFinal(plano);
    }

    public void salvar(PlanoDeAula plano) {
        repositorio.salvar(plano);
        ui.exibir("\n[Plano salvo — total na conta: " + repositorio.listar().size() + "]");
    }

    public void exportar(PlanoDeAula plano) {
        exportador.exportar(plano);
    }
}
