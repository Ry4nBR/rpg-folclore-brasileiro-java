package br.folclore.modelo;

public class BotoCorDeRosa extends Personagem {
    private static final int PV_BASE = 46;
    private static final int ATAQUE_BASE = 9;
    private static final int DEFESA_BASE = 5;
    private static final int VELOCIDADE_BASE = 6;
    private static final int PODER_ATAQUE_BASICO = 5;

    public BotoCorDeRosa() {
        super("Boto-cor-de-rosa", Tipo.AGUA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Respingo Rosa";
    }
}
