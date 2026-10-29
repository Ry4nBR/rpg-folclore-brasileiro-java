package br.folclore.modelo;

import java.util.ArrayList;
import java.util.List;

public final class CatalogoDeHerois {

    private CatalogoDeHerois() {
    }

    public static List<Personagem> criarTodos() {
        List<Personagem> herois = new ArrayList<>();
        herois.add(new Saci());
        herois.add(new Lobisomem());
        herois.add(new NegrinhoDoPastoreio());
        return herois;
    }
}
