package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public interface Habilidade {
    String getNome();

    int getUsosMaximos();

    int getUsosRestantes();

    boolean temUsosRestantes();

    ResultadoAcao executar(Personagem usuario, Personagem alvo);
}
