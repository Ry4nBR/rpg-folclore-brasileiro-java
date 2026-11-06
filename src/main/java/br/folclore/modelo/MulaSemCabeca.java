package br.folclore.modelo;

public class MulaSemCabeca extends Personagem {
    private static final int PV_BASE = 55;
    private static final int ATAQUE_BASE = 9;
    private static final int DEFESA_BASE = 5;
    private static final int PODER_ATAQUE_BASICO = 5;

    public MulaSemCabeca() {
        super("Mula sem Cabeça", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE);
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Coice Flamejante";
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }
}
