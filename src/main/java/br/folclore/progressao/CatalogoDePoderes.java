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
            case "Chama Dourada" -> {
                return new HabilidadeDano("Chama Dourada", 3, 11);
            }
            case "Rolo de Fogo" -> {
                return new HabilidadeDano("Rolo de Fogo", 3, 12);
            }
            case "Galope Infernal" -> {
                return new HabilidadeDano("Galope Infernal", 3, 15);
            }
            default -> throw new IllegalArgumentException("Poder desconhecido: " + nome);
        }
    }
}
