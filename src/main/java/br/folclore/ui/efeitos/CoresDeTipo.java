package br.folclore.ui.efeitos;

import br.folclore.modelo.Tipo;
import java.awt.Color;

public final class CoresDeTipo {
    private CoresDeTipo() {
    }

    public static Color paraTipo(Tipo tipo) {
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo.");
        }

        return switch (tipo) {
            case VENTO -> Color.CYAN;
            case FISICO -> Color.ORANGE;
            case PSIQUICO -> new Color(128, 0, 128);
            case FOGO -> Color.RED;
            case PLANTA -> Color.GREEN;
            case AGUA -> Color.BLUE;
        };
    }
}
