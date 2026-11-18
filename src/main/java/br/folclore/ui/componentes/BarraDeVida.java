package br.folclore.ui.componentes;

import java.awt.Color;
import java.awt.Graphics2D;

public final class BarraDeVida {
    private static final int ALTURA = 12;
    private static final Color COR_FUNDO = Color.GRAY;
    private static final Color COR_PREENCHIMENTO = new Color(55, 205, 55);

    private BarraDeVida() {
    }

    public static void desenhar(Graphics2D g, int x, int y, int largura,
                                int pvAtual, int pvMaximo) {
        if (largura < 0) {
            throw new IllegalArgumentException("A largura não pode ser negativa.");
        }
        if (pvMaximo <= 0) {
            throw new IllegalArgumentException("Os pontos de vida máximos devem ser maiores que zero.");
        }

        int pvLimitado = Math.max(0, Math.min(pvAtual, pvMaximo));
        int larguraPreenchida = (int) ((long) largura * pvLimitado / pvMaximo);

        g.setColor(COR_FUNDO);
        g.fillRect(x, y, largura, ALTURA);
        g.setColor(COR_PREENCHIMENTO);
        g.fillRect(x, y, larguraPreenchida, ALTURA);
    }
}
