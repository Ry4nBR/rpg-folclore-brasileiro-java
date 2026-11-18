package br.folclore.ui;

import br.folclore.ui.telas.TelaInicial;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {

    public JanelaPrincipal() {
        super(ConfiguracoesUi.TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        GerenciadorDeTelas gerenciadorDeTelas = new GerenciadorDeTelas();
        gerenciadorDeTelas.trocarPara(new TelaInicial(gerenciadorDeTelas));
        add(new PainelDoJogo(gerenciadorDeTelas));
        pack();
        setLocationRelativeTo(null);
    }
}
