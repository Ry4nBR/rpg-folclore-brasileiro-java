package br.folclore.modelo;

import br.folclore.habilidades.HabilidadeDano;

public class Capelobo extends Personagem {
    private static final int PV_BASE = 75;
    private static final int ATAQUE_BASE = 13;
    private static final int DEFESA_BASE = 7;
    private static final int VELOCIDADE_BASE = 7;
    private static final int PODER_ATAQUE_BASICO = 7;

    public Capelobo() {
        super("Capelobo", Tipo.PLANTA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
        adicionarHabilidade(new HabilidadeDano("Uivo da Mata", 3, 16));
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Mordida Selvagem";
    }
}
