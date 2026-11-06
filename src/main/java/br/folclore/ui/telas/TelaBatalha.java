package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Tipo;
import br.folclore.ui.ConfiguracoesUi;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.CaixaDeMensagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Objects;
import java.util.function.Consumer;

public class TelaBatalha implements Tela {
    private static final Color COR_CEU = new Color(34, 48, 86);
    private static final Color COR_CHAO = new Color(69, 84, 55);
    private static final Color COR_HUD = new Color(20, 25, 35, 220);
    private static final Color COR_VENTO = new Color(0, 210, 220);
    private static final Color COR_FISICO = new Color(240, 130, 45);
    private static final Color COR_FOGO = new Color(210, 55, 45);

    private final Batalha batalha;
    private final Consumer<ResultadoBatalha> aoEncerrar;
    private final CaixaDeMensagens caixaDeMensagens = new CaixaDeMensagens(20, 410, 480, 110);
    private final Queue<String> fila = new ArrayDeque<>();

    public TelaBatalha(Batalha batalha, Consumer<ResultadoBatalha> aoEncerrar) {
        this.batalha = Objects.requireNonNull(batalha, "A batalha não pode ser nula.");
        this.aoEncerrar = Objects.requireNonNull(aoEncerrar, "A ação de encerramento não pode ser nula.");
        absorverMensagens();
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D graphics) {
        Graphics2D contexto = (Graphics2D) graphics.create();
        try {
            desenharCenario(contexto);
            desenharPlataforma(contexto, 90, 390, 300, 40);
            desenharPlataforma(contexto, 570, 285, 330, 64);

            desenharCombatente(contexto, batalha.getJogador(), 140, 230, 150, 180);
            desenharCombatente(contexto, batalha.getChefe(), 640, 80, 170, 200);

            desenharHud(contexto, batalha.getChefe(), 40, 40, 320, 70);
            desenharHud(contexto, batalha.getJogador(), 600, 320, 320, 80);
            caixaDeMensagens.desenhar(contexto);
        } finally {
            contexto.dispose();
        }
    }

    private void desenharCenario(Graphics2D graphics) {
        graphics.setPaint(new GradientPaint(0, 0, COR_CEU, 0, ConfiguracoesUi.ALTURA, COR_CHAO));
        graphics.fillRect(0, 0, ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA);
    }

    private void desenharPlataforma(Graphics2D graphics, int x, int y, int largura, int altura) {
        graphics.setColor(new Color(24, 34, 30, 190));
        graphics.fillOval(x, y, largura, altura);
        graphics.setColor(new Color(205, 190, 130));
        graphics.drawOval(x, y, largura, altura);
    }

    private void desenharCombatente(Graphics2D graphics, Personagem personagem,
                                    int x, int y, int largura, int altura) {
        graphics.setColor(corDoTipo(personagem.getTipo()));
        graphics.fillRoundRect(x, y, largura, altura, 18, 18);
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(x, y, largura, altura, 18, 18);

        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        FontMetrics metricas = graphics.getFontMetrics();
        int textoX = x + (largura - metricas.stringWidth(personagem.getNome())) / 2;
        int textoY = y + altura / 2;
        graphics.drawString(personagem.getNome(), textoX, textoY);
    }

    private void desenharHud(Graphics2D graphics, Personagem personagem,
                             int x, int y, int largura, int altura) {
        graphics.setColor(COR_HUD);
        graphics.fillRoundRect(x, y, largura, altura, 14, 14);
        graphics.setColor(Color.WHITE);
        graphics.drawRoundRect(x, y, largura, altura, 14, 14);

        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 16));
        graphics.drawString(personagem.getNome() + " (" + personagem.getTipo().getRotulo() + ")",
                x + 12, y + 23);
        BarraDeVida.desenhar(graphics, x + 12, y + 35, largura - 24, 22,
                personagem.getPvAtual(), personagem.getPvMaximo());
    }

    private Color corDoTipo(Tipo tipo) {
        return switch (tipo) {
            case VENTO -> COR_VENTO;
            case FISICO -> COR_FISICO;
            case FOGO -> COR_FOGO;
        };
    }

    @Override
    public void aoClicar(int x, int y) {
        avancarMensagem();
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ENTER || codigo == KeyEvent.VK_SPACE) {
            avancarMensagem();
        }
    }

    private void absorverMensagens() {
        fila.addAll(batalha.consumirMensagens());
        atualizarMensagem();
    }

    private void avancarMensagem() {
        if (!fila.isEmpty()) {
            fila.remove();
            atualizarMensagem();
        }
    }

    private void atualizarMensagem() {
        String mensagem = fila.peek();
        caixaDeMensagens.definirTexto(mensagem == null ? "" : mensagem);
    }

    private boolean podeAgir() {
        return fila.isEmpty();
    }
}
