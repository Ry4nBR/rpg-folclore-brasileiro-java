package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.progressao.CatalogoDePoderes;

public class Lobisomem extends Personagem {
    private static final int PV_BASE = 60;
    private static final int ATAQUE_BASE = 12;
    private static final int DEFESA_BASE = 6;
    private static final int VELOCIDADE_BASE = 8;
    private static final int PODER_ATAQUE_BASICO = 7;

    public Lobisomem() {
        super("Lobisomem", Tipo.FISICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
        for (Habilidade habilidade : CatalogoDePoderes.criarIniciais(getNome())) {
            adicionarHabilidade(habilidade);
        }
    }

    @Override
    protected int getPoderAtaqueBasico() {
        return PODER_ATAQUE_BASICO;
    }

    @Override
    public int atacar(Personagem alvo) {
        return alvo.receberDano(preverDanoAtaqueBasico(alvo));
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "Garra Selvagem";
    }
}
