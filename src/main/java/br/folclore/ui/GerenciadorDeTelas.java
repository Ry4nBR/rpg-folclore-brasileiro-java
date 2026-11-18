package br.folclore.ui;

import br.folclore.ui.telas.Tela;
import java.awt.Graphics2D;

public class GerenciadorDeTelas {

    private Tela telaAtual;

    public void trocarPara(Tela nova) {
        telaAtual = nova;
    }

    public void atualizar() {
        if (telaAtual != null) {
            telaAtual.atualizar();
        }
    }

    public void desenhar(Graphics2D g) {
        if (telaAtual != null) {
            telaAtual.desenhar(g);
        }
    }

    public void aoClicar(int x, int y) {
        if (telaAtual != null) {
            telaAtual.aoClicar(x, y);
        }
    }

    public void aoMoverMouse(int x, int y) {
        if (telaAtual != null) {
            telaAtual.aoMoverMouse(x, y);
        }
    }

    public void aoPressionarTecla(int codigo) {
        if (telaAtual != null) {
            telaAtual.aoPressionarTecla(codigo);
        }
    }
}
