package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Graphics2D;
import java.util.function.Consumer;

public class TelaBatalha implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Batalha batalha;
    private final Consumer<ResultadoBatalha> aoEncerrar;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        batalha.iniciar();
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
