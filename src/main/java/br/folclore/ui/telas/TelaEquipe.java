package br.folclore.ui.telas;

import br.folclore.modelo.Personagem;
import br.folclore.progressao.Equipe;
import br.folclore.progressao.SessaoDeJogo;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.Botao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;

public class TelaEquipe implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final SessaoDeJogo sessao;
    private final List<Botao> botoesTitular = new ArrayList<>();
    private final Botao botaoVoltar;

    public TelaEquipe(GerenciadorDeTelas gerenciador, SessaoDeJogo sessao) {
        this.gerenciador = gerenciador;
        this.sessao = sessao;
        botaoVoltar = new Botao(380, 470, 200, 50, "Voltar",
                () -> gerenciador.trocarPara(new TelaSpawnMedico(gerenciador, sessao)));
        Equipe equipe = sessao.getEquipe();
        for (int i = 0; i < equipe.getMembros().size(); i++) {
            final int indice = i;
            Botao botao = new Botao(700, 105 + i * 70, 220, 40,
                    "Tornar titular", () -> equipe.trocarTitular(indice));
            botoesTitular.add(botao);
        }
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
            Botao botao = botoesTitular.get(i);
            botao.setHabilitado(membro != equipe.getTitular() && membro.estaVivo());
            botao.desenhar(g);
        }

        for (int i = equipe.getMembros().size(); i < Equipe.MAX_VAGAS; i++) {
            int y = 130 + i * 70;
            if (i == Equipe.MAX_VAGAS - 1) {
                g.setColor(Color.GRAY);
                g.drawString("Vaga reservada", 40, y);
            } else {
                g.setColor(Color.WHITE);
                g.drawString("Vaga disponível", 40, y);
            }
        }
        botaoVoltar.desenhar(g);
    }

    @Override
    public void aoClicar(int x, int y) {
        for (Botao botao : botoesTitular) {
            botao.clicar(x, y);
        }
        botaoVoltar.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        for (Botao botao : botoesTitular) {
            botao.atualizarHover(x, y);
        }
        botaoVoltar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
