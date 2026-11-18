package br.folclore.ui.componentes;

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
    }
}
