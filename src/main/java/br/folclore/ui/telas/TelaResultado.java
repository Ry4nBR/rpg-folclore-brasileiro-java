package br.folclore.ui.telas;

import br.folclore.ui.ConfiguracoesUi;
import br.folclore.ui.componentes.Botao;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.Objects;

public class TelaResultado implements Tela {
    private static final Color COR_FUNDO = new Color(20, 70, 45);
    private static final Color COR_VITORIA = new Color(55, 205, 95);
    private static final Color COR_DERROTA = new Color(225, 65, 65);

    private final boolean vitoria;
    private final Botao botaoMenu;
    private final Botao botaoTentarNovamente;

    public TelaResultado(boolean vitoria, Runnable aoMenu, Runnable aoTentarNovamente) {
        this.vitoria = vitoria;
        Objects.requireNonNull(aoMenu, "A ação de voltar ao menu não pode ser nula.");
        Objects.requireNonNull(aoTentarNovamente, "A ação de tentar novamente não pode ser nula.");
        botaoMenu = new Botao(360, 380, 240, 50, "Voltar ao menu", aoMenu);
        botaoTentarNovamente = vitoria ? null
                : new Botao(360, 320, 240, 50, "Tentar novamente", aoTentarNovamente);
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

            contexto.setColor(vitoria ? COR_VITORIA : COR_DERROTA);
            contexto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 48));
            desenharCentralizado(contexto, vitoria ? "Vitória!" : "Derrota", 205);

            contexto.setColor(Color.WHITE);
            contexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 22));
            desenharCentralizado(contexto,
                    vitoria ? "Ginásio do Fogo concluído!" : "Você foi derrotado. Tente novamente!",
                    255);

            if (botaoTentarNovamente != null) {
                botaoTentarNovamente.desenhar(contexto);
            }
            botaoMenu.desenhar(contexto);
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
        if (botaoTentarNovamente != null) {
            botaoTentarNovamente.clicar(x, y);
        }
        botaoMenu.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        if (botaoTentarNovamente != null) {
            botaoTentarNovamente.atualizarHover(x, y);
        }
        botaoMenu.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
