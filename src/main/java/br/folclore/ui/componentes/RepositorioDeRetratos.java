package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;

public final class RepositorioDeRetratos {
    private static final String DIRETORIO_RETRATOS = "/retratos/";

    static {
        obter("saci");
    }

    private RepositorioDeRetratos() {
    }

    public static BufferedImage obter(String id) {
        return CarregadorDeImagens.carregar(DIRETORIO_RETRATOS + id + ".png");
    }
}
