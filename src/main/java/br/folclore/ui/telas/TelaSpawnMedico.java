package br.folclore.ui.telas;

import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaSpawnMedico implements Tela {
    private final BufferedImage fundo;

    public TelaSpawnMedico(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
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
