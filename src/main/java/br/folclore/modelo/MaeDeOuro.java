package br.folclore.modelo;

import br.folclore.habilidades.HabilidadeDano;

public class MaeDeOuro extends Personagem {
    private static final int PV_BASE = 30;
    private static final int ATAQUE_BASE = 4;
    private static final int DEFESA_BASE = 4;
    private static final int VELOCIDADE_BASE = 7;
    private static final int PODER_ATAQUE_BASICO = 3;

    public MaeDeOuro() {
        super("Mãe de Ouro", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
        adicionarHabilidade(new HabilidadeDano("Chama Dourada", 3, 4));
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Bola de Fogo";
    }
}
