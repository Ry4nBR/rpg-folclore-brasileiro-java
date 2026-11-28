package br.folclore.ui.componentes;

import br.folclore.modelo.Personagem;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public final class RepositorioDeSprites {

    private static final String DIRETORIO_SPRITES = "/sprites/";
    private static final Map<String, SpriteAnimado> ANIMACOES = new HashMap<>();

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
        precarregar("honorato");
    }

    private RepositorioDeSprites() {
    }

    public static synchronized BufferedImage obter(String id) {
        String extensao = "saci".equals(id) ? ".gif" : ".png";
        if (".gif".equals(extensao)) {
            SpriteAnimado animacao = ANIMACOES.get(id);
            if (animacao == null && !ANIMACOES.containsKey(id)) {
                try (InputStream entrada = RepositorioDeSprites.class.getResourceAsStream(DIRETORIO_SPRITES + id + extensao)) {
                    if (entrada != null) {
                        animacao = SpriteAnimado.carregar(entrada);
                    }
                } catch (IOException e) {
                    System.err.println("Falha ao carregar animacao de " + id + ": " + e.getMessage());
                }
                ANIMACOES.put(id, animacao);
            }
            if (animacao != null) {
                return animacao.atual();
            }
        }
        return CarregadorDeImagens.carregar(DIRETORIO_SPRITES + id + extensao);
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
