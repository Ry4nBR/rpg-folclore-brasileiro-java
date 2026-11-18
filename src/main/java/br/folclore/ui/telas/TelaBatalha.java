package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.function.Consumer;

public class TelaBatalha implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Batalha batalha;
    private final Consumer<ResultadoBatalha> aoEncerrar;
    private final BufferedImage fundo;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        this.fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
        batalha.iniciar();
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
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
