package br.folclore.ia;

public final class DecisaoIA {
    private final TipoDeDecisao tipo;
    private final int indiceHabilidade;

    private DecisaoIA(TipoDeDecisao tipo, int indiceHabilidade) {
        this.tipo = tipo;
        this.indiceHabilidade = indiceHabilidade;
    }

    public static DecisaoIA atacar() {
        return new DecisaoIA(TipoDeDecisao.ATACAR, -1);
    }

    public static DecisaoIA habilidade(int indice) {
        return new DecisaoIA(TipoDeDecisao.USAR_HABILIDADE, indice);
    }

    public TipoDeDecisao getTipo() {
        return tipo;
    }

    public int getIndiceHabilidade() {
        return indiceHabilidade;
    }
}
