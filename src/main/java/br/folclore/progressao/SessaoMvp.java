package br.folclore.progressao;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Personagem;
import java.util.Objects;

public class SessaoMvp {
    private boolean ginasioConcluido;

    public boolean isGinasioConcluido() {
        return ginasioConcluido;
    }

    public Batalha criarBatalha(Personagem heroi) {
        return new Batalha(heroi, new MulaSemCabeca());
    }

    public void registrarResultado(ResultadoBatalha resultado) {
        Objects.requireNonNull(resultado, "O resultado da batalha não pode ser nulo.");
        if (resultado == ResultadoBatalha.VITORIA) {
            ginasioConcluido = true;
        }
    }

    public void reiniciar() {
        ginasioConcluido = false;
    }
}
