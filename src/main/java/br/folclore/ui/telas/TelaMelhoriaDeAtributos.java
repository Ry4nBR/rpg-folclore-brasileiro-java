package br.folclore.ui.telas;

import br.folclore.modelo.Personagem;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaMelhoriaDeAtributos implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;

    public TelaMelhoriaDeAtributos(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 960, 540);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        g.setColor(Color.WHITE);
        String titulo = "Melhorar atributos";
        FontMetrics metricas = g.getFontMetrics();
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 60);

        Personagem titular = sessao.getEquipe().getTitular();
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Titular: " + titular.getNome(), 80, 110);
        g.drawString("XP: " + titular.getXp() + " (custo "
                + Personagem.CUSTO_MELHORIA_XP + " por ponto)", 80, 145);
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
