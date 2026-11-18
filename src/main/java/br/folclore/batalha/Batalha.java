package br.folclore.batalha;

import br.folclore.modelo.Personagem;

public class Batalha {
    private final Personagem jogador;
    private final Personagem inimigo;
    private EstadoBatalha estado = EstadoBatalha.INICIO;
    private final boolean fugaPermitida;

    public Batalha(Personagem jogador, Personagem inimigo) {
        if (jogador == null || inimigo == null) {
            throw new IllegalArgumentException("Os combatentes não podem ser nulos.");
        }

        this.jogador = jogador;
        this.inimigo = inimigo;
        this.fugaPermitida = !inimigo.ehChefe();
    }
}
