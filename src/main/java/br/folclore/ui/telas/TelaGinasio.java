package br.folclore.ui.telas;

import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaGinasio implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final int indiceGinasio;
    private final BufferedImage fundo;

    public TelaGinasio(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao, int indiceGinasio) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        this.indiceGinasio = indiceGinasio;
        fundo = indiceGinasio == 0
                ? CarregadorDeImagens.carregar("/fundos/ginasio_fogo.png")
                : null;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        if (fundo != null) {
            g.drawImage(fundo, 0, 0, null);
        }
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
