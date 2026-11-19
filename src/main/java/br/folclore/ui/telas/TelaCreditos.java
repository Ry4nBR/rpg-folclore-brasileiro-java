package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Color;
import java.awt.Graphics2D;

public class TelaCreditos implements Tela {
    private final GerenciadorDeTelas gerenciador;

    public TelaCreditos(GerenciadorDeTelas gerenciador) {
        if (gerenciador == null) {
            throw new IllegalArgumentException("O gerenciador de telas não pode ser nulo.");
        }
        this.gerenciador = gerenciador;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 960, 540);
    }

    @Override
    public void aoClicar(int x, int y) {
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
