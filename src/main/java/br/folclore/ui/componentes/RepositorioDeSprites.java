package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;

public final class RepositorioDeSprites {

    private static final String DIRETORIO_SPRITES = "/sprites/";

    private RepositorioDeSprites() {
    }

    public static BufferedImage obter(String id) {
        return CarregadorDeImagens.carregar(DIRETORIO_SPRITES + id + ".png");
    }

    public static void precarregar(String id) {
        obter(id);
    }
}
