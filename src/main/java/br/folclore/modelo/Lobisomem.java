package br.folclore.modelo;

import br.folclore.habilidades.HabilidadeDano;

public class Lobisomem extends Personagem {
    private static final int PV_BASE = 70;
    private static final int ATAQUE_BASE = 12;
    private static final int DEFESA_BASE = 7;
    private static final int PODER_ATAQUE_BASICO = 7;

    public Lobisomem() {
        super("Lobisomem", Tipo.FISICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE);
        definirHabilidade(new HabilidadeDano("Uivo Brutal", 2, 12));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Garra Selvagem";
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }
}
