package br.folclore.ia;

import br.folclore.modelo.Personagem;

public interface InteligenciaArtificial {
    DecisaoIA decidir(Personagem inimigo, Personagem jogador, int turno);
}
