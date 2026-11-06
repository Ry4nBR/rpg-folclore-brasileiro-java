package br.folclore.ui.componentes;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public final class BarraDeVida {
    private static final Color COR_FUNDO = Color.GRAY;
    private static final Color COR_ALTA = new Color(40, 180, 70);
    private static final Color COR_MEDIA = new Color(245, 205, 45);
    private static final Color COR_BAIXA = new Color(210, 45, 45);

    private BarraDeVida() {
    }

    public static void desenhar(Graphics2D graphics, int x, int y, int largura, int altura,
                                int pvAtual, int pvMaximo) {
        graphics.setColor(COR_FUNDO);
        graphics.fillRect(x, y, largura, altura);

        int pvLimitado = pvMaximo > 0 ? Math.max(0, Math.min(pvAtual, pvMaximo)) : 0;
        if (pvMaximo > 0 && largura > 0 && altura > 0) {
            long porcentagem = (long) pvLimitado * 100 / pvMaximo;
            int larguraPreenchida = (int) ((long) largura * pvLimitado / pvMaximo);
            graphics.setColor(corDaVida(porcentagem));
            graphics.fillRect(x, y, larguraPreenchida, altura);
        }

        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 14));
        FontMetrics metricas = graphics.getFontMetrics();
        String texto = "PV " + pvAtual + "/" + pvMaximo;
        int textoX = x + (largura - metricas.stringWidth(texto)) / 2;
        int textoY = y + (altura - metricas.getHeight()) / 2 + metricas.getAscent();
        graphics.setColor(Color.BLACK);
        graphics.drawString(texto, textoX, textoY);
    }

    private static Color corDaVida(long porcentagem) {
        if (porcentagem > 50) {
            return COR_ALTA;
        }
        if (porcentagem > 20) {
            return COR_MEDIA;
        }
        return COR_BAIXA;
    }
}
