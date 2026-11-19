package br.folclore.modelo;

public class MaeDeOuro extends Personagem {
    public MaeDeOuro() {
        super("Mãe de Ouro", Tipo.FOGO, 40, 8, 4, 7);
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
