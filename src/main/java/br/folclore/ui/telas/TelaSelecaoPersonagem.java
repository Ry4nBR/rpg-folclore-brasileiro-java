package br.folclore.ui.telas;

import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.Personagem;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.ConfiguracoesUi;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.List;
import java.util.function.Consumer;

public class TelaSelecaoPersonagem implements Tela {

    private final GerenciadorDeTelas gerenciador;
    private final Consumer<Personagem> aoConfirmar;
    private final List<Personagem> opcoes = CatalogoDeHerois.criarTodos();

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
