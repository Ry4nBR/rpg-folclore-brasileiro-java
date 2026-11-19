package br.folclore.ui.telas;

import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaSpawnMedico implements Tela {
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;
    private final Botao botaoCurar;

    public TelaSpawnMedico(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
        botaoCurar = new Botao(380, 400, 200, 50, "Revigorar equipe", null);
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
        String titulo = "Spawn Médico";
        FontMetrics metricas = g.getFontMetrics();
        g.setColor(Color.WHITE);
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 70);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Titular: " + sessao.getEquipe().getTitular().getNome(), 325, 300);
        g.drawString("PV: " + sessao.getEquipe().getTitular().getPvAtual()
                + "/" + sessao.getEquipe().getTitular().getPvMaximo(), 325, 330);
        botaoCurar.desenhar(g);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoCurar.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoCurar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
