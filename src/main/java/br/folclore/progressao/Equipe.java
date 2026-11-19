package br.folclore.progressao;

import br.folclore.modelo.Personagem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Equipe {
    public static final int MAX_VAGAS = 4;

    private final List<Personagem> membros = new ArrayList<>();
    private int indiceTitular = 0;

    public Equipe(Personagem inicial) {
        if (inicial == null) {
            throw new IllegalArgumentException("O membro inicial não pode ser nulo.");
        }

        membros.add(inicial);
    }

    public Personagem getTitular() {
        return membros.get(indiceTitular);
    }

    public List<Personagem> getMembros() {
        return Collections.unmodifiableList(membros);
    }
}
