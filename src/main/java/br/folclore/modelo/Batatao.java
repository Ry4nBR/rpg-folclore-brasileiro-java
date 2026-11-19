package br.folclore.modelo;

public class Batatao extends Personagem {
    private static final int PV_BASE = 45;
    private static final int ATAQUE_BASE = 9;
    private static final int DEFESA_BASE = 5;
    private static final int VELOCIDADE_BASE = 5;

    public Batatao() {
        super("Batatão", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
    }

    @Override
    public int atacar(Personagem alvo) {
        return 0;
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Ataque básico";
    }
}
