package br.folclore.ui.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Objects;

public class Botao {
    private static final Color COR_NORMAL = new Color(45, 70, 90);
    private static final Color COR_HOVER = new Color(70, 130, 180);
    private static final int TAMANHO_FONTE_ROTULO = 20;
    private static final int TAMANHO_FONTE_MINIMO = 10;
    private static final int MARGEM_ROTULO = 12;

    private final int x;
    private final int y;
    private final int largura;
    private final int altura;
    private String rotulo;
    private final Runnable aoAcionar;
    private boolean habilitado = true;
    private boolean sobMouse;

    public Botao(int x, int y, int largura, int altura, String rotulo, Runnable aoAcionar) {
        if (largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException("A largura e a altura devem ser positivas.");
        }
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.rotulo = Objects.requireNonNull(rotulo, "O rótulo não pode ser nulo.");
        this.aoAcionar = Objects.requireNonNull(aoAcionar, "A ação não pode ser nula.");
    }

    public void desenhar(Graphics2D graphics) {
        Graphics2D contexto = (Graphics2D) graphics.create();
        try {
            contexto.setColor(!habilitado ? Color.GRAY : sobMouse ? COR_HOVER : COR_NORMAL);
            contexto.fillRect(x, y, largura, altura);

            contexto.setStroke(new BasicStroke(3));
            contexto.setColor(habilitado ? Color.WHITE : Color.DARK_GRAY);
            contexto.drawRect(x, y, largura - 1, altura - 1);

            contexto.setFont(fonteDoRotulo(contexto));
            FontMetrics metricas = contexto.getFontMetrics();
            int textoX = x + (largura - metricas.stringWidth(rotulo)) / 2;
            int textoY = y + (altura - metricas.getHeight()) / 2 + metricas.getAscent();
            contexto.drawString(rotulo, textoX, textoY);
        } finally {
            contexto.dispose();
        }
    }

    private Font fonteDoRotulo(Graphics2D graphics) {
        int tamanho = TAMANHO_FONTE_ROTULO;
        Font fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanho);
        while (tamanho > TAMANHO_FONTE_MINIMO
                && graphics.getFontMetrics(fonte).stringWidth(rotulo) > largura - MARGEM_ROTULO * 2) {
            fonte = new Font(Font.MONOSPACED, Font.BOLD, --tamanho);
        }
        return fonte;
    }

    public boolean contem(int mouseX, int mouseY) {
        return mouseX >= x && mouseX < x + largura
                && mouseY >= y && mouseY < y + altura;
    }

    public void atualizarHover(int mouseX, int mouseY) {
        sobMouse = contem(mouseX, mouseY);
    }

    public void clicar(int mouseX, int mouseY) {
        if (habilitado && contem(mouseX, mouseY)) {
            aoAcionar.run();
        }
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public void setRotulo(String rotulo) {
        this.rotulo = Objects.requireNonNull(rotulo, "O rótulo não pode ser nulo.");
    }
}
