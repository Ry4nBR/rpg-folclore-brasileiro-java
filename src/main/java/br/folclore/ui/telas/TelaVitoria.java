package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.habilidades.Habilidade;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaVitoria implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Batalha batalha;
    private final Runnable aoContinuar;
    private final BufferedImage fundo;

    public TelaVitoria(GerenciadorDeTelas gerenciador, Batalha batalha, Runnable aoContinuar) {
        if (gerenciador == null || batalha == null || aoContinuar == null) {
            throw new IllegalArgumentException("Gerenciador, batalha e callback são obrigatórios.");
        }
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoContinuar = aoContinuar;
        fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 48));
        String titulo = "Vitória!";
        FontMetrics metricas = g.getFontMetrics();
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 180);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        String experiencia = "+" + batalha.getXpDaVitoria() + " XP";
        metricas = g.getFontMetrics();
        g.drawString(experiencia, (960 - metricas.stringWidth(experiencia)) / 2, 235);

        if (!batalha.getInimigo().getHabilidades().isEmpty()) {
            Habilidade habilidade = batalha.getInimigo().getHabilidades().get(0);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            String poderLiberado = "Novo poder liberado: " + habilidade.getNome();
            metricas = g.getFontMetrics();
            g.drawString(poderLiberado, (960 - metricas.stringWidth(poderLiberado)) / 2, 295);
        }
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
