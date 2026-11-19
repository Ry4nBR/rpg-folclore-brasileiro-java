package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TelaDerrota implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Runnable aoRecomecar;

    public TelaDerrota(GerenciadorDeTelas gerenciador, Runnable aoRecomecar) {
        if (gerenciador == null || aoRecomecar == null) {
            throw new IllegalArgumentException("Gerenciador e callback são obrigatórios.");
        }
        this.gerenciador = gerenciador;
        this.aoRecomecar = aoRecomecar;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 960, 540);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        String titulo = "Derrota";
        FontMetrics metricas = g.getFontMetrics();
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 180);
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
