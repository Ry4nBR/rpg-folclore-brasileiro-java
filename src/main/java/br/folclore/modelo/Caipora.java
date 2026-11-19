package br.folclore.modelo;

public class Caipora extends Personagem {
    public Caipora() {
        super("Caipora", Tipo.PLANTA, 44, 9, 5, 7);
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
