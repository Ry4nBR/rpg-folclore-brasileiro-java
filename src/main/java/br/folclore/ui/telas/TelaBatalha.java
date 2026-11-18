package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CaixaDeTexto;
import br.folclore.ui.componentes.CarregadorDeImagens;
import br.folclore.ui.componentes.PosicoesDeBatalha;
import br.folclore.ui.componentes.RepositorioDeSprites;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class TelaBatalha implements Tela {
    private static final int CAIXA_TEXTO_X = 20;
    private static final int CAIXA_TEXTO_Y = 400;
    private static final int CAIXA_TEXTO_LARGURA = 480;
    private static final int CAIXA_TEXTO_ALTURA = 120;

    private final GerenciadorDeTelas gerenciador;
    private final Batalha batalha;
    private final Consumer<ResultadoBatalha> aoEncerrar;
    private final BufferedImage fundo;
    private final CaixaDeTexto caixaDeTexto;
    private final Botao botaoAtacar;
    private final Botao botaoHabilidade;
    private final Queue<String> filaMensagens = new ArrayDeque<>();
    private int mensagensLidasDoRegistro;
    private String mensagemAtual;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        this.fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
        this.caixaDeTexto = new CaixaDeTexto(CAIXA_TEXTO_X, CAIXA_TEXTO_Y,
                CAIXA_TEXTO_LARGURA, CAIXA_TEXTO_ALTURA);
        this.botaoAtacar = new Botao(520, 410, 200, 45, "Atacar", () -> batalha.acaoAtacar());
        this.botaoHabilidade = new Botao(740, 410, 200, 45, "Habilidade", null);
        batalha.iniciar();
        sincronizarMensagens();
        mostrarProximaMensagem();
    }

    @Override
    public void atualizar() {
        sincronizarMensagens();
        if (mensagemAtual == null) {
            mostrarProximaMensagem();
        }
        caixaDeTexto.atualizar();
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        desenharPlataformas(g);
        RepositorioDeSprites.desenhar(g, batalha.getJogador(),
                PosicoesDeBatalha.JOGADOR_X, PosicoesDeBatalha.JOGADOR_Y, true);
        RepositorioDeSprites.desenhar(g, batalha.getInimigo(),
                PosicoesDeBatalha.INIMIGO_X, PosicoesDeBatalha.INIMIGO_Y, false);
        desenharHudInimigo(g);
        desenharHudJogador(g);
        caixaDeTexto.desenhar(g);
        botaoAtacar.desenhar(g);
        botaoHabilidade.desenhar(g);
    }

    private void desenharPlataformas(Graphics2D g) {
        g.setColor(new Color(70, 55, 45, 180));
        int largura = PosicoesDeBatalha.TAMANHO_SPRITE + 32;
        int altura = 42;
        g.fillOval(PosicoesDeBatalha.JOGADOR_X - 16,
                PosicoesDeBatalha.JOGADOR_Y + PosicoesDeBatalha.TAMANHO_SPRITE - 18,
                largura, altura);
        g.fillOval(PosicoesDeBatalha.INIMIGO_X - 16,
                PosicoesDeBatalha.INIMIGO_Y + PosicoesDeBatalha.TAMANHO_SPRITE - 18,
                largura, altura);
    }

    private void desenharHudInimigo(Graphics2D g) {
        int x = 40;
        int y = 40;
        int largura = 300;
        int altura = 70;

        g.setColor(new Color(25, 30, 35, 210));
        g.fillRect(x, y, largura, altura);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, largura - 1, altura - 1);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        g.drawString(batalha.getInimigo().getNome(), x + 12, y + 20);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        g.drawString(batalha.getInimigo().getTipo().getRotulo(), x + 12, y + 39);
        BarraDeVida.desenhar(g, x + 12, y + 50, largura - 24,
                batalha.getInimigo().getPvAtual(), batalha.getInimigo().getPvMaximo());
    }

    private void desenharHudJogador(Graphics2D g) {
        int x = 600;
        int y = 330;
        int largura = 320;
        int altura = 80;

        g.setColor(new Color(25, 30, 35, 210));
        g.fillRect(x, y, largura, altura);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, largura - 1, altura - 1);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
        g.drawString(batalha.getJogador().getNome(), x + 12, y + 20);
        BarraDeVida.desenhar(g, x + 12, y + 30, largura - 24,
                batalha.getJogador().getPvAtual(), batalha.getJogador().getPvMaximo());
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        g.drawString("PV " + batalha.getJogador().getPvAtual()
                + "/" + batalha.getJogador().getPvMaximo(), x + 12, y + 68);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoAtacar.clicar(x, y);
        if (x >= CAIXA_TEXTO_X && x < CAIXA_TEXTO_X + CAIXA_TEXTO_LARGURA
                && y >= CAIXA_TEXTO_Y && y < CAIXA_TEXTO_Y + CAIXA_TEXTO_ALTURA) {
            avancarMensagem();
        }
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoAtacar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ENTER) {
            avancarMensagem();
        }
    }

    private void avancarMensagem() {
        sincronizarMensagens();
        if (!caixaDeTexto.estaCompleta()) {
            caixaDeTexto.completar();
            return;
        }
        mostrarProximaMensagem();
    }

    private void sincronizarMensagens() {
        List<String> registro = batalha.getRegistro();
        while (mensagensLidasDoRegistro < registro.size()) {
            filaMensagens.add(registro.get(mensagensLidasDoRegistro));
            mensagensLidasDoRegistro++;
        }
    }

    private void mostrarProximaMensagem() {
        mensagemAtual = filaMensagens.poll();
        if (mensagemAtual != null) {
            caixaDeTexto.definirTexto(mensagemAtual);
        }
    }
}
