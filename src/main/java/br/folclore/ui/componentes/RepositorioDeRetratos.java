package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;

public final class RepositorioDeRetratos {
    private static final String DIRETORIO_RETRATOS = "/retratos/";

    static {
        obter("saci");
        obter("lobisomem");
        obter("negrinho_do_pastoreio");
    }

    private RepositorioDeRetratos() {
    }

    public static BufferedImage obter(String id) {
        return CarregadorDeImagens.carregar(DIRETORIO_RETRATOS + id + ".png");
    }
}
