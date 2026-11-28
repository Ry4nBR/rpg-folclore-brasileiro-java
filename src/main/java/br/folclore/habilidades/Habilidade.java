package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public interface Habilidade {
    String getNome();

    ResultadoAcao executar(Personagem usuario, Personagem alvo);

    int getUsosMaximos();

    int getUsosRestantes();

    boolean temUsosRestantes();

    void restaurarUsos();

    default int preverDano(Personagem usuario, Personagem alvo) {
        return 0;
    }

    default String descreverEfeito(Personagem usuario, Personagem alvo) {
        return "Dano: " + preverDano(usuario, alvo);
    }
}
