package br.folclore.ui.efeitos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class AnimacaoDeslocamento implements Animacao {
    private static final int DURACAO_QUADROS = 20;
    private static final int QUADROS_AVANCO = DURACAO_QUADROS / 2;
    private static final int DISTANCIA_AVANCO = 40;

    private final BufferedImage sprite;
    private final int xInicial;
    private final int yInicial;
    private final int tamanho;
    private final boolean espelhado;
    private final double direcaoX;
    private final double direcaoY;
    private int quadroAtual;

    public AnimacaoDeslocamento(BufferedImage sprite, int xInicial, int yInicial,
                                int xAlvo, int yAlvo, int tamanho, boolean espelhado) {
        if (sprite == null) {
            throw new IllegalArgumentException("O sprite não pode ser nulo.");
        }
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho do sprite deve ser positivo.");
        }

        this.sprite = sprite;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.tamanho = tamanho;
        this.espelhado = espelhado;

        double diferencaX = xAlvo - xInicial;
        double diferencaY = yAlvo - yInicial;
        double distancia = Math.hypot(diferencaX, diferencaY);
        this.direcaoX = distancia == 0 ? 0 : diferencaX / distancia;
        this.direcaoY = distancia == 0 ? 0 : diferencaY / distancia;
    }

    @Override
    public void atualizar() {
        if (!terminou()) {
            quadroAtual++;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        double progresso;
        if (quadroAtual <= QUADROS_AVANCO) {
            progresso = (double) quadroAtual / QUADROS_AVANCO;
        } else {
            progresso = (double) (DURACAO_QUADROS - quadroAtual) / QUADROS_AVANCO;
        }

        int deslocamentoX = (int) Math.round(direcaoX * DISTANCIA_AVANCO * progresso);
        int deslocamentoY = (int) Math.round(direcaoY * DISTANCIA_AVANCO * progresso);
        int x = xInicial + deslocamentoX;
        int y = yInicial + deslocamentoY;
        if (espelhado) {
            g.drawImage(sprite, x + tamanho, y, x, y + tamanho,
                    0, 0, sprite.getWidth(), sprite.getHeight(), null);
        } else {
            g.drawImage(sprite, x, y, tamanho, tamanho, null);
        }
    }

    @Override
    public boolean terminou() {
        return quadroAtual >= DURACAO_QUADROS;
    }
}
