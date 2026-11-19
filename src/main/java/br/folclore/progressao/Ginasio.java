package br.folclore.progressao;

import br.folclore.modelo.Personagem;
import br.folclore.modelo.Tipo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Ginasio {
    private final String nome;
    private final Tipo elemento;
    private final List<Personagem> encontros;

    public Ginasio(String nome, Tipo elemento, List<Personagem> encontros) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome do ginásio não pode ser vazio.");
        }
        if (elemento == null) {
            throw new IllegalArgumentException("O elemento do ginásio não pode ser nulo.");
        }
        if (encontros == null || encontros.size() != 3) {
            throw new IllegalArgumentException("O ginásio deve ter exatamente três encontros.");
        }
        if (encontros.stream().anyMatch(personagem -> personagem == null)) {
            throw new IllegalArgumentException("Os encontros do ginásio não podem ser nulos.");
        }
        if (!encontros.get(2).ehChefe()) {
            throw new IllegalArgumentException("O último encontro deve ser um chefe.");
        }

        this.nome = nome;
        this.elemento = elemento;
        this.encontros = new ArrayList<>(encontros);
    }

    public String getNome() {
        return nome;
    }

    public Tipo getElemento() {
        return elemento;
    }

    public List<Personagem> getEncontros() {
        return Collections.unmodifiableList(encontros);
    }

    public Personagem getChefe() {
        return encontros.get(2);
    }

    public int getTotalDeEncontros() {
        return encontros.size();
    }
}
