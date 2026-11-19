package br.folclore.modelo;

public class BotoCorDeRosa extends Personagem {
    public BotoCorDeRosa() {
        super("Boto-cor-de-rosa", Tipo.AGUA, 46, 9, 5, 6);
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
