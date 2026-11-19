package br.folclore.modelo;

public class Honorato extends Personagem {
    private static final int PV_BASE = 80;
    private static final int ATAQUE_BASE = 13;
    private static final int DEFESA_BASE = 8;
    private static final int VELOCIDADE_BASE = 7;

    public Honorato() {
        super("Honorato", Tipo.AGUA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
