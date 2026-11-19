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

public class TelaPoderes implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final BufferedImage fundo;

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
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
