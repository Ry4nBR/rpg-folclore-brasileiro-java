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
}
