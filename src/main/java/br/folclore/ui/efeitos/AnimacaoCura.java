package br.folclore.ui.efeitos;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics2D;

public final class AnimacaoCura implements Animacao {
    public static final int DURACAO_QUADROS = 30;
    private static final int QUANTIDADE_PARTICULAS = 7;
    private static final int RAIO_PARTICULA = 4;
    private static final int DISTANCIA_SUBIDA = 30;

    private final int x;
    private final int y;
    private final int tamanho;
    private int quadroAtual;

    public AnimacaoCura(int x, int y, int tamanho) {
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho do sprite deve ser positivo.");
        }

        this.x = x;
        this.y = y;
        this.tamanho = tamanho;
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
        Color corAnterior = g.getColor();
        Composite compositeAnterior = g.getComposite();
        g.setColor(Color.GREEN);
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacidade));

        for (int i = 0; i < QUANTIDADE_PARTICULAS; i++) {
            double fase = quadroAtual * 0.25 + i;
            int centroX = x + tamanho * (i + 1) / (QUANTIDADE_PARTICULAS + 1)
                    + (int) Math.round(4 * Math.sin(fase));
            int centroY = y + tamanho * 3 / 4 - quadroAtual - (i % 3) * 5;
            g.fillOval(centroX - RAIO_PARTICULA, centroY - RAIO_PARTICULA,
                    RAIO_PARTICULA * 2, RAIO_PARTICULA * 2);
        }

        g.setComposite(compositeAnterior);
        g.setColor(corAnterior);
    }

    @Override
    public boolean terminou() {
        return quadroAtual >= DURACAO_QUADROS;
    }
}
