package br.folclore.ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelDoJogo extends JPanel {
    public PainelDoJogo() {
        setPreferredSize(new Dimension(ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA));
        setFocusable(true);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);

        Timer loop = new Timer(1000 / ConfiguracoesUi.FPS, evento -> {
            atualizar();
            repaint();
        });
        loop.start();
    }

    private void atualizar() {
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setColor(Color.BLACK);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
        graphics2D.setRenderingHint(
                RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        graphics2D.dispose();
    }
}
