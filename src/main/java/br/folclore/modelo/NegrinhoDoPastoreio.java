package br.folclore.modelo;

public class NegrinhoDoPastoreio extends Personagem {
    private static final int PV_BASE = 45;
    private static final int ATAQUE_BASE = 10;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 9;

    public NegrinhoDoPastoreio() {
        super("Negrinho do Pastoreio", Tipo.PSIQUICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
