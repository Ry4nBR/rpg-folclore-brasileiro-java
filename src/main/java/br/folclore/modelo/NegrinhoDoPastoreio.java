package br.folclore.modelo;

public class NegrinhoDoPastoreio extends Personagem {
    private static final int PV_BASE = 45;
    private static final int ATAQUE_BASE = 10;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 9;
    private static final int PODER_ATAQUE_BASICO = 6;

    public NegrinhoDoPastoreio() {
        super("Negrinho do Pastoreio", Tipo.PSIQUICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Toque Psíquico";
    }
}
