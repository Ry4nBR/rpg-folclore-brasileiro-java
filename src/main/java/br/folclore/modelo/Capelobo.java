package br.folclore.modelo;

public class Capelobo extends Personagem {
    public Capelobo() {
        super("Capelobo", Tipo.PLANTA, 75, 13, 7, 7);
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
