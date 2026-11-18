package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Graphics2D;

public class TelaInicial implements Tela {

    private final GerenciadorDeTelas gerenciador;

    public TelaInicial(GerenciadorDeTelas gerenciador) {
        this.gerenciador = gerenciador;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
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
