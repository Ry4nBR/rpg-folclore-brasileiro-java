package br.folclore.ui.componentes;

import br.folclore.modelo.Personagem;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class RepositorioDeSprites {

    private static final String DIRETORIO_SPRITES = "/sprites/";

    static {
        precarregar("saci");
        precarregar("lobisomem");
        precarregar("negrinho_do_pastoreio");
        precarregar("mae_de_ouro");
        precarregar("batatao");
        precarregar("mula_sem_cabeca");
        precarregar("curupira");
        precarregar("caipora");
        precarregar("capelobo");
        precarregar("iara");
        precarregar("boto_cor_de_rosa");
    }

    private RepositorioDeSprites() {
    }

    public static BufferedImage obter(String id) {
        return CarregadorDeImagens.carregar(DIRETORIO_SPRITES + id + ".png");
    }

    public static void precarregar(String id) {
        obter(id);
    }

    public static void desenhar(Graphics2D g, Personagem personagem, int x, int y,
                                boolean espelhado) {
        BufferedImage sprite = obter(NormalizadorDeNomes.paraId(personagem.getNome()));
        int tamanho = PosicoesDeBatalha.TAMANHO_SPRITE;
        int origemInicialX = espelhado ? sprite.getWidth() : 0;
        int origemFinalX = espelhado ? 0 : sprite.getWidth();

        g.drawImage(sprite, x, y, x + tamanho, y + tamanho,
                origemInicialX, 0, origemFinalX, sprite.getHeight(), null);
    }
}
