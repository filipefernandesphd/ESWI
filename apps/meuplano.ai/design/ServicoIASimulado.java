/**
 * Implementação de IA <b>simulada</b> (offline).
 *
 * <p>Estende {@link ServicoIABase} e só precisa implementar
 * {@link #chamarModelo(String)}. Devolve uma resposta determinística — útil
 * para desenvolver e testar sem depender de nenhum provedor externo.</p>
 */
public class ServicoIASimulado extends ServicoIABase {

    @Override
    protected String chamarModelo(String prompt) {
        // Numa IA real, aqui entraria a chamada ao modelo. Aqui é só template.
        return "[gerado pela IA Simulada]";
    }
}
