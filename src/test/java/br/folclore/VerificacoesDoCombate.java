package br.folclore;

import br.folclore.modelo.GolpesVerificacao;
import br.folclore.ui.componentes.SpriteAnimadoVerificacao;
import br.folclore.ui.efeitos.AnimacaoGifVerificacao;
import br.folclore.ui.telas.AnimacoesSaciVerificacao;
import br.folclore.ui.telas.TelaBatalhaVerificacao;

/** Executado por mvn test; qualquer falha de verificacao interrompe o build. */
public final class VerificacoesDoCombate {
    public static void main(String[] args) throws Exception {
        GolpesVerificacao.main(args);
        SpriteAnimadoVerificacao.main(args);
        AnimacaoGifVerificacao.main(args);
        TelaBatalhaVerificacao.main(args);
        AnimacoesSaciVerificacao.main(args);
    }
}
