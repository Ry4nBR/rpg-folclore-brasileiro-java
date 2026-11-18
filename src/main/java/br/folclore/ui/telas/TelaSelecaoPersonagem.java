package br.folclore.ui.telas;

import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.Personagem;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.ConfiguracoesUi;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.function.Consumer;

public class TelaSelecaoPersonagem implements Tela {

    private final GerenciadorDeTelas gerenciador;
    private final Consumer<Personagem> aoConfirmar;
    private final List<Personagem> opcoes = CatalogoDeHerois.criarTodos();
    private int indiceSelecionado = -1;

    public TelaSelecaoPersonagem(GerenciadorDeTelas gerenciador, Consumer<Personagem> aoConfirmar) {
        this.gerenciador = gerenciador;
        this.aoConfirmar = aoConfirmar;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(new Color(25, 45, 70));
        g.fillRect(0, 0, ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 40));
        String titulo = "Escolha seu personagem";
        FontMetrics metricas = g.getFontMetrics();
        int x = (ConfiguracoesUi.LARGURA - metricas.stringWidth(titulo)) / 2;
        g.setColor(Color.WHITE);
        g.drawString(titulo, x, 70);

        int[] posicoesX = {100, 380, 660};
        g.setStroke(new java.awt.BasicStroke(3));
        g.setColor(Color.WHITE);
        for (int posicaoX : posicoesX) {
            g.fillRect(posicaoX, 110, 200, 300);
            g.setColor(Color.BLACK);
            g.drawRect(posicaoX, 110, 199, 299);
            g.setColor(Color.WHITE);
        }

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.setColor(Color.BLACK);
        for (int i = 0; i < opcoes.size(); i++) {
            Personagem personagem = opcoes.get(i);
            int posicaoX = posicoesX[i];
            g.drawString(personagem.getNome(), posicaoX + 10, 145);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            g.drawString(personagem.getTipo().getRotulo(), posicaoX + 10, 165);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        }

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        g.setColor(Color.BLACK);
        for (int i = 0; i < opcoes.size(); i++) {
            Personagem personagem = opcoes.get(i);
            int posicaoX = posicoesX[i];
            g.drawString("PV: " + personagem.getPvMaximo(), posicaoX + 10, 350);
            g.drawString("ATQ: " + personagem.getAtaque(), posicaoX + 10, 370);
            g.drawString("DEF: " + personagem.getDefesa(), posicaoX + 10, 390);
            g.drawString("VEL: " + personagem.getVelocidade(), posicaoX + 10, 410);
            g.setColor(Color.GRAY);
            g.fillRect(posicaoX + 20, 180, 160, 160);
            g.setColor(Color.DARK_GRAY);
            g.drawRect(posicaoX + 20, 180, 159, 159);
            g.setColor(Color.BLACK);
        }

        if (indiceSelecionado >= 0) {
            int posicaoX = posicoesX[indiceSelecionado];
            g.setStroke(new java.awt.BasicStroke(4));
            g.setColor(Color.YELLOW);
            g.drawRect(posicaoX - 2, 108, 204, 304);
        }
    }

    @Override
    public void aoClicar(int x, int y) {
        int[] posicoesX = {100, 380, 660};
        for (int i = 0; i < opcoes.size(); i++) {
            int posicaoX = posicoesX[i];
            if (x >= posicaoX && x <= posicaoX + 200
                    && y >= 110 && y <= 410) {
                indiceSelecionado = i;
                break;
            }
        }
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_LEFT) {
            indiceSelecionado = indiceSelecionado <= 0
                    ? opcoes.size() - 1
                    : indiceSelecionado - 1;
        } else if (codigo == KeyEvent.VK_RIGHT) {
            indiceSelecionado = (indiceSelecionado + 1) % opcoes.size();
        }
    }
}
