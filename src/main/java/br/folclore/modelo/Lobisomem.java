package br.folclore.modelo;

public class Lobisomem extends Personagem {

    public Lobisomem() {
        super("Lobisomem", Tipo.FISICO, 60, 12, 6, 8);
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
