package br.folclore.ia;

import br.folclore.modelo.Personagem;

public class IaPadrao implements InteligenciaArtificial {
    @Override
    public DecisaoIA decidir(Personagem inimigo, Personagem jogador, int turno) {
        return DecisaoIA.atacar();
    }
}
