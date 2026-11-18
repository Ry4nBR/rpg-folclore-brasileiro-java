package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.habilidades.Habilidade;
import br.folclore.modelo.Personagem;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CaixaDeTexto;
import br.folclore.ui.componentes.CarregadorDeImagens;
import br.folclore.ui.componentes.NormalizadorDeNomes;
import br.folclore.ui.componentes.PosicoesDeBatalha;
import br.folclore.ui.componentes.RepositorioDeSprites;
import br.folclore.ui.efeitos.AnimacaoCura;
import br.folclore.ui.efeitos.AnimacaoDeslocamento;
import br.folclore.ui.efeitos.AnimacaoNumeroFlutuante;
import br.folclore.ui.efeitos.AnimacaoParticulas;
import br.folclore.ui.efeitos.AnimacaoTremor;
import br.folclore.ui.efeitos.CoresDeTipo;
import br.folclore.ui.efeitos.GerenciadorDeEfeitos;
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
    private final GerenciadorDeEfeitos gerenciadorDeEfeitos = new GerenciadorDeEfeitos();
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
    private int quadrosDeslocamentoJogador;
    private int quadrosDeslocamentoInimigo;
    private int quadrosTremorJogador;
    private int quadrosTremorInimigo;
    private int quadrosAteImpactoHabilidade;
    private int danoHabilidadePendente;
    private double pvExibidoJogador;
    private double pvExibidoInimigo;

    public TelaBatalha(GerenciadorDeTelas gerenciador, Batalha batalha,
                       Consumer<ResultadoBatalha> aoEncerrar) {
        this.gerenciador = gerenciador;
        this.batalha = batalha;
        this.aoEncerrar = aoEncerrar;
        this.fundo = CarregadorDeImagens.carregar("/fundos/batalha_generico.png");
        this.caixaDeTexto = new CaixaDeTexto(CAIXA_TEXTO_X, CAIXA_TEXTO_Y,
                CAIXA_TEXTO_LARGURA, CAIXA_TEXTO_ALTURA);
        this.botaoAtacar = new Botao(520, 410, 200, 45, "Atacar",
                this::atacarComJogador);
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
        pvExibidoJogador = batalha.getJogador().getPvAtual();
        pvExibidoInimigo = batalha.getInimigo().getPvAtual();
        sincronizarMensagens();
        mostrarProximaMensagem();
        atualizarHabilitacaoBotoes();
    }

    @Override
    public void atualizar() {
        gerenciadorDeEfeitos.atualizar();
        atualizarQuadrosDeAnimacao();
        atualizarImpactoHabilidadePendente();
        atualizarPvExibido();
        sincronizarMensagens();
        if (mensagemAtual == null) {
            mostrarProximaMensagem();
        }
        if (mensagemAtual == null && filaMensagens.isEmpty()
                && !gerenciadorDeEfeitos.temAtivas()
                && batalha.getEstado() == EstadoBatalha.TURNO_DO_INIMIGO) {
            atacarComInimigo();
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
        if (quadrosDeslocamentoJogador == 0 && quadrosTremorJogador == 0) {
            RepositorioDeSprites.desenhar(g, batalha.getJogador(),
                    PosicoesDeBatalha.JOGADOR_X, PosicoesDeBatalha.JOGADOR_Y, true);
        }
        if (quadrosDeslocamentoInimigo == 0 && quadrosTremorInimigo == 0) {
            RepositorioDeSprites.desenhar(g, batalha.getInimigo(),
                    PosicoesDeBatalha.INIMIGO_X, PosicoesDeBatalha.INIMIGO_Y, false);
        }
        desenharHudInimigo(g);
        desenharHudJogador(g);
        gerenciadorDeEfeitos.desenhar(g);
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

    private void atacarComJogador() {
        int pvAntes = batalha.getInimigo().getPvAtual();
        batalha.acaoAtacar();
        iniciarEfeitosDeAtaque(batalha.getJogador(), batalha.getInimigo(),
                pvAntes - batalha.getInimigo().getPvAtual());
    }

    private void atacarComInimigo() {
        int pvAntes = batalha.getJogador().getPvAtual();
        batalha.executarTurnoDoInimigo();
        iniciarEfeitosDeAtaque(batalha.getInimigo(), batalha.getJogador(),
                pvAntes - batalha.getJogador().getPvAtual());
    }

    private void iniciarEfeitosDeAtaque(Personagem atacante, Personagem alvo, int dano) {
        if (dano <= 0) {
            return;
        }

        boolean atacanteJogador = atacante == batalha.getJogador();
        int xAtacante = obterX(atacante);
        int yAtacante = obterY(atacante);
        int xAlvo = obterX(alvo);
        int yAlvo = obterY(alvo);
        int tamanho = PosicoesDeBatalha.TAMANHO_SPRITE;
        boolean atacanteEspelhado = atacanteJogador;
        BufferedImage spriteAtacante = RepositorioDeSprites.obter(
                NormalizadorDeNomes.paraId(atacante.getNome()));

        gerenciadorDeEfeitos.adicionar(new AnimacaoDeslocamento(spriteAtacante,
                xAtacante, yAtacante, xAlvo, yAlvo, tamanho, atacanteEspelhado));
        if (atacanteJogador) {
            quadrosDeslocamentoJogador = AnimacaoDeslocamento.DURACAO_QUADROS;
        } else {
            quadrosDeslocamentoInimigo = AnimacaoDeslocamento.DURACAO_QUADROS;
        }
        iniciarEfeitosDeDano(alvo, dano);
    }

    private void iniciarParticulasDeHabilidade(int dano) {
        int tamanho = PosicoesDeBatalha.TAMANHO_SPRITE;
        gerenciadorDeEfeitos.adicionar(new AnimacaoParticulas(
                CoresDeTipo.paraTipo(batalha.getJogador().getTipo()),
                PosicoesDeBatalha.JOGADOR_X + tamanho / 2,
                PosicoesDeBatalha.JOGADOR_Y + tamanho / 2,
                PosicoesDeBatalha.INIMIGO_X + tamanho / 2,
                PosicoesDeBatalha.INIMIGO_Y + tamanho / 2));
        quadrosAteImpactoHabilidade = AnimacaoParticulas.DURACAO_QUADROS;
        danoHabilidadePendente = dano;
    }

    private void atualizarImpactoHabilidadePendente() {
        if (quadrosAteImpactoHabilidade == 0) {
            return;
        }
        quadrosAteImpactoHabilidade--;
        if (quadrosAteImpactoHabilidade == 0) {
            int dano = danoHabilidadePendente;
            danoHabilidadePendente = 0;
            iniciarEfeitosDeDano(batalha.getInimigo(), dano);
        }
    }

    private void iniciarEfeitosDeDano(Personagem alvo, int dano) {
        int xAlvo = obterX(alvo);
        int yAlvo = obterY(alvo);
        int tamanho = PosicoesDeBatalha.TAMANHO_SPRITE;
        boolean alvoEspelhado = alvo == batalha.getJogador();
        BufferedImage spriteAlvo = RepositorioDeSprites.obter(
                NormalizadorDeNomes.paraId(alvo.getNome()));
        gerenciadorDeEfeitos.adicionar(new AnimacaoTremor(spriteAlvo,
                xAlvo, yAlvo, tamanho, alvoEspelhado));
        gerenciadorDeEfeitos.adicionar(new AnimacaoNumeroFlutuante(dano,
                xAlvo + tamanho / 2, yAlvo + tamanho / 2, false));
        if (alvo == batalha.getJogador()) {
            quadrosTremorJogador = AnimacaoTremor.DURACAO_QUADROS;
        } else {
            quadrosTremorInimigo = AnimacaoTremor.DURACAO_QUADROS;
        }
    }

    private int obterX(Personagem personagem) {
        return personagem == batalha.getJogador()
                ? PosicoesDeBatalha.JOGADOR_X : PosicoesDeBatalha.INIMIGO_X;
    }

    private int obterY(Personagem personagem) {
        return personagem == batalha.getJogador()
                ? PosicoesDeBatalha.JOGADOR_Y : PosicoesDeBatalha.INIMIGO_Y;
    }

    private void atualizarQuadrosDeAnimacao() {
        if (quadrosDeslocamentoJogador > 0) {
            quadrosDeslocamentoJogador--;
        }
        if (quadrosDeslocamentoInimigo > 0) {
            quadrosDeslocamentoInimigo--;
        }
        if (quadrosTremorJogador > 0) {
            quadrosTremorJogador--;
        }
        if (quadrosTremorInimigo > 0) {
            quadrosTremorInimigo--;
        }
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
        int pvInimigoAntes = batalha.getInimigo().getPvAtual();
        int pvJogadorAntes = batalha.getJogador().getPvAtual();
        batalha.acaoHabilidade(indice);
        int dano = pvInimigoAntes - batalha.getInimigo().getPvAtual();
        if (dano > 0) {
            iniciarParticulasDeHabilidade(dano);
        }
        int cura = batalha.getJogador().getPvAtual() - pvJogadorAntes;
        if (cura > 0) {
            iniciarEfeitosDeCura(cura);
        }
        exibindoSubmenuHabilidades = false;
    }

    private void iniciarEfeitosDeCura(int cura) {
        int xJogador = PosicoesDeBatalha.JOGADOR_X;
        int yJogador = PosicoesDeBatalha.JOGADOR_Y;
        int tamanho = PosicoesDeBatalha.TAMANHO_SPRITE;
        gerenciadorDeEfeitos.adicionar(new AnimacaoCura(xJogador, yJogador, tamanho));
        gerenciadorDeEfeitos.adicionar(new AnimacaoNumeroFlutuante(cura,
                xJogador + tamanho / 2, yJogador + tamanho / 2, true));
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
                (int) Math.round(pvExibidoInimigo), batalha.getInimigo().getPvMaximo());
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
                (int) Math.round(pvExibidoJogador), batalha.getJogador().getPvMaximo());
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        g.drawString("PV " + (int) Math.round(pvExibidoJogador)
                + "/" + batalha.getJogador().getPvMaximo(), x + 12, y + 68);
    }

    private void atualizarPvExibido() {
        if (gerenciadorDeEfeitos.temAtivas()) {
            return;
        }
        pvExibidoJogador = interpolarPv(pvExibidoJogador, batalha.getJogador().getPvAtual());
        pvExibidoInimigo = interpolarPv(pvExibidoInimigo, batalha.getInimigo().getPvAtual());
    }

    private double interpolarPv(double pvExibido, int pvReal) {
        double diferenca = pvReal - pvExibido;
        if (Math.abs(diferenca) < 0.1) {
            return pvReal;
        }
        return pvExibido + diferenca * 0.2;
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
        if (gerenciadorDeEfeitos.temAtivas()) {
            return;
        }
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
