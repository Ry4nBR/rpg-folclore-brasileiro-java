package br.folclore.ui.telas;

import java.awt.Graphics2D;

public interface Tela {
    void atualizar();

    void desenhar(Graphics2D graphics);

    void aoClicar(int x, int y);

    void aoMoverMouse(int x, int y);

    void aoPressionarTecla(int codigo);
}
