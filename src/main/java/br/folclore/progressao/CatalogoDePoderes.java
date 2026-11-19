package br.folclore.progressao;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.HabilidadeCura;
import br.folclore.habilidades.HabilidadeDano;

public final class CatalogoDePoderes {
    private CatalogoDePoderes() {
    }

    public static Habilidade criarPorNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do poder não pode ser nulo.");
        }

        switch (nome) {
            case "Ventos Revigorantes" -> {
                return new HabilidadeCura("Ventos Revigorantes", 4, 5);
            }
            case "Uivo Brutal" -> {
                return new HabilidadeDano("Uivo Brutal", 3, 12);
            }
            case "Objetos Perdidos" -> {
                return new HabilidadeDano("Objetos Perdidos", 3, 11);
            }
            default -> throw new IllegalArgumentException("Poder desconhecido: " + nome);
        }
    }
}
