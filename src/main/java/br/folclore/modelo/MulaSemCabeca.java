package br.folclore.modelo;

public class MulaSemCabeca extends Personagem {
    public MulaSemCabeca() {
        super("Mula sem Cabeça", Tipo.FOGO, 70, 12, 7, 8);
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
