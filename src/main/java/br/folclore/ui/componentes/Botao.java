package br.folclore.ui.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class Botao {

    private final int x;
    private final int y;
    private final int largura;
    private final int altura;
    private final String rotulo;
    private final Runnable aoAcionar;

    public Botao(int x, int y, int largura, int altura, String rotulo, Runnable aoAcionar) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.rotulo = rotulo;
        this.aoAcionar = aoAcionar;
    }

    public void desenhar(Graphics2D g) {
        g.setColor(new Color(48, 48, 48));
        g.fillRect(x, y, largura, altura);
        g.setStroke(new BasicStroke(3));
        g.setColor(Color.WHITE);
        g.drawRect(x, y, largura - 1, altura - 1);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        FontMetrics metricas = g.getFontMetrics();
        int textoX = x + (largura - metricas.stringWidth(rotulo)) / 2;
        int textoY = y + ((altura - metricas.getHeight()) / 2) + metricas.getAscent();
        g.drawString(rotulo, textoX, textoY);
    }
}
