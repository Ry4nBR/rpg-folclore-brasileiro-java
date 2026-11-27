package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;

public final class RepositorioDeRetratos {
    private static final String DIRETORIO_RETRATOS = "/retratos/";

    static {
        obter("batatao");
        obter("boto_cor_de_rosa");
        obter("caipora");
        obter("capelobo");
        obter("curupira");
        obter("honorato");
        obter("iara");
        obter("saci");
        obter("lobisomem");
        obter("mae_de_ouro");
        obter("mula_sem_cabeca");
        obter("negrinho_do_pastoreio");
    }

    private RepositorioDeRetratos() {
    }

    public static BufferedImage obter(String id) {
        return CarregadorDeImagens.carregar(DIRETORIO_RETRATOS + id + ".png");
    }
}
