package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.habilidades.Habilidade;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CaixaDeTexto;
import br.folclore.ui.componentes.CarregadorDeImagens;
import br.folclore.ui.componentes.PosicoesDeBatalha;
import br.folclore.ui.componentes.RepositorioDeSprites;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayDeque;
import java.util.ArrayList;
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
    private final Botao botaoVerStatus;
    private final Botao botaoFugir;
    private final Botao botaoFecharStatus;
    private final Botao botaoVoltarHabilidades;
    private final List<Botao> botoesHabilidade = new ArrayList<>();
    private final List<String> rotulosHabilidade = new ArrayList<>();
    private final Queue<String> filaMensagens = new ArrayDeque<>();
    private int mensagensLidasDoRegistro;
    private String mensagemAtual;
    private boolean exibindoSubmenuHabilidades;
    private boolean exibindoPainelStatus;
    private boolean notificacaoEncerramentoEnviada;
    private String textoStatus;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        this.fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
        this.caixaDeTexto = new CaixaDeTexto(CAIXA_TEXTO_X, CAIXA_TEXTO_Y,
                CAIXA_TEXTO_LARGURA, CAIXA_TEXTO_ALTURA);
        this.botaoAtacar = new Botao(520, 410, 200, 45, "Atacar", () -> batalha.acaoAtacar());
        this.botaoHabilidade = new Botao(740, 410, 200, 45, "Habilidade",
                this::abrirSubmenuHabilidades);
        this.botaoVerStatus = new Botao(520, 465, 200, 45, "Ver status",
                this::abrirPainelStatus);
        this.botaoFugir = new Botao(740, 465, 200, 45, "Fugir",
                () -> batalha.acaoFugir());
        this.botaoFugir.setHabilitado(batalha.isFugaPermitida());
        this.botaoFecharStatus = new Botao(380, 290, 200, 45, "Fechar",
                this::fecharPainelStatus);
        this.botaoVoltarHabilidades = new Botao(740, 465, 200, 45, "Voltar",
                this::voltarAoMenuPrincipal);
        batalha.iniciar();
        sincronizarMensagens();
        mostrarProximaMensagem();
        atualizarHabilitacaoBotoes();
    }

    @Override
    public void atualizar() {
        sincronizarMensagens();
        if (mensagemAtual == null) {
            mostrarProximaMensagem();
        }
        if (mensagemAtual == null && filaMensagens.isEmpty()
                && batalha.getEstado() == EstadoBatalha.TURNO_DO_INIMIGO) {
            batalha.executarTurnoDoInimigo();
            sincronizarMensagens();
            mostrarProximaMensagem();
        }
        caixaDeTexto.atualizar();
        atualizarHabilitacaoBotoes();
        if (!notificacaoEncerramentoEnviada && batalha.estaEncerrada()
                && mensagemAtual == null && filaMensagens.isEmpty()) {
            notificacaoEncerramentoEnviada = true;
            aoEncerrar.accept(batalha.getResultado());
        }
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
        if (exibindoSubmenuHabilidades) {
            desenharSubmenuHabilidades(g);
        } else {
            botaoAtacar.desenhar(g);
            botaoHabilidade.desenhar(g);
            botaoVerStatus.desenhar(g);
            botaoFugir.desenhar(g);
        }
        if (exibindoPainelStatus) {
            desenharPainelStatus(g);
        }
    }

    private void abrirPainelStatus() {
        textoStatus = batalha.acaoVerStatus();
        exibindoPainelStatus = true;
    }

    private void fecharPainelStatus() {
        exibindoPainelStatus = false;
    }

    private void desenharPainelStatus(Graphics2D g) {
        int x = 180;
        int y = 170;
        int largura = 600;
        int altura = 190;
        g.setColor(new Color(20, 24, 30, 235));
        g.fillRect(x, y, largura, altura);
        g.setColor(Color.WHITE);
        g.drawRect(x, y, largura - 1, altura - 1);

        Font fonteAnterior = g.getFont();
        int tamanhoFonte = 18;
        Font fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte);
        FontMetrics metricas = g.getFontMetrics(fonte);
        while (metricas.stringWidth(textoStatus) > largura - 40 && tamanhoFonte > 10) {
            tamanhoFonte--;
            fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte);
            metricas = g.getFontMetrics(fonte);
        }
        g.setFont(fonte);
        int textoX = x + (largura - metricas.stringWidth(textoStatus)) / 2;
        g.drawString(textoStatus, textoX, y + 75);
        g.setFont(fonteAnterior);
        botaoFecharStatus.desenhar(g);
    }

    private void abrirSubmenuHabilidades() {
        exibindoSubmenuHabilidades = true;
        botoesHabilidade.clear();
        rotulosHabilidade.clear();

        List<Habilidade> habilidades = batalha.getJogador().getHabilidades();
        for (int i = 0; i < habilidades.size() && i < 3; i++) {
            Habilidade habilidade = habilidades.get(i);
            int x = i % 2 == 0 ? 520 : 740;
            int y = 410 + i / 2 * 55;
            rotulosHabilidade.add(habilidade.getNome() + " ("
                    + habilidade.getUsosRestantes() + "/" + habilidade.getUsosMaximos()
                    + " usos)");
            int indiceHabilidade = i;
            Botao botao = new Botao(x, y, 200, 45, "",
                    () -> usarHabilidade(indiceHabilidade));
            botao.setHabilitado(habilidade.temUsosRestantes());
            botoesHabilidade.add(botao);
        }
    }

    private void usarHabilidade(int indice) {
        batalha.acaoHabilidade(indice);
        exibindoSubmenuHabilidades = false;
    }

    private void voltarAoMenuPrincipal() {
        exibindoSubmenuHabilidades = false;
    }

    private void desenharSubmenuHabilidades(Graphics2D g) {
        for (int i = 0; i < botoesHabilidade.size(); i++) {
            int x = i % 2 == 0 ? 520 : 740;
            int y = 410 + i / 2 * 55;
            botoesHabilidade.get(i).desenhar(g);
            desenharRotuloHabilidade(g, rotulosHabilidade.get(i), x, y);
        }
        botaoVoltarHabilidades.desenhar(g);
    }

    private void desenharRotuloHabilidade(Graphics2D g, String rotulo, int x, int y) {
        Font fonteAnterior = g.getFont();
        Color corAnterior = g.getColor();
        int tamanhoFonte = 20;
        Font fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte);
        FontMetrics metricas = g.getFontMetrics(fonte);
        while (metricas.stringWidth(rotulo) > 184 && tamanhoFonte > 10) {
            tamanhoFonte--;
            fonte = new Font(Font.MONOSPACED, Font.BOLD, tamanhoFonte);
            metricas = g.getFontMetrics(fonte);
        }
        g.setFont(fonte);
        g.setColor(Color.WHITE);
        int textoX = x + (200 - metricas.stringWidth(rotulo)) / 2;
        int textoY = y + ((45 - metricas.getHeight()) / 2) + metricas.getAscent();
        g.drawString(rotulo, textoX, textoY);
        g.setFont(fonteAnterior);
        g.setColor(corAnterior);
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
        atualizarHabilitacaoBotoes();
        if (exibindoPainelStatus) {
            botaoFecharStatus.clicar(x, y);
            return;
        }
        if (exibindoSubmenuHabilidades) {
            for (Botao botao : botoesHabilidade) {
                botao.clicar(x, y);
            }
            botaoVoltarHabilidades.clicar(x, y);
        } else {
            botaoAtacar.clicar(x, y);
            botaoHabilidade.clicar(x, y);
            botaoVerStatus.clicar(x, y);
            botaoFugir.clicar(x, y);
        }
        if (x >= CAIXA_TEXTO_X && x < CAIXA_TEXTO_X + CAIXA_TEXTO_LARGURA
                && y >= CAIXA_TEXTO_Y && y < CAIXA_TEXTO_Y + CAIXA_TEXTO_ALTURA) {
            avancarMensagem();
        }
        atualizarHabilitacaoBotoes();
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoAtacar.atualizarHover(x, y);
        botaoHabilidade.atualizarHover(x, y);
        botaoVerStatus.atualizarHover(x, y);
        botaoFugir.atualizarHover(x, y);
        botaoFecharStatus.atualizarHover(x, y);
        for (Botao botao : botoesHabilidade) {
            botao.atualizarHover(x, y);
        }
        botaoVoltarHabilidades.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (exibindoPainelStatus) {
            if (codigo == KeyEvent.VK_ESCAPE) {
                fecharPainelStatus();
            }
        } else if (codigo == KeyEvent.VK_ESCAPE && exibindoSubmenuHabilidades) {
            voltarAoMenuPrincipal();
        } else if (codigo == KeyEvent.VK_ENTER) {
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
        atualizarHabilitacaoBotoes();
    }

    private void atualizarHabilitacaoBotoes() {
        boolean habilitado = batalha.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR
                && filaMensagens.isEmpty()
                && mensagemAtual == null;
        botaoAtacar.setHabilitado(habilitado);
        botaoHabilidade.setHabilitado(habilitado);
        botaoVerStatus.setHabilitado(habilitado);
        botaoFugir.setHabilitado(habilitado && batalha.isFugaPermitida());
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
