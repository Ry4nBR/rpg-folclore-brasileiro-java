package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.habilidades.Habilidade;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Saci;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.BarraDeVida;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CaixaDeTexto;
import br.folclore.ui.componentes.CarregadorDeImagens;
import br.folclore.ui.componentes.NormalizadorDeNomes;
import br.folclore.ui.componentes.PosicoesDeBatalha;
import br.folclore.ui.componentes.RepositorioDeSprites;
import br.folclore.ui.componentes.SpriteAnimado;
import br.folclore.ui.efeitos.AnimacaoCura;
import br.folclore.ui.efeitos.AnimacaoDeslocamento;
import br.folclore.ui.efeitos.AnimacaoGif;
import br.folclore.ui.efeitos.AnimacaoNumeroFlutuante;
import br.folclore.ui.efeitos.AnimacaoParticulas;
import br.folclore.ui.efeitos.AnimacaoTremor;
import br.folclore.ui.efeitos.CoresDeTipo;
import br.folclore.ui.efeitos.GerenciadorDeEfeitos;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
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
    private final Botao botaoMochila;
    private final Botao botaoVerStatus;
    private final Botao botaoFugir;
    private final Botao botaoFecharStatus;
    private final Botao botaoVoltarHabilidades;
    private final List<Botao> botoesHabilidade = new ArrayList<>();
    private final Queue<String> filaMensagens = new ArrayDeque<>();
    private int mensagensLidasDoRegistro;
    private String mensagemAtual;
    private boolean exibindoSubmenuHabilidades;
    private boolean exibindoPainelStatus;
    private boolean notificacaoEncerramentoEnviada;
    private boolean exibindoMochila;
    private int quadrosDeslocamentoJogador;
    private int quadrosDeslocamentoInimigo;
    private int quadrosTremorJogador;
    private int quadrosTremorInimigo;
    private int quadrosAteImpactoHabilidade;
    private int danoHabilidadePendente;
    private int curaHabilidadePendente;
    private final Map<String, SpriteAnimado> animacoesSaci = new HashMap<>();
    private AnimacaoGif animacaoHabilidadeSaci;
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
        this.botaoAtacar = new Botao(520, 410, 200, 45, "Ataque",
                this::abrirSubmenuHabilidades);
        this.botaoMochila = new Botao(740, 410, 200, 45, "Mochila",
                this::abrirMochila);
        this.botaoVerStatus = new Botao(520, 465, 200, 45, "Status",
                this::abrirPainelStatus);
        this.botaoFugir = new Botao(740, 465, 200, 45, "Fugir",
                this::fugir);
        this.botaoFugir.setHabilitado(batalha.isFugaPermitida());
        this.botaoFecharStatus = new Botao(380, 345, 200, 35, "Voltar",
                this::fecharPainelStatus);
        this.botaoVoltarHabilidades = new Botao(520, 515, 420, 22, "Voltar (Esc)",
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
                && mensagemAtual == null && filaMensagens.isEmpty()
                && !gerenciadorDeEfeitos.temAtivas()) {
            notificacaoEncerramentoEnviada = true;
            aoEncerrar.accept(batalha.getResultado());
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        desenharPlataformas(g);
        if (quadrosDeslocamentoJogador == 0 && quadrosTremorJogador == 0
                && animacaoHabilidadeSaci == null) {
            RepositorioDeSprites.desenhar(g, batalha.getJogador(),
                    PosicoesDeBatalha.JOGADOR_X, PosicoesDeBatalha.JOGADOR_Y, false);
        }
        if (quadrosDeslocamentoInimigo == 0 && quadrosTremorInimigo == 0) {
            RepositorioDeSprites.desenhar(g, batalha.getInimigo(),
                    PosicoesDeBatalha.INIMIGO_X, PosicoesDeBatalha.INIMIGO_Y, true);
        }
        desenharHudInimigo(g);
        desenharHudJogador(g);
        gerenciadorDeEfeitos.desenhar(g);
        caixaDeTexto.desenhar(g);
        if (exibindoSubmenuHabilidades) {
            desenharSubmenuHabilidades(g);
        } else {
            botaoAtacar.desenhar(g);
            botaoMochila.desenhar(g);
            botaoVerStatus.desenhar(g);
            botaoFugir.desenhar(g);
        }
        if (exibindoPainelStatus || exibindoMochila) {
            desenharPainelStatus(g);
        }
    }

    private void abrirPainelStatus() {
        if (podeAgir()) {
            exibindoPainelStatus = true;
        }
    }

    private void abrirMochila() {
        if (podeAgir()) {
            exibindoMochila = true;
        }
    }

    private void fugir() {
        if (podeAgir() && batalha.isFugaPermitida()) {
            batalha.acaoFugir();
        }
    }

    private void fecharPainelStatus() {
        exibindoPainelStatus = false;
        exibindoMochila = false;
    }

    private void atacarComJogador() {
        if (!podeAgir()) {
            return;
        }
        exibindoSubmenuHabilidades = false;
        int pvAntes = batalha.getInimigo().getPvAtual();
        batalha.acaoAtacar();
        int dano = pvAntes - batalha.getInimigo().getPvAtual();
        if (batalha.getJogador() instanceof Saci
                && iniciarAnimacaoSaci("/efeitos/saci_ataque.gif", dano, 0)) {
            return;
        }
        iniciarEfeitosDeAtaque(batalha.getJogador(), batalha.getInimigo(),
                dano);
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
        boolean atacanteEspelhado = !atacanteJogador;
        String idAtacante = NormalizadorDeNomes.paraId(atacante.getNome());

        gerenciadorDeEfeitos.adicionar(new AnimacaoDeslocamento(() -> RepositorioDeSprites.obter(idAtacante),
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
        if (animacaoHabilidadeSaci != null) {
            if (animacaoHabilidadeSaci.terminou()) {
                animacaoHabilidadeSaci = null;
                if (danoHabilidadePendente > 0) {
                    iniciarEfeitosDeDano(batalha.getInimigo(), danoHabilidadePendente);
                }
                if (curaHabilidadePendente > 0) {
                    iniciarEfeitosDeCura(curaHabilidadePendente);
                }
                danoHabilidadePendente = 0;
                curaHabilidadePendente = 0;
            }
            return;
        }
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
        boolean alvoEspelhado = alvo != batalha.getJogador();
        String idAlvo = NormalizadorDeNomes.paraId(alvo.getNome());
        gerenciadorDeEfeitos.adicionar(new AnimacaoTremor(() -> RepositorioDeSprites.obter(idAlvo),
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
        g.setColor(new Color(20, 24, 30, 245));
        g.fillRect(90, 90, 780, 300);
        g.setColor(Color.WHITE);
        g.drawRect(90, 90, 779, 299);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 24));
        g.drawString(exibindoMochila ? "Mochila" : "Status dos combatentes", 120, 125);
        if (exibindoMochila) {
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 20));
            g.drawString("Sua mochila está vazia.", 120, 205);
            g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
            g.drawString("Abrir a mochila não consome seu turno.", 120, 240);
        } else {
            desenharStatusCombatente(g, batalha.getJogador(), "Seu personagem", 120);
            desenharStatusCombatente(g, batalha.getInimigo(), "Inimigo", 510);
        }
        botaoFecharStatus.desenhar(g);
    }

    private void desenharStatusCombatente(Graphics2D g, Personagem personagem, String titulo, int x) {
        g.setColor(new Color(150, 210, 255));
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.drawString(titulo, x, 160);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 17));
        g.drawString(personagem.getNome(), x, 185);
        g.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 16));
        String[] linhas = {
            "Tipo: " + personagem.getTipo().getRotulo(),
            "PV: " + personagem.getPvAtual() + "/" + personagem.getPvMaximo(),
            "Ataque: " + personagem.getAtaque(),
            "Defesa: " + personagem.getDefesa(),
            "Velocidade: " + personagem.getVelocidade()
        };
        for (int i = 0; i < linhas.length; i++) {
            g.drawString(linhas[i], x, 210 + i * 25);
        }
    }

    private void abrirSubmenuHabilidades() {
        if (!podeAgir()) {
            return;
        }
        exibindoSubmenuHabilidades = true;
        botoesHabilidade.clear();
        Personagem jogador = batalha.getJogador();
        Personagem inimigo = batalha.getInimigo();
        botoesHabilidade.add(new Botao(520, 410, 200, 45, jogador.getNomeAtaqueBasico(),
                "Dano: " + jogador.preverDanoAtaqueBasico(inimigo) + " | Sem limite", this::atacarComJogador));
        List<Habilidade> habilidades = jogador.getHabilidades();
        for (int i = 0; i < habilidades.size() && i < 3; i++) {
            Habilidade habilidade = habilidades.get(i);
            int slot = i + 1;
            int x = slot % 2 == 0 ? 520 : 740;
            int y = 410 + slot / 2 * 55;
            String detalhe = habilidade.descreverEfeito(jogador, inimigo) + " | "
                    + habilidade.getUsosRestantes() + "/" + habilidade.getUsosMaximos();
            int indiceHabilidade = i;
            Botao botao = new Botao(x, y, 200, 45, habilidade.getNome(), detalhe,
                    () -> usarHabilidade(indiceHabilidade));
            botao.setHabilitado(habilidade.temUsosRestantes());
            botoesHabilidade.add(botao);
        }
    }

    private void usarHabilidade(int indice) {
        if (!podeAgir()) {
            return;
        }
        int pvInimigoAntes = batalha.getInimigo().getPvAtual();
        int pvJogadorAntes = batalha.getJogador().getPvAtual();
        int usosAntes = batalha.getJogador().getHabilidades().get(indice).getUsosRestantes();
        String nomeHabilidade = batalha.getJogador().getHabilidades().get(indice).getNome();
        batalha.acaoHabilidade(indice);
        int dano = pvInimigoAntes - batalha.getInimigo().getPvAtual();
        int cura = batalha.getJogador().getPvAtual() - pvJogadorAntes;
        boolean habilidadeExecutada = batalha.getJogador().getHabilidades().get(indice)
                .getUsosRestantes() < usosAntes;
        if (batalha.getJogador() instanceof Saci && habilidadeExecutada
                && iniciarAnimacaoSaci(caminhoAnimacaoSaci(nomeHabilidade), dano, cura)) {
            exibindoSubmenuHabilidades = false;
            return;
        }
        if (dano > 0) {
            iniciarParticulasDeHabilidade(dano);
        }
        if (cura > 0) {
            iniciarEfeitosDeCura(cura);
        }
        exibindoSubmenuHabilidades = false;
    }

    private String caminhoAnimacaoSaci(String nomeHabilidade) {
        return switch (nomeHabilidade) {
            case "Chute Furacão" -> "/efeitos/saci_habilidade.gif";
            case "Vento Revigorante" -> "/efeitos/saci_vento_revigorante.gif";
            case "Assobio" -> "/efeitos/saci_assobio.gif";
            default -> null;
        };
    }

    private boolean iniciarAnimacaoSaci(String caminho, int dano, int cura) {
        if (caminho == null) {
            return false;
        }
        SpriteAnimado sprite = animacoesSaci.get(caminho);
        if (sprite == null) {
            try (InputStream entrada = TelaBatalha.class.getResourceAsStream(caminho)) {
                if (entrada == null) {
                    return false;
                }
                sprite = SpriteAnimado.carregar(entrada);
                animacoesSaci.put(caminho, sprite);
            } catch (IOException e) {
                System.err.println("Falha ao carregar habilidade do Saci: " + e.getMessage());
                return false;
            }
        }
        animacaoHabilidadeSaci = new AnimacaoGif(sprite,
                PosicoesDeBatalha.JOGADOR_X, PosicoesDeBatalha.JOGADOR_Y,
                PosicoesDeBatalha.TAMANHO_SPRITE, false);
        danoHabilidadePendente = dano;
        curaHabilidadePendente = cura;
        gerenciadorDeEfeitos.adicionar(animacaoHabilidadeSaci);
        return true;
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
        for (Botao botao : botoesHabilidade) {
            botao.desenhar(g);
        }
        botaoVoltarHabilidades.desenhar(g);
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
        if (exibindoPainelStatus || exibindoMochila) {
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
            botaoMochila.clicar(x, y);
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
        botaoMochila.atualizarHover(x, y);
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
        if (exibindoPainelStatus || exibindoMochila) {
            if (codigo == KeyEvent.VK_ESCAPE || codigo == KeyEvent.VK_ENTER) {
                fecharPainelStatus();
            }
            return;
        }
        if (codigo == KeyEvent.VK_ESCAPE && exibindoSubmenuHabilidades) {
            voltarAoMenuPrincipal();
        } else if (codigo == KeyEvent.VK_ENTER) {
            avancarMensagem();
        } else if (podeAgir()) {
            if (exibindoSubmenuHabilidades && codigo >= KeyEvent.VK_1 && codigo <= KeyEvent.VK_4) {
                int slot = codigo - KeyEvent.VK_1;
                if (slot == 0) {
                    atacarComJogador();
                } else if (slot - 1 < batalha.getJogador().getHabilidades().size()
                        && batalha.getJogador().getHabilidades().get(slot - 1).temUsosRestantes()) {
                    usarHabilidade(slot - 1);
                }
            } else if (!exibindoSubmenuHabilidades) {
                switch (codigo) {
                    case KeyEvent.VK_A -> abrirSubmenuHabilidades();
                    case KeyEvent.VK_M -> abrirMochila();
                    case KeyEvent.VK_S -> abrirPainelStatus();
                    case KeyEvent.VK_F -> fugir();
                    default -> { }
                }
            }
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
        boolean habilitado = podeAgir();
        botaoAtacar.setHabilitado(habilitado);
        botaoMochila.setHabilitado(habilitado);
        botaoVerStatus.setHabilitado(habilitado);
        botaoFugir.setHabilitado(habilitado && batalha.isFugaPermitida());
        for (int slot = 0; slot < botoesHabilidade.size(); slot++) {
            boolean disponivel = slot == 0 || batalha.getJogador().getHabilidades().get(slot - 1).temUsosRestantes();
            botoesHabilidade.get(slot).setHabilitado(habilitado && disponivel);
        }
    }

    private boolean podeAgir() {
        return batalha.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR
                && filaMensagens.isEmpty() && mensagemAtual == null
                && !gerenciadorDeEfeitos.temAtivas();
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
