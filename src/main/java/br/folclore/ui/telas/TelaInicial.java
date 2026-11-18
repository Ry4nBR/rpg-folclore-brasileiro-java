package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.ConfiguracoesUi;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class TelaInicial implements Tela {

    private final GerenciadorDeTelas gerenciador;

    public TelaInicial(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(new Color(20, 70, 30));
        g.fillRect(0, 0, ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        String titulo = "RPG do Folclore Brasileiro";
        FontMetrics metricas = g.getFontMetrics();
        int x = (ConfiguracoesUi.LARGURA - metricas.stringWidth(titulo)) / 2;
        g.setColor(Color.WHITE);
        g.drawString(titulo, x, 180);
    }

    @Override
    public void aoClicar(int x, int y) {
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
