package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CaixaDeTexto;
import br.folclore.ui.componentes.LinhaDeDialogo;
import br.folclore.ui.componentes.RepositorioDeRetratos;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;

public class TelaDialogo implements Tela {
    private static final int CAIXA_X = 40;
    private static final int CAIXA_Y = 380;
    private static final int CAIXA_LARGURA = 880;
    private static final int CAIXA_ALTURA = 140;

    private final GerenciadorDeTelas gerenciador;
    private final Tela telaDeFundo;
    private final List<LinhaDeDialogo> linhas;
    private final Runnable aoTerminar;
    private final CaixaDeTexto caixa;
    private int indiceLinhaAtual;
    private boolean aoTerminarExecutado;
    private int framesIndicador;

    public TelaDialogo(GerenciadorDeTelas gerenciador, Tela telaDeFundo,
                       List<LinhaDeDialogo> linhas, Runnable aoTerminar) {
        if (gerenciador == null) {
            throw new IllegalArgumentException("O gerenciador de telas não pode ser nulo.");
        }
        if (linhas == null || linhas.isEmpty()) {
            throw new IllegalArgumentException("O diálogo deve conter ao menos uma linha.");
        }
        if (aoTerminar == null) {
            throw new IllegalArgumentException("A ação de término não pode ser nula.");
        }

        this.gerenciador = gerenciador;
        this.telaDeFundo = telaDeFundo;
        this.linhas = List.copyOf(linhas);
        this.aoTerminar = aoTerminar;
        caixa = new CaixaDeTexto(CAIXA_X, CAIXA_Y, CAIXA_LARGURA, CAIXA_ALTURA);
        caixa.definirTexto(this.linhas.get(indiceLinhaAtual).getTexto());
        definirRetratoDaLinhaAtual();
    }

    @Override
    public void atualizar() {
        caixa.atualizar();
        if (caixa.estaCompleta()) {
            framesIndicador++;
        } else {
            framesIndicador = 0;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        if (telaDeFundo == null) {
            g.setColor(Color.DARK_GRAY);
            g.fillRect(0, 0, 960, 540);
        } else {
            telaDeFundo.desenhar(g);
        }
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, 960, 540);
        caixa.desenhar(g);

        String falante = linhas.get(indiceLinhaAtual).getFalante();
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        FontMetrics metricas = g.getFontMetrics();
        int larguraEtiqueta = metricas.stringWidth(falante) + 24;
        int etiquetaX = CAIXA_X + 18;
        int etiquetaY = CAIXA_Y - 34;
        g.setColor(new Color(20, 20, 20, 235));
        g.fillRoundRect(etiquetaX, etiquetaY, larguraEtiqueta, 32, 8, 8);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(2f));
        g.drawRoundRect(etiquetaX, etiquetaY, larguraEtiqueta, 32, 8, 8);
        g.drawString(falante, etiquetaX + 12, etiquetaY + 22);
        if (caixa.estaCompleta() && (framesIndicador / 30) % 2 == 0) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
            g.setColor(Color.BLACK);
            g.drawString("▼", CAIXA_X + CAIXA_LARGURA - 38,
                    CAIXA_Y + CAIXA_ALTURA - 12);
        }
    }

    @Override
    public void aoClicar(int x, int y) {
        avancar();
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ENTER) {
            avancar();
        }
    }

    private void definirRetratoDaLinhaAtual() {
        String idRetrato = linhas.get(indiceLinhaAtual).getIdRetrato();
        caixa.definirRetrato(idRetrato == null ? null : RepositorioDeRetratos.obter(idRetrato));
    }

    private void avancar() {
        if (!caixa.estaCompleta()) {
            caixa.completar();
            return;
        }
        if (indiceLinhaAtual + 1 < linhas.size()) {
            indiceLinhaAtual++;
            caixa.definirTexto(linhas.get(indiceLinhaAtual).getTexto());
            definirRetratoDaLinhaAtual();
            return;
        }
        if (!aoTerminarExecutado) {
            aoTerminarExecutado = true;
            aoTerminar.run();
        }
    }
}
