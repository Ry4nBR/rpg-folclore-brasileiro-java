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

    public static Personagem criarPorNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do herói não pode ser nulo.");
        }

        return switch (nome) {
            case "Saci" -> new Saci();
            case "Lobisomem" -> new Lobisomem();
            case "Negrinho do Pastoreio" -> new NegrinhoDoPastoreio();
            default -> throw new IllegalArgumentException("Herói desconhecido: " + nome);
        };
    }
}
