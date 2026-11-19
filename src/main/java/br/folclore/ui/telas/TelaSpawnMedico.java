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
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;
    private final Botao botaoCurar;
    private final Botao botaoEquipe;
    private final Botao botaoMelhorias;
    private final Botao botaoPoderes;
    private final Botao botaoSalvar;
    private String mensagemNotificacao;
    private long mensagemExpiraEm;

    public TelaSpawnMedico(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
        botaoCurar = new Botao(380, 400, 200, 50, "Revigorar equipe", this::revigorarEquipe);
        botaoEquipe = new Botao(40, 400, 200, 50, "Equipe",
                () -> gerenciador.trocarPara(new TelaEquipe(gerenciador, sessao)));
        botaoMelhorias = new Botao(600, 400, 200, 50, "Melhorar atributos",
                () -> gerenciador.trocarPara(new TelaMelhoriaDeAtributos(gerenciador, sessao)));
        botaoPoderes = new Botao(820, 400, 100, 50, "Poderes",
                () -> gerenciador.trocarPara(new TelaPoderes(gerenciador, sessao)));
        botaoSalvar = new Botao(40, 470, 200, 45, "Salvar jogo", null);
    }

    @Override
    public void atualizar() {
        if (mensagemNotificacao != null && System.currentTimeMillis() >= mensagemExpiraEm) {
            mensagemNotificacao = null;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
        String titulo = "Spawn Médico";
        FontMetrics metricas = g.getFontMetrics();
        g.setColor(Color.WHITE);
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 70);

        desenharPorta(g, 130, "Fogo", true);
        desenharPorta(g, 410, "Planta", sessao.portaLiberada(1));
        desenharPorta(g, 690, "Água", sessao.portaLiberada(2));

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.drawString("Titular: " + sessao.getEquipe().getTitular().getNome(), 325, 100);
        g.drawString("PV: " + sessao.getEquipe().getTitular().getPvAtual()
                + "/" + sessao.getEquipe().getTitular().getPvMaximo(), 325, 123);
        botaoCurar.desenhar(g);
        botaoEquipe.desenhar(g);
        botaoMelhorias.desenhar(g);
        botaoPoderes.desenhar(g);
        botaoSalvar.desenhar(g);
        if (mensagemNotificacao != null) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            FontMetrics metricasMensagem = g.getFontMetrics();
            g.setColor(Color.WHITE);
            g.drawString(mensagemNotificacao,
                    (960 - metricasMensagem.stringWidth(mensagemNotificacao)) / 2, 480);
        }
    }

    private void desenharPorta(Graphics2D g, int x, String rotulo, boolean liberada) {
        Color madeira = liberada ? new Color(91, 48, 30) : new Color(105, 105, 105);
        Color detalhe = liberada ? new Color(52, 33, 25) : new Color(65, 65, 65);
        g.setColor(madeira);
        g.fillRect(x, 130, 140, 220);
        g.setColor(detalhe);
        g.drawRect(x, 130, 139, 219);
        g.drawRect(x + 12, 142, 115, 195);
        g.drawLine(x + 69, 143, x + 69, 337);
        if (liberada) {
            g.setColor(new Color(218, 179, 107));
            g.fillOval(x + 114, 238, 9, 9);
        } else {
            g.setColor(new Color(230, 210, 150));
            g.fillRoundRect(x + 54, 236, 34, 30, 5, 5);
            g.setStroke(new java.awt.BasicStroke(5));
            g.drawArc(x + 59, 219, 24, 27, 0, 180);
        }
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.setColor(Color.WHITE);
        g.drawString(rotulo, x + (140 - g.getFontMetrics().stringWidth(rotulo)) / 2, 380);
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoCurar.clicar(x, y);
        botaoEquipe.clicar(x, y);
        botaoMelhorias.clicar(x, y);
        botaoPoderes.clicar(x, y);
        botaoSalvar.clicar(x, y);
        if (x >= 130 && x < 270 && y >= 130 && y < 350) {
            sessao.entrarNoGinasio(0);
            gerenciador.trocarPara(new TelaGinasio(gerenciador, sessao, 0));
        } else if (x >= 410 && x < 550 && y >= 130 && y < 350) {
            if (!sessao.portaLiberada(1)) {
                mensagemNotificacao = "Vença o Ginásio do Fogo para abrir esta porta.";
                mensagemExpiraEm = System.currentTimeMillis() + 2000;
            } else {
                sessao.entrarNoGinasio(1);
                gerenciador.trocarPara(new TelaGinasio(gerenciador, sessao, 1));
            }
        } else if (x >= 690 && x < 830 && y >= 130 && y < 350) {
            if (!sessao.portaLiberada(2)) {
                mensagemNotificacao = "Vença o Ginásio da Planta para abrir esta porta.";
                mensagemExpiraEm = System.currentTimeMillis() + 2000;
            } else {
                sessao.entrarNoGinasio(2);
                gerenciador.trocarPara(new TelaGinasio(gerenciador, sessao, 2));
            }
        }
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoCurar.atualizarHover(x, y);
        botaoEquipe.atualizarHover(x, y);
        botaoMelhorias.atualizarHover(x, y);
        botaoPoderes.atualizarHover(x, y);
        botaoSalvar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }

    private void revigorarEquipe() {
        sessao.getEquipe().revigorarTodos();
        mensagemNotificacao = "Sua equipe foi revigorada!";
        mensagemExpiraEm = System.currentTimeMillis() + 2000;
    }
}
