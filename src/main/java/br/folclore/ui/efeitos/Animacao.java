package br.folclore.ui.efeitos;

import java.awt.Graphics2D;

public interface Animacao {
    void atualizar();

    void desenhar(Graphics2D g);

    boolean terminou();
}
