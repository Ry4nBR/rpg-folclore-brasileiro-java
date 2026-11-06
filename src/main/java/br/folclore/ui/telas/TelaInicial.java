package br.folclore.ui.telas;

import br.folclore.ui.ConfiguracoesUi;
import br.folclore.ui.componentes.Botao;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.Objects;

public class TelaInicial implements Tela {
    private static final Color COR_FUNDO = new Color(20, 70, 45);
    private static final String TITULO = "RPG do Folclore Brasileiro";
    private static final String SUBTITULO = "Ginásio do Fogo — MVP";
    private final Runnable aoJogar;
    private final Botao botaoJogar;
    private final Botao botaoSair;

    public TelaInicial(Runnable aoJogar) {
        this.aoJogar = Objects.requireNonNull(aoJogar, "A ação de jogar não pode ser nula.");
        botaoJogar = new Botao(380, 250, 200, 50, "Jogar", aoJogar);
        botaoSair = new Botao(380, 320, 200, 50, "Sair", () -> System.exit(0));
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D graphics) {
        Graphics2D contexto = (Graphics2D) graphics.create();
        try {
            contexto.setColor(COR_FUNDO);
            contexto.fillRect(0, 0, ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA);

            contexto.setColor(Color.WHITE);
            contexto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 40));
            desenharCentralizado(contexto, TITULO, 150);
            contexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 24));
            desenharCentralizado(contexto, SUBTITULO, 195);

            botaoJogar.desenhar(contexto);
            botaoSair.desenhar(contexto);
        } finally {
            contexto.dispose();
        }
    }

    private void desenharCentralizado(Graphics2D graphics, String texto, int y) {
        FontMetrics metricas = graphics.getFontMetrics();
        int x = (ConfiguracoesUi.LARGURA - metricas.stringWidth(texto)) / 2;
        graphics.drawString(texto, x, y);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoJogar.clicar(x, y);
        botaoSair.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoJogar.atualizarHover(x, y);
        botaoSair.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ENTER) {
            aoJogar.run();
        }
    }
}
