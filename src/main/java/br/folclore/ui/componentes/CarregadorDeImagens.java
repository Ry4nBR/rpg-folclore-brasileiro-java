package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import javax.imageio.ImageIO;

public final class CarregadorDeImagens {

    private static final Map<String, BufferedImage> CACHE = new HashMap<>();

    private CarregadorDeImagens() {
    }

    public static synchronized BufferedImage carregar(String caminho) {
        BufferedImage imagemCacheada = CACHE.get(caminho);
        if (imagemCacheada != null) {
            return imagemCacheada;
        }

        try (InputStream recurso = CarregadorDeImagens.class.getResourceAsStream(caminho)) {
            if (recurso != null) {
                BufferedImage imagem = ImageIO.read(recurso);
                if (imagem != null) {
                    CACHE.put(caminho, imagem);
                    return imagem;
                }
            }
        } catch (IOException e) {
            System.err.println("Falha ao carregar imagem: " + caminho);
        }

        System.err.println("Imagem nao encontrada: " + caminho);
        BufferedImage substituta = new BufferedImage(32, 32, BufferedImage.TYPE_INT_RGB);
        java.awt.Graphics2D g = substituta.createGraphics();
        try {
            g.setColor(java.awt.Color.MAGENTA);
            g.fillRect(0, 0, 32, 32);
        } finally {
            g.dispose();
        }
        CACHE.put(caminho, substituta);
        return substituta;
    }
}
