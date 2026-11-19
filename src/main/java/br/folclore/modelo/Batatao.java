package br.folclore.modelo;

public class Batatao extends Personagem {
    public Batatao() {
        super("Batatão", Tipo.FOGO, 45, 9, 5, 5);
    }

    @Override
    public int atacar(Personagem alvo) {
        return 0;
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Ataque básico";
    }
}
