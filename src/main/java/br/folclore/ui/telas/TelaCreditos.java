package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public class TelaCreditos implements Tela {
    private static final int DURACAO_ROLAGEM = 900;
    private final GerenciadorDeTelas gerenciador;
    private int deslocamentoVertical;
    private boolean finalizado;

    public TelaCreditos(GerenciadorDeTelas gerenciador) {
        if (gerenciador == null) {
            throw new IllegalArgumentException("O gerenciador de telas não pode ser nulo.");
        }
        this.gerenciador = gerenciador;
    }

    @Override
    public void atualizar() {
        if (deslocamentoVertical < DURACAO_ROLAGEM) {
            deslocamentoVertical++;
        }
        if (deslocamentoVertical >= DURACAO_ROLAGEM) {
            voltarParaTelaInicial();
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 960, 540);
        g.setColor(Color.WHITE);
        desenharLinhaCentralizada(g, "RPG do Folclore Brasileiro", 30, 600 - deslocamentoVertical);
        desenharLinhaCentralizada(g, "Ryan Amorim (Back-end, POO, Lógica de Batalha, IA)",
                20, 690 - deslocamentoVertical);
        desenharLinhaCentralizada(g, "Guilherme Aparecido (Front-end, UI, Save, Áudio)",
                20, 780 - deslocamentoVertical);
        desenharLinhaCentralizada(g, "Inspirado no folclore brasileiro",
                20, 870 - deslocamentoVertical);
    }

    @Override
    public void aoClicar(int x, int y) {
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ESCAPE || codigo == KeyEvent.VK_ENTER) {
            voltarParaTelaInicial();
        }
    }

    private void desenharLinhaCentralizada(Graphics2D g, String texto, int tamanhoFonte, int y) {
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte));
        FontMetrics metricas = g.getFontMetrics();
        g.drawString(texto, (960 - metricas.stringWidth(texto)) / 2, y);
    }

    private void voltarParaTelaInicial() {
        if (!finalizado) {
            finalizado = true;
            gerenciador.trocarPara(new TelaInicial(gerenciador));
        }
    }
}
