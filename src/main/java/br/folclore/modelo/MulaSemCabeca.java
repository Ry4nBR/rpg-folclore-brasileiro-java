package br.folclore.modelo;

public class MulaSemCabeca extends Personagem {
    private static final int PV_BASE = 70;
    private static final int ATAQUE_BASE = 12;
    private static final int DEFESA_BASE = 7;
    private static final int VELOCIDADE_BASE = 8;
    private static final int PODER_ATAQUE_BASICO = 7;

    public MulaSemCabeca() {
        super("Mula sem Cabeça", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Coice Flamejante";
    }
}
