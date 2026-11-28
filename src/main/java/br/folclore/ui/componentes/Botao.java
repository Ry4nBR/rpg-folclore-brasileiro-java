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
    private final String detalhe;
    private final Runnable aoAcionar;
    private boolean habilitado = true;
    private boolean sobMouse;

    public Botao(int x, int y, int largura, int altura, String rotulo, Runnable aoAcionar) {
        this(x, y, largura, altura, rotulo, null, aoAcionar);
    }

    public Botao(int x, int y, int largura, int altura, String rotulo, String detalhe, Runnable aoAcionar) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
        this.rotulo = rotulo;
        this.detalhe = detalhe;
        this.aoAcionar = aoAcionar;
    }

    public void desenhar(Graphics2D g) {
        Color corFundo;
        if (!habilitado) {
            corFundo = Color.GRAY;
        } else if (sobMouse) {
            corFundo = new Color(70, 130, 180);
        } else {
            corFundo = new Color(48, 48, 48);
        }
        g.setColor(corFundo);
        g.fillRect(x, y, largura, altura);
        g.setStroke(new BasicStroke(3));
        g.setColor(habilitado ? Color.WHITE : Color.LIGHT_GRAY);
        g.drawRect(x, y, largura - 1, altura - 1);

        if (detalhe != null) {
            desenharTexto(g, rotulo, 16, y + 18);
            desenharTexto(g, detalhe, 12, y + 36);
            return;
        }
        int tamanhoFonte = Math.min(20, altura - 6);
        while (tamanhoFonte > 10 && g.getFontMetrics(new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte))
                .stringWidth(rotulo) > largura - 16) {
            tamanhoFonte--;
        }
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte));
        FontMetrics metricas = g.getFontMetrics();
        int textoX = x + (largura - metricas.stringWidth(rotulo)) / 2;
        int textoY = y + ((altura - metricas.getHeight()) / 2) + metricas.getAscent();
        g.drawString(rotulo, textoX, textoY);
    }

    private void desenharTexto(Graphics2D g, String texto, int tamanho, int linhaBase) {
        Font fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanho);
        while (tamanho > 10 && g.getFontMetrics(fonte).stringWidth(texto) > largura - 12) {
            fonte = new Font(Font.MONOSPACED, Font.BOLD, --tamanho);
        }
        g.setFont(fonte);
        g.drawString(texto, x + (largura - g.getFontMetrics().stringWidth(texto)) / 2, linhaBase);
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public boolean contem(int x, int y) {
        return x >= this.x && x <= this.x + largura
                && y >= this.y && y <= this.y + altura;
    }

    public void clicar(int x, int y) {
        if (habilitado && contem(x, y) && aoAcionar != null) {
            aoAcionar.run();
        }
    }

    public void atualizarHover(int x, int y) {
        sobMouse = habilitado && contem(x, y);
    }
}
