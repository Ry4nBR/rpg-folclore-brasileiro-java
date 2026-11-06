package br.folclore.modelo;

import java.util.List;

public final class CatalogoDeHerois {
    private CatalogoDeHerois() {
    }

    public static List<Personagem> criarTodos() {
        return List.of(new Saci(), new Lobisomem());
    }
}
