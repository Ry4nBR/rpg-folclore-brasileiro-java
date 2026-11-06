package br.folclore.ui;

import br.folclore.ui.telas.Tela;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        this(null);
    }

    public JanelaPrincipal(Tela telaInicial) {
        super(ConfiguracoesUi.TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new PainelDoJogo(telaInicial));
        pack();
        setLocationRelativeTo(null);
    }
}
