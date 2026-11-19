package br.folclore.ui.telas;

import br.folclore.habilidades.Habilidade;
import br.folclore.modelo.Personagem;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.CarregadorDeImagens;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.List;

public class TelaPoderes implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;
    private int indicePoderSelecionado = -1;

    public TelaPoderes(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        fundo = CarregadorDeImagens.carregar("/fundos/spawn_medico.png");
    }

    @Override
    public void atualizar() {
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
            g.setColor(new Color(25, 25, 25, 210));
            g.fillRect(40, y, 420, 42);
            g.setColor(Color.WHITE);
            g.drawRect(40, y, 419, 41);
            g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 16));
            g.drawString(descricaoDoSlot(titular, indiceSlot), 52, y + 27);
        }

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 18));
        g.drawString("Poderes liberados", 490, 105);
        List<Habilidade> poderes = titular.getPoderesLiberados();
        for (int indice = 0; indice < poderes.size(); indice++) {
            int y = 125 + indice * 38;
            g.setColor(indice == indicePoderSelecionado
                    ? new Color(50, 90, 130, 220)
                    : new Color(25, 25, 25, 210));
            g.fillRect(480, y, 440, 34);
            g.setColor(Color.WHITE);
            g.drawRect(480, y, 439, 33);
            Habilidade habilidade = poderes.get(indice);
            g.drawString(habilidade.getNome() + " ("
                    + habilidade.getUsosMaximos() + " usos)", 490, y + 23);
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
        List<Habilidade> poderes = sessao.getEquipe().getTitular().getPoderesLiberados();
        int indice = (y - 125) / 38;
        if (x >= 480 && x < 920 && y >= 125
                && indice >= 0 && indice < poderes.size()
                && y < 125 + poderes.size() * 38) {
            indicePoderSelecionado = indice;
        }
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
