package br.folclore.ui.efeitos;

import java.awt.Color;
import java.awt.Graphics2D;

public final class AnimacaoParticulas implements Animacao {
    public static final int DURACAO_QUADROS = 25;
    private static final int QUANTIDADE_PARTICULAS = 8;
    private static final int RAIO_MINIMO = 3;
    private static final int AMPLITUDE_RAJADA = 14;

    private final Color cor;
    private final int xInicial;
    private final int yInicial;
    private final int xAlvo;
    private final int yAlvo;
    private int quadroAtual;

    public AnimacaoParticulas(Color cor, int xInicial, int yInicial, int xAlvo, int yAlvo) {
        if (cor == null) {
            throw new IllegalArgumentException("A cor das partículas não pode ser nula.");
        }

        this.cor = cor;
        this.xInicial = xInicial;
        this.yInicial = yInicial;
        this.xAlvo = xAlvo;
        this.yAlvo = yAlvo;
    }

    @Override
    public void atualizar() {
        if (!terminou()) {
            quadroAtual++;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        double progresso = (double) quadroAtual / DURACAO_QUADROS;
        double centroX = xInicial + (xAlvo - xInicial) * progresso;
        double centroY = yInicial + (yAlvo - yInicial) * progresso;
        double amplitude = AMPLITUDE_RAJADA * Math.sin(Math.PI * progresso);

        Color corAnterior = g.getColor();
        g.setColor(cor);
        for (int i = 0; i < QUANTIDADE_PARTICULAS; i++) {
            double angulo = 2 * Math.PI * i / QUANTIDADE_PARTICULAS;
            int x = (int) Math.round(centroX + Math.cos(angulo) * amplitude);
            int y = (int) Math.round(centroY + Math.sin(angulo) * amplitude);
            int raio = RAIO_MINIMO + i % 3;
            g.fillOval(x - raio, y - raio, raio * 2, raio * 2);
        }
        g.setColor(corAnterior);
    }

    @Override
    public boolean terminou() {
        return quadroAtual >= DURACAO_QUADROS;
    }
}
