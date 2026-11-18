package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.CarregadorDeImagens;
import br.folclore.ui.componentes.PosicoesDeBatalha;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class TelaBatalha implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Batalha batalha;
    private final Consumer<ResultadoBatalha> aoEncerrar;
    private final BufferedImage fundo;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        this.fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
        batalha.iniciar();
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        desenharPlataformas(g);
        desenharHudInimigo(g);
    }

    private void desenharPlataformas(Graphics2D g) {
        g.setColor(new Color(70, 55, 45, 180));
        int largura = PosicoesDeBatalha.TAMANHO_SPRITE + 32;
        int altura = 42;
        g.fillOval(PosicoesDeBatalha.JOGADOR_X - 16,
                PosicoesDeBatalha.JOGADOR_Y + PosicoesDeBatalha.TAMANHO_SPRITE - 18,
                largura, altura);
        g.fillOval(PosicoesDeBatalha.INIMIGO_X - 16,
                PosicoesDeBatalha.INIMIGO_Y + PosicoesDeBatalha.TAMANHO_SPRITE - 18,
                largura, altura);
    }

    private void desenharHudInimigo(Graphics2D g) {
        int x = 40;
        int y = 40;
        int largura = 300;
        int altura = 70;

        g.setColor(new Color(25, 30, 35, 210));
        g.fillRect(x, y, largura, altura);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, largura - 1, altura - 1);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        g.drawString(batalha.getInimigo().getNome(), x + 12, y + 20);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        g.drawString(batalha.getInimigo().getTipo().getRotulo(), x + 12, y + 39);
        BarraDeVida.desenhar(g, x + 12, y + 50, largura - 24,
                batalha.getInimigo().getPvAtual(), batalha.getInimigo().getPvMaximo());
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
