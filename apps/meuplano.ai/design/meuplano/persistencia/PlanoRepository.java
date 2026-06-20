package meuplano.persistencia;

import java.util.List;

import meuplano.dominio.PlanoDeAula;

/**
 * Contrato de persistência de planos de aula (pós-condição "salvar o plano").
 *
 * <p>Interface: o controller depende dela, não de onde os dados ficam guardados.
 * Hoje há uma implementação em memória; amanhã poderia haver uma em banco de
 * dados ou arquivo, sem alterar o restante do sistema.</p>
 */
public interface PlanoRepository {

    /**
     * Salva um plano de aula.
     *
     * @param plano o plano a persistir
     */
    void salvar(PlanoDeAula plano);

    /**
     * Lista os planos já salvos.
     *
     * @return a lista de planos persistidos
     */
    List<PlanoDeAula> listar();
}
