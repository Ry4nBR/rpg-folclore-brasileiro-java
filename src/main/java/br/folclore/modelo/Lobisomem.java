package br.folclore.modelo;

public class Lobisomem extends Personagem {
    private static final int PV_BASE = 60;
    private static final int ATAQUE_BASE = 12;
    private static final int DEFESA_BASE = 6;
    private static final int VELOCIDADE_BASE = 8;

    public Lobisomem() {
        super("Lobisomem", Tipo.FISICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
