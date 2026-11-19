package br.folclore.ui.telas;

import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Graphics2D;

public class TelaMelhoriaDeAtributos implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;

    public TelaMelhoriaDeAtributos(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
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
