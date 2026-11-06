package br.folclore.modelo;

import br.folclore.habilidades.HabilidadeCura;

public class Saci extends Personagem {
    private static final int PV_BASE = 60;
    private static final int ATAQUE_BASE = 10;
    private static final int DEFESA_BASE = 6;
    private static final int PODER_ATAQUE_BASICO = 6;

    public Saci() {
        super("Saci", Tipo.VENTO, PV_BASE, ATAQUE_BASE, DEFESA_BASE);
        definirHabilidade(new HabilidadeCura("Ventos Revigorantes", 3, 20));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Rasteira de Vento";
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }
}
