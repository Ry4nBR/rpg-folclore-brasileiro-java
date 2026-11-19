package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.ConfiguracoesUi;
import br.folclore.ui.Roteiro;
import br.folclore.ui.componentes.Botao;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TelaInicial implements Tela {

    private final GerenciadorDeTelas gerenciador;
    private final Botao botaoNovoJogo;
    private final Botao botaoContinuar;
    private final Botao botaoSair;
    private int indiceSelecionado;

    public TelaInicial(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
        botaoNovoJogo = new Botao(380, 250, 200, 50, "Novo Jogo",
                () -> gerenciador.trocarPara(new TelaDialogo(
                        gerenciador,
                        this,
                        Roteiro.introducao(),
                        () -> gerenciador.trocarPara(new TelaSelecaoPersonagem(
                                gerenciador,
                                personagem -> System.out.println(personagem.getNome()))))));
        botaoContinuar = new Botao(380, 315, 200, 50, "Continuar",
                () -> System.out.println("Continuar selecionado"));
        botaoSair = new Botao(380, 380, 200, 50, "Sair", () -> System.exit(0));
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
        botaoNovoJogo.desenhar(g);
        botaoContinuar.desenhar(g);
        botaoSair.desenhar(g);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoNovoJogo.clicar(x, y);
        botaoContinuar.clicar(x, y);
        botaoSair.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoNovoJogo.atualizarHover(x, y);
        botaoContinuar.atualizarHover(x, y);
        botaoSair.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_UP) {
            indiceSelecionado = (indiceSelecionado + 2) % 3;
        } else if (codigo == KeyEvent.VK_DOWN) {
            indiceSelecionado = (indiceSelecionado + 1) % 3;
        } else if (codigo == KeyEvent.VK_ENTER) {
            switch (indiceSelecionado) {
                case 0 -> botaoNovoJogo.clicar(480, 275);
                case 1 -> botaoContinuar.clicar(480, 340);
                case 2 -> botaoSair.clicar(480, 405);
                default -> {
                }
            }
        }

        botaoNovoJogo.atualizarHover(480, indiceSelecionado == 0 ? 275 : -1);
        botaoContinuar.atualizarHover(480, indiceSelecionado == 1 ? 340 : -1);
        botaoSair.atualizarHover(480, indiceSelecionado == 2 ? 405 : -1);
    }
}
