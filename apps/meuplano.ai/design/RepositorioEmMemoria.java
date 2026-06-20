import java.util.ArrayList;
import java.util.List;

/**
 * Implementação de {@link PlanoRepository} que guarda os planos em memória.
 *
 * <p>Suficiente para a aula. O ponto importante é que ela satisfaz o contrato:
 * trocá-la por uma versão em banco de dados não afetaria o controller.</p>
 */
public class RepositorioEmMemoria implements PlanoRepository {

    private final List<PlanoDeAula> planos = new ArrayList<>();

    @Override
    public void salvar(PlanoDeAula plano) {
        planos.add(plano);
    }

    @Override
    public List<PlanoDeAula> listar() {
        return new ArrayList<>(planos);
    }
}
