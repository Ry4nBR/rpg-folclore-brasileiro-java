package br.folclore.ui;

import br.folclore.ui.telas.Tela;
import br.folclore.ui.telas.TelaInicial;
import javax.swing.JFrame;

public class JanelaPrincipal extends JFrame {
    public JanelaPrincipal() {
        super(ConfiguracoesUi.TITULO);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        Tela telaInicial = new TelaInicial(() -> System.out.println("Jogar"));
        add(new PainelDoJogo(telaInicial));
        pack();
        setLocationRelativeTo(null);
    }
}
