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
            case 1 -> List.of(
                    new LinhaDeDialogo("capelobo", "Capelobo",
                            "A mata fechou seus caminhos, e eu guardo a passagem."),
                    new LinhaDeDialogo("capelobo", "Capelobo",
                            "Só quem respeita a floresta pode enfrentar sua força."),
                    new LinhaDeDialogo("capelobo", "Capelobo",
                            "Vem, viajante. Que a raiz revele quem merece seguir."));
            case 2 -> List.of(
                    new LinhaDeDialogo("honorato", "Honorato",
                            "As águas silenciaram, e a corrente já não conhece seu rumo."),
                    new LinhaDeDialogo("honorato", "Honorato",
                            "Para devolver vida ao rio, terás de atravessar meu desafio."),
                    new LinhaDeDialogo("honorato", "Honorato",
                            "Ergue-te, viajante. Que a força das águas decida."));
            default -> throw new IllegalArgumentException("Índice de ginásio inválido.");
        };
    }

    public static List<LinhaDeDialogo> aposGinasio(int indice) {
        return switch (indice) {
            case 0 -> List.of(
                    new LinhaDeDialogo(null, "Narrador",
                            "Vencida a Mula sem Cabeça, o fogo baixou seu bramido."),
                    new LinhaDeDialogo(null, "Narrador",
                            "A floresta abriu mais um espaço para os poderes do herói."),
                    new LinhaDeDialogo(null, "Narrador",
                            "Um aliado vindo das lendas iniciais juntou-se à equipe."),
                    new LinhaDeDialogo(null, "Narrador",
                            "Mas a mata ainda guarda dois desafios adiante."));
            case 1 -> List.of(
                    new LinhaDeDialogo(null, "Narrador",
                            "O Capelobo caiu, e as trilhas tornaram a se abrir."),
                    new LinhaDeDialogo(null, "Narrador",
                            "Mais um espaço para habilidade foi liberado pela floresta."),
                    new LinhaDeDialogo(null, "Narrador",
                            "O último dos heróis iniciais veio completar a equipe."),
                    new LinhaDeDialogo(null, "Narrador",
                            "Agora resta enfrentar o guardião das águas."));
            default -> throw new IllegalArgumentException("Índice de ginásio inválido.");
        };
    }
}
