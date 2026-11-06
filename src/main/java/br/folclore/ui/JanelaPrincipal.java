package br.folclore.ui;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.modelo.Personagem;
import br.folclore.progressao.SessaoMvp;
import br.folclore.ui.telas.TelaBatalha;
import br.folclore.ui.telas.TelaInicial;
import br.folclore.ui.telas.TelaResultado;
import br.folclore.ui.telas.TelaSelecaoPersonagem;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    private final GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas();
    private final SessaoMvp sessao = new SessaoMvp();

    public JanelaPrincipal() {
        super(ConfiguracoesUi.TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new PainelDoJogo(gerenciadorDeTelas, null));
        abrirMenu();
        pack();
        setLocationRelativeTo(null);
    }

    private void abrirMenu() {
        sessao.reiniciar();
        gerenciadorDeTelas.trocarPara(new TelaInicial(this::abrirSelecao));
    }

    private void abrirSelecao() {
        gerenciadorDeTelas.trocarPara(new TelaSelecaoPersonagem(
                this::abrirBatalha,
                this::abrirMenu));
    }

    private void abrirBatalha(Personagem escolhido) {
        Batalha batalha = sessao.criarBatalha(escolhido);
        gerenciadorDeTelas.trocarPara(new TelaBatalha(batalha, this::aoFimDaBatalha));
    }

    private void aoFimDaBatalha(ResultadoBatalha resultado) {
        sessao.registrarResultado(resultado);
        gerenciadorDeTelas.trocarPara(new TelaResultado(
                resultado == ResultadoBatalha.VITORIA,
                sessao.isGinasioConcluido(),
                this::abrirMenu,
                this::abrirSelecao));
    }

}
