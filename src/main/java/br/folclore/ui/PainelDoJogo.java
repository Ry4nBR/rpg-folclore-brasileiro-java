package br.folclore.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelDoJogo extends JPanel {

    private final GerenciadorDeTelas gerenciadorDeTelas;

    public PainelDoJogo() {
        gerenciadorDeTelas = new GerenciadorDeTelas();
        setPreferredSize(new Dimension(ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA));
        setFocusable(true);
        setDoubleBuffered(true);
        Timer timer = new Timer(1000 / ConfiguracoesUi.FPS, event -> {
            gerenciadorDeTelas.atualizar();
            repaint();
        });
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D g2d = (Graphics2D) graphics.create();
        try {
            g2d.setColor(Color.BLACK);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setRenderingHint(
                    RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_OFF);
            g2d.setRenderingHint(
                    RenderingHints.KEY_INTERPOLATION,
                    RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
            gerenciadorDeTelas.desenhar(g2d);
        } finally {
            g2d.dispose();
        }
    }
}
