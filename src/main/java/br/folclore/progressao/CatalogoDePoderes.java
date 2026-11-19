package br.folclore.progressao;

import br.folclore.habilidades.Habilidade;

public final class CatalogoDePoderes {
    private CatalogoDePoderes() {
    }

    public static Habilidade criarPorNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do poder não pode ser nulo.");
        }

        switch (nome) {
            default -> throw new IllegalArgumentException("Poder desconhecido: " + nome);
        }
    }
}
