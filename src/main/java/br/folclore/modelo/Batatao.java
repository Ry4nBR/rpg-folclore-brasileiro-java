package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.progressao.CatalogoDePoderes;

public class Batatao extends Personagem {
    private static final int PV_BASE = 34;
    private static final int ATAQUE_BASE = 5;
    private static final int DEFESA_BASE = 5;
    private static final int VELOCIDADE_BASE = 5;
    private static final int PODER_ATAQUE_BASICO = 3;

    public Batatao() {
        super("Batatão", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE, VELOCIDADE_BASE);
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
        return "Cabeçada Flamejante";
    }
}
