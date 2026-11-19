package br.folclore.modelo;

public class MaeDeOuro extends Personagem {
    private static final int PV_BASE = 40;
    private static final int ATAQUE_BASE = 8;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 7;

    public MaeDeOuro() {
        super("Mãe de Ouro", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
