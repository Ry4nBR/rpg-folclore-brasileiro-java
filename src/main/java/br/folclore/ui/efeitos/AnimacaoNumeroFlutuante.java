package br.folclore.ui.efeitos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public final class AnimacaoNumeroFlutuante implements Animacao {
    private static final int DURACAO_QUADROS = 30;
    private static final int DISTANCIA_SUBIDA = 30;
    private static final Font FONTE = new Font(Font.MONOSPACED, Font.BOLD, 22);

    private final String texto;
    private final int x;
    private final int y;
    private final Color cor;
    private int quadroAtual;

    public AnimacaoNumeroFlutuante(int valor, int x, int y, boolean cura) {
        this.texto = (cura ? "+" : "-") + valor;
        this.x = x;
        this.y = y;
        this.cor = cura ? Color.GREEN : Color.RED;
    }

    @Override
    public void atualizar() {
        if (!terminou()) {
            quadroAtual++;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        float progresso = (float) quadroAtual / DURACAO_QUADROS;
        float opacidade = 1 - progresso;
        int posicaoY = y - Math.round(DISTANCIA_SUBIDA * progresso);

        Font fonteAnterior = g.getFont();
        Color corAnterior = g.getColor();
        Composite compositeAnterior = g.getComposite();
        g.setFont(FONTE);
        g.setColor(cor);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));
        FontMetrics metricas = g.getFontMetrics(FONTE);
        g.drawString(texto, x - metricas.stringWidth(texto) / 2, posicaoY);
        g.setComposite(compositeAnterior);
        g.setColor(corAnterior);
        g.setFont(fonteAnterior);
    }

    @Override
    public boolean terminou() {
        return quadroAtual >= DURACAO_QUADROS;
    }
}
