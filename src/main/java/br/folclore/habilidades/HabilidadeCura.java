package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public class HabilidadeCura extends HabilidadeBase {
    private final int percentual;

    public HabilidadeCura(String nome, int usosMaximos, int percentual) {
        super(nome, usosMaximos);
        this.percentual = percentual;
    }

    @Override
    public ResultadoAcao executar(Personagem usuario, Personagem alvo) {
        throw new UnsupportedOperationException("A execução desta habilidade ainda não foi implementada.");
    }
}
