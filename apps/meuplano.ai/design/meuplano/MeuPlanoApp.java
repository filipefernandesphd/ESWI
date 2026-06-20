package meuplano;

import meuplano.controller.GerarPlanoController;
import meuplano.exportacao.ExportadorPlano;
import meuplano.exportacao.ExportadorTexto;
import meuplano.ia.ServicoIA;
import meuplano.ia.ServicoIASimulado;
import meuplano.persistencia.PlanoRepository;
import meuplano.persistencia.RepositorioEmMemoria;
import meuplano.ui.ConsoleUI;

/**
 * Ponto de entrada do MeuPlano.AI — versão de DESIGN.
 *
 * <p>O {@code main} só faz a <b>montagem</b>: instancia as implementações
 * concretas e as injeta no {@link GerarPlanoController}. Repare que trocar a IA
 * usada é trocar <b>uma única linha</b> — graças à interface {@link ServicoIA} e
 * ao polimorfismo, o controller não muda.</p>
 */
public class MeuPlanoApp {

    public static void main(String[] args) {
        // Escolha da IA: troque por 'new ServicoIAOpenRouter()' e nada mais muda.
        ServicoIA ia = new ServicoIASimulado();

        PlanoRepository repositorio = new RepositorioEmMemoria();
        ExportadorPlano exportador = new ExportadorTexto();
        ConsoleUI ui = new ConsoleUI();

        GerarPlanoController controller =
                new GerarPlanoController(ia, repositorio, exportador, ui);

        controller.executarFluxo();
        ui.fechar();
    }
}
