package br.folclore.ui;

import br.folclore.ui.telas.Tela;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PainelDoJogo extends JPanel {
    private final GerenciadorDeTelas gerenciadorDeTelas;

    public PainelDoJogo() {
        this(null);
    }

    public PainelDoJogo(Tela telaInicial) {
        gerenciadorDeTelas = new GerenciadorDeTelas();
        gerenciadorDeTelas.trocarPara(telaInicial);
        setPreferredSize(new Dimension(ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA));
        setFocusable(true);
        setDoubleBuffered(true);
        setBackground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evento) {
                gerenciadorDeTelas.aoClicar(evento.getX(), evento.getY());
                requestFocusInWindow();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent evento) {
                gerenciadorDeTelas.aoMoverMouse(evento.getX(), evento.getY());
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent evento) {
                gerenciadorDeTelas.aoPressionarTecla(evento.getKeyCode());
            }
        });

        Timer loop = new Timer(1000 / ConfiguracoesUi.FPS, evento -> {
            gerenciadorDeTelas.atualizar();
            repaint();
        });
        loop.start();
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
        try {
            gerenciadorDeTelas.desenhar(graphics2D);
        } finally {
            graphics2D.dispose();
        }
    }
}
