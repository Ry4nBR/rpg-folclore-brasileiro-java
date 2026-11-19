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
    private String mensagemNotificacao;
    private long mensagemExpiraEm;

    public TelaSpawnMedico(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
        botaoCurar = new Botao(380, 400, 200, 50, "Revigorar equipe", this::revigorarEquipe);
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

        g.setColor(new Color(91, 48, 30));
        g.fillRect(130, 130, 140, 220);
        g.setColor(new Color(52, 33, 25));
        g.drawRect(130, 130, 139, 219);
        g.drawRect(142, 142, 115, 195);
        g.drawLine(199, 143, 199, 337);
        g.setColor(new Color(218, 179, 107));
        g.fillOval(244, 238, 9, 9);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Fogo", 172, 380);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        g.drawString("Titular: " + sessao.getEquipe().getTitular().getNome(), 325, 300);
        g.drawString("PV: " + sessao.getEquipe().getTitular().getPvAtual()
                + "/" + sessao.getEquipe().getTitular().getPvMaximo(), 325, 330);
        botaoCurar.desenhar(g);
        if (mensagemNotificacao != null) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            FontMetrics metricasMensagem = g.getFontMetrics();
            g.setColor(Color.WHITE);
            g.drawString(mensagemNotificacao,
                    (960 - metricasMensagem.stringWidth(mensagemNotificacao)) / 2, 480);
        }
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

    private void revigorarEquipe() {
        sessao.getEquipe().revigorarTodos();
        mensagemNotificacao = "Sua equipe foi revigorada!";
        mensagemExpiraEm = System.currentTimeMillis() + 2000;
    }
}
