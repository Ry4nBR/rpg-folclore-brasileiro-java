package br.folclore.modelo;

public class Honorato extends Personagem {
    public Honorato() {
        super("Honorato", Tipo.AGUA, 80, 13, 8, 7);
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
