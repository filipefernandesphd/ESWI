/**
 * Implementação que <i>representaria</i> uma IA real via OpenRouter.
 *
 * <p>Aqui ela continua <b>simulada/offline</b> (não faz rede), mas existe para
 * provar o polimorfismo: é só mais uma subclasse de {@link ServicoIABase}. Numa
 * versão real, {@link #chamarModelo(String)} faria a chamada HTTP ao provedor —
 * e <b>nada mais</b> no sistema precisaria mudar.</p>
 */
public class ServicoIAOpenRouter extends ServicoIABase {

    @Override
    protected String chamarModelo(String prompt) {
        // TODO (futuro): chamada HTTP real ao OpenRouter usando 'prompt'.
        return "[gerado pela IA OpenRouter (simulado)]";
    }
}
