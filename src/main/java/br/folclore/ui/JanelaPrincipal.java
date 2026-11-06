package br.folclore.ui;

import br.folclore.ui.telas.TelaInicial;
import br.folclore.ui.telas.TelaSelecaoPersonagem;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    private final GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas();

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
        gerenciadorDeTelas.trocarPara(new TelaInicial(this::abrirSelecao));
    }

    private void abrirSelecao() {
        gerenciadorDeTelas.trocarPara(new TelaSelecaoPersonagem(
                personagem -> System.out.println(personagem.getNome()),
                this::abrirMenu));
    }
}
