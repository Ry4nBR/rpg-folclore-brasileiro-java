package br.folclore.ui;

import br.folclore.ui.componentes.LinhaDeDialogo;
import java.util.List;

public final class Roteiro {
    private Roteiro() {
    }

    public static List<LinhaDeDialogo> introducao() {
        return List.of(
                new LinhaDeDialogo(null, "Narrador",
                        "Conta-se que, num Brasil de mata funda, as lendas antigas despertaram."),
                new LinhaDeDialogo(null, "Narrador",
                        "Curupiras, iaras e encantados voltaram a caminhar entre rios e árvores."),
                new LinhaDeDialogo(null, "Narrador",
                        "Três ginásios elementais guardam o equilíbrio da floresta."),
                new LinhaDeDialogo(null, "Narrador",
                        "Agora, um novo herói deve ouvir o chamado e restaurar a harmonia."));
    }

    public static List<LinhaDeDialogo> preGinasio(int indice) {
        return switch (indice) {
            case 0 -> List.of(
                    new LinhaDeDialogo("mula_sem_cabeca", "Mula sem Cabeça",
                            "Meu galope anuncia que o fogo da mata está fora de controle."),
                    new LinhaDeDialogo("mula_sem_cabeca", "Mula sem Cabeça",
                            "Se queres passar, mostra coragem diante das minhas chamas."),
                    new LinhaDeDialogo("mula_sem_cabeca", "Mula sem Cabeça",
                            "Prepara-te, viajante: este desafio não se vence com palavras."));
            default -> throw new IllegalArgumentException("Índice de ginásio inválido.");
        };
    }
}
