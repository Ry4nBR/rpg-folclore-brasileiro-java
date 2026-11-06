package br.folclore;

import br.folclore.ui.JanelaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true));
    }
}
