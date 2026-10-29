package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public class HabilidadeDano extends HabilidadeBase {
    private final int poderBase;

    public HabilidadeDano(String nome, int usosMaximos, int poderBase) {
        super(nome, usosMaximos);
        this.poderBase = poderBase;
    }

    @Override
    public ResultadoAcao executar(Personagem usuario, Personagem alvo) {
        throw new UnsupportedOperationException("A execução desta habilidade ainda não foi implementada.");
    }
}
