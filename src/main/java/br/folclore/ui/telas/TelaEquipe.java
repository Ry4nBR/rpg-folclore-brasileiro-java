package br.folclore.ui.telas;

import br.folclore.modelo.Personagem;
import br.folclore.progressao.Equipe;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TelaEquipe implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;

    public TelaEquipe(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
        g.setColor(new Color(35, 35, 35));
        g.fillRect(0, 0, 960, 540);

        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 36));
        g.setColor(Color.WHITE);
        g.drawString("Equipe", 380, 70);

        Equipe equipe = sessao.getEquipe();
        g.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        for (int i = 0; i < equipe.getMembros().size(); i++) {
            Personagem membro = equipe.getMembros().get(i);
            int y = 130 + i * 70;
            g.setColor(Color.WHITE);
            g.drawString(membro.getNome() + " - " + membro.getTipo().getRotulo(),
                    40, y);
            g.drawString("PV: " + membro.getPvAtual() + "/" + membro.getPvMaximo(),
                    380, y);
            if (membro == equipe.getTitular()) {
                g.drawString("Titular", 600, y);
            }
        }
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
