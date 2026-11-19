package br.folclore.modelo;

public class Curupira extends Personagem {
    public Curupira() {
        super("Curupira", Tipo.PLANTA, 42, 8, 4, 9);
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
