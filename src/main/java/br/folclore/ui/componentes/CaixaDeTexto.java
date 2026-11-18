package br.folclore.ui.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

public final class CaixaDeTexto {
    private static final int RAIO_CANTO = 18;
    private static final int RECUO_BORDA_INTERNA = 4;

    private final int x;
    private final int y;
    private final int largura;
    private final int altura;

    public CaixaDeTexto(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }

    public void desenhar(Graphics2D g) {
        Graphics2D contexto = (Graphics2D) g.create();
        try {
            contexto.setColor(Color.WHITE);
            contexto.fillRoundRect(x, y, largura, altura, RAIO_CANTO, RAIO_CANTO);

            contexto.setColor(Color.BLACK);
            contexto.setStroke(new BasicStroke(2f));
            contexto.drawRoundRect(x, y, largura - 1, altura - 1,
                    RAIO_CANTO, RAIO_CANTO);
            contexto.drawRoundRect(x + RECUO_BORDA_INTERNA, y + RECUO_BORDA_INTERNA,
                    largura - RECUO_BORDA_INTERNA * 2 - 1,
                    altura - RECUO_BORDA_INTERNA * 2 - 1,
                    RAIO_CANTO - RECUO_BORDA_INTERNA,
                    RAIO_CANTO - RECUO_BORDA_INTERNA);
        } finally {
            contexto.dispose();
        }
    }
}
