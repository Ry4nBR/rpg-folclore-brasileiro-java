package br.folclore.modelo;

public class Curupira extends Personagem {
    private static final int PV_BASE = 42;
    private static final int ATAQUE_BASE = 8;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 9;

    public Curupira() {
        super("Curupira", Tipo.PLANTA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
