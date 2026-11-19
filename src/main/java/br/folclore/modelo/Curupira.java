package br.folclore.modelo;

import br.folclore.habilidades.HabilidadeDano;

public class Curupira extends Personagem {
    private static final int PV_BASE = 42;
    private static final int ATAQUE_BASE = 8;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 9;
    private static final int PODER_ATAQUE_BASICO = 5;

    public Curupira() {
        super("Curupira", Tipo.PLANTA, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
        adicionarHabilidade(new HabilidadeDano("Pegadas Invertidas", 3, 10));
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Pisada Veloz";
    }
}
