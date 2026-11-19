package br.folclore.ui.telas;

import br.folclore.modelo.Atributo;
import br.folclore.modelo.Personagem;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.Botao;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class TelaMelhoriaDeAtributos implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;
    private final Botao botaoPv;
    private final Botao botaoAtaque;
    private final Botao botaoDefesa;
    private final Botao botaoVelocidade;
    private String mensagemNotificacao;

    public TelaMelhoriaDeAtributos(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
        botaoPv = new Botao(600, 185, 180, 40, "+ PV", this::melhorarPv);
        botaoAtaque = new Botao(600, 235, 180, 40, "+ Ataque", this::melhorarAtaque);
        botaoDefesa = new Botao(600, 285, 180, 40, "+ Defesa", this::melhorarDefesa);
        botaoVelocidade = new Botao(600, 335, 180, 40, "+ Velocidade",
                this::melhorarVelocidade);
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 960, 540);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        g.setColor(Color.WHITE);
        String titulo = "Melhorar atributos";
        FontMetrics metricas = g.getFontMetrics();
        g.drawString(titulo, (960 - metricas.stringWidth(titulo)) / 2, 60);

        Personagem titular = sessao.getEquipe().getTitular();
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Titular: " + titular.getNome(), 80, 110);
        g.drawString("XP: " + titular.getXp() + " (custo "
                + Personagem.CUSTO_MELHORIA_XP + " por ponto)", 80, 145);
        g.drawString("PV máximo: " + titular.getPvMaximo(), 80, 215);
        g.drawString("Ataque: " + titular.getAtaque(), 80, 265);
        g.drawString("Defesa: " + titular.getDefesa(), 80, 315);
        g.drawString("Velocidade: " + titular.getVelocidade(), 80, 365);
        botaoPv.desenhar(g);
        botaoAtaque.desenhar(g);
        botaoDefesa.desenhar(g);
        botaoVelocidade.desenhar(g);
        if (mensagemNotificacao != null) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
            g.setColor(Color.WHITE);
            g.drawString(mensagemNotificacao, 80, 435);
        }
    }

    @Override
    public void aoClicar(int x, int y) {
        botaoPv.clicar(x, y);
        botaoAtaque.clicar(x, y);
        botaoDefesa.clicar(x, y);
        botaoVelocidade.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoPv.atualizarHover(x, y);
        botaoAtaque.atualizarHover(x, y);
        botaoDefesa.atualizarHover(x, y);
        botaoVelocidade.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }

    private void melhorarPv() {
        if (sessao.getEquipe().getTitular().melhorarAtributo(Atributo.PV)) {
            mensagemNotificacao = "PV aumentou!";
        } else {
            mensagemNotificacao = "XP insuficiente!";
        }
    }

    private void melhorarAtaque() {
        if (sessao.getEquipe().getTitular().melhorarAtributo(Atributo.ATAQUE)) {
            mensagemNotificacao = "Ataque aumentou!";
        } else {
            mensagemNotificacao = "XP insuficiente!";
        }
    }

    private void melhorarDefesa() {
        if (sessao.getEquipe().getTitular().melhorarAtributo(Atributo.DEFESA)) {
            mensagemNotificacao = "Defesa aumentou!";
        } else {
            mensagemNotificacao = "XP insuficiente!";
        }
    }

    private void melhorarVelocidade() {
        if (sessao.getEquipe().getTitular().melhorarAtributo(Atributo.VELOCIDADE)) {
            mensagemNotificacao = "Velocidade aumentou!";
        } else {
            mensagemNotificacao = "XP insuficiente!";
        }
    }
}
