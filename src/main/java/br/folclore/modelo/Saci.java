package br.folclore.modelo;

public class Saci extends Personagem {

    public Saci() {
        super("Saci", Tipo.VENTO, 50, 9, 5, 10);
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
