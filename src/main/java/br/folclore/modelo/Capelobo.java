package br.folclore.modelo;

public class Capelobo extends Personagem {
    private static final int PV_BASE = 75;
    private static final int ATAQUE_BASE = 13;
    private static final int DEFESA_BASE = 7;
    private static final int VELOCIDADE_BASE = 7;

    public Capelobo() {
        super("Capelobo", Tipo.PLANTA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
    }

    @Override
    public int atacar(Personagem alvo) {
        return 0;
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "";
    }
}
