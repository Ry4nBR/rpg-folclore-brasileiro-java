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

    public boolean recrutar(Personagem personagem) {
        if (personagem == null) {
            throw new IllegalArgumentException("O personagem recrutado não pode ser nulo.");
        }
        if (membros.size() >= MAX_VAGAS
                || membros.stream().anyMatch(membro -> membro.getNome().equals(personagem.getNome()))) {
            return false;
        }

        membros.add(personagem);
        return true;
    }

    public boolean trocarTitular(int indice) {
        if (indice < 0 || indice >= membros.size() || !membros.get(indice).estaVivo()) {
            return false;
        }

        indiceTitular = indice;
        return true;
    }

    public void restaurarIndiceTitular(int indice) {
        if (indice < 0 || indice >= membros.size()) {
            throw new IllegalArgumentException("O índice do titular salvo não é válido.");
        }
        indiceTitular = indice;
    }

    public void revigorarTodos() {
        for (Personagem membro : membros) {
            membro.revigorar();
        }
    }

    public void desbloquearSlotParaTodos() {
        for (Personagem membro : membros) {
            membro.desbloquearSlot();
        }
    }
}
