package br.folclore.modelo;

public class Iara extends Personagem {
    public Iara() {
        super("Iara", Tipo.AGUA, 43, 8, 4, 8);
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
