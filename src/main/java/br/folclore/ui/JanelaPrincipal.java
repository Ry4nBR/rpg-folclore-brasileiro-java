package br.folclore.ui;

import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        super(ConfiguracoesUi.TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        add(new PainelDoJogo());
        pack();
        setLocationRelativeTo(null);
    }
}
