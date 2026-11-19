package br.folclore.ui.telas;

import br.folclore.habilidades.Habilidade;
import br.folclore.modelo.Personagem;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.List;

public class TelaPoderes implements Tela {
    private static final int LISTA_PODERES_X = 480;
    private static final int LISTA_PODERES_Y = 118;
    private static final int ALTURA_LINHA_PODER = 30;

    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;
    private final Botao botaoEquipar;
    private final Botao botaoVoltar;
    private int indicePoderSelecionado = -1;
    private int indiceEquipadoSelecionado = -1;
    private String mensagemNotificacao;

    public TelaPoderes(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
        botaoEquipar = new Botao(700, 440, 180, 40, "Equipar", this::equiparPoder);
        botaoVoltar = new Botao(40, 465, 180, 40, "Voltar", this::voltar);
        atualizar();
    }

    @Override
    public void atualizar() {
        boolean podeTrocar = sessao.getEquipe().getTitular().podeTrocarPoderes();
        botaoEquipar.setHabilitado(podeTrocar
                && indiceEquipadoSelecionado >= 0
                && indicePoderSelecionado >= 0);
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.drawImage(fundo, 0, 0, null);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(0, 0, 960, 540);
        g.setColor(Color.WHITE);
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 32));
        g.drawString("Poderes", 390, 55);

        Personagem titular = sessao.getEquipe().getTitular();
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.drawString(titular.getNome(), 50, 95);
        for (int indiceSlot = 0; indiceSlot < Personagem.MAX_SLOTS; indiceSlot++) {
            int y = 125 + indiceSlot * 55;
            g.setColor(indiceSlot > 0 && indiceSlot - 1 == indiceEquipadoSelecionado
                    ? new Color(50, 90, 130, 220)
                    : new Color(25, 25, 25, 210));
            g.fillRect(40, y, 420, 42);
            g.setColor(Color.WHITE);
            g.drawRect(40, y, 419, 41);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            g.drawString(descricaoDoSlot(titular, indiceSlot), 52, y + 27);
        }

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.drawString("Poderes liberados", LISTA_PODERES_X + 10, 105);
        List<Habilidade> poderes = titular.getPoderesLiberados();
        for (int indice = 0; indice < poderes.size(); indice++) {
            int y = LISTA_PODERES_Y + indice * ALTURA_LINHA_PODER;
            g.setColor(indice == indicePoderSelecionado
                    ? new Color(50, 90, 130, 220)
                    : new Color(25, 25, 25, 210));
            g.fillRect(LISTA_PODERES_X, y, 440, ALTURA_LINHA_PODER - 2);
            g.setColor(Color.WHITE);
            g.drawRect(LISTA_PODERES_X, y, 439, ALTURA_LINHA_PODER - 3);
            Habilidade habilidade = poderes.get(indice);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            g.drawString(habilidade.getNome() + " ("
                    + habilidade.getUsosMaximos() + " usos)", LISTA_PODERES_X + 10, y + 20);
        }
        botaoEquipar.desenhar(g);
        botaoVoltar.desenhar(g);
        if (!titular.podeTrocarPoderes()) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            g.setColor(Color.WHITE);
            g.drawString("Libere 4 poderes para trocar", 480, 425);
        } else if (mensagemNotificacao != null) {
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            g.drawString(mensagemNotificacao, 480, 425);
        }
    }

    private String descricaoDoSlot(Personagem titular, int indiceSlot) {
        if (indiceSlot == 0) {
            return "1. Ataque básico: " + titular.getNomeAtaqueBasico();
        }
        if (indiceSlot >= titular.getSlotsDeHabilidade()) {
            return (indiceSlot + 1) + ". Bloqueado (venha um ginásio)";
        }

        int indiceHabilidade = indiceSlot - 1;
        if (indiceHabilidade < titular.getHabilidades().size()) {
            Habilidade habilidade = titular.getHabilidades().get(indiceHabilidade);
            return (indiceSlot + 1) + ". " + habilidade.getNome();
        }
        return (indiceSlot + 1) + ". Vazio";
    }

    @Override
    public void aoClicar(int x, int y) {
        Personagem titular = sessao.getEquipe().getTitular();
        List<Habilidade> poderes = titular.getPoderesLiberados();
        if (x >= 40 && x < 460 && y >= 125 && y < 125 + Personagem.MAX_SLOTS * 55) {
            int indiceSlot = (y - 125) / 55;
            if (indiceSlot > 0
                    && indiceSlot < titular.getSlotsDeHabilidade()
                    && indiceSlot - 1 < titular.getHabilidades().size()) {
                indiceEquipadoSelecionado = indiceSlot - 1;
            } else {
                indiceEquipadoSelecionado = -1;
            }
        } else if (x >= LISTA_PODERES_X && x < 920 && y >= LISTA_PODERES_Y) {
            int indice = (y - LISTA_PODERES_Y) / ALTURA_LINHA_PODER;
            if (indice >= 0 && indice < poderes.size()) {
                indicePoderSelecionado = indice;
            }
        }
        atualizar();
        botaoEquipar.clicar(x, y);
        botaoVoltar.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoEquipar.atualizarHover(x, y);
        botaoVoltar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_ESCAPE) {
            voltar();
        }
    }

    private void voltar() {
        gerenciador.trocarPara(new TelaSpawnMedico(gerenciador, sessao));
    }

    private void equiparPoder() {
        Personagem titular = sessao.getEquipe().getTitular();
        Habilidade poder = titular.getPoderesLiberados().get(indicePoderSelecionado);
        if (titular.trocarPoderEquipado(indiceEquipadoSelecionado, poder)) {
            mensagemNotificacao = "Poder equipado!";
        } else {
            mensagemNotificacao = "Não foi possível equipar esse poder.";
        }
    }
}
