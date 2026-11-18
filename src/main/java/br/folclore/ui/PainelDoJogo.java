package br.folclore.ui;

import java.awt.Dimension;
import javax.swing.JPanel;

public class PainelDoJogo extends JPanel {

    public PainelDoJogo() {
        setPreferredSize(new Dimension(ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA));
        setFocusable(true);
        setDoubleBuffered(true);
    }
}
