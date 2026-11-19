package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaGinasio implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final int indiceGinasio;
    private final BufferedImage fundo;
    private final Botao botaoDesafiar;

    public TelaGinasio(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao, int indiceGinasio) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        this.indiceGinasio = indiceGinasio;
        fundo = indiceGinasio == 0
                ? CarregadorDeImagens.carregar("/fundos/ginasio_fogo.png")
                : indiceGinasio == 1
                        ? CarregadorDeImagens.carregar("/fundos/ginasio_planta.png")
                        : indiceGinasio == 2
                                ? CarregadorDeImagens.carregar("/fundos/ginasio_agua.png")
                                : null;
        String rotuloDesafio = sessao.getEncontrosVencidos() == 2
                ? "Enfrentar o Chefe"
                : "Desafiar";
        botaoDesafiar = new Botao(380, 400, 200, 50, rotuloDesafio, this::desafiar);
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        if (fundo != null) {
            g.drawImage(fundo, 0, 0, null);
        }
        if (sessao.getGinasioAtual() != null) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
            g.setColor(Color.WHITE);
            String nome = sessao.getGinasioAtual().getNome();
            FontMetrics metricas = g.getFontMetrics();
            g.drawString(nome, (960 - metricas.stringWidth(nome)) / 2, 70);

            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
            String progresso = "Desafio " + (sessao.getEncontrosVencidos() + 1) + " de 3";
            metricas = g.getFontMetrics();
            g.drawString(progresso, (960 - metricas.stringWidth(progresso)) / 2, 105);
        }
        botaoDesafiar.desenhar(g);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoDesafiar.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoDesafiar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }

    private void desafiar() {
        Batalha batalha = new Batalha(
                sessao.getEquipe().getTitular(),
                sessao.getProximoEncontro());
        gerenciador.trocarPara(new TelaBatalha(gerenciador, batalha, this::processarResultado));
    }

    private void processarResultado(ResultadoBatalha resultado) {
        if (resultado == ResultadoBatalha.VITORIA) {
            boolean ginasioVencido = sessao.registrarVitoriaNoEncontro();
            if (ginasioVencido) {
                gerenciador.trocarPara(new TelaSpawnMedico(gerenciador, sessao));
            } else {
                gerenciador.trocarPara(new TelaGinasio(gerenciador, sessao, indiceGinasio));
            }
        } else if (resultado == ResultadoBatalha.FUGA) {
            gerenciador.trocarPara(new TelaGinasio(gerenciador, sessao, indiceGinasio));
        }
    }
}
