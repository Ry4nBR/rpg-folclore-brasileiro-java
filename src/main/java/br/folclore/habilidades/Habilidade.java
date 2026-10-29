package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public interface Habilidade {
    String getNome();

    ResultadoAcao executar(Personagem usuario, Personagem alvo);
}
