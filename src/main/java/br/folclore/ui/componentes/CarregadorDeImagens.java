package br.folclore.ui.componentes;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public final class CarregadorDeImagens {

    private CarregadorDeImagens() {
    }

    public static BufferedImage carregar(String caminho) {
        try (InputStream recurso = CarregadorDeImagens.class.getResourceAsStream(caminho)) {
            if (recurso != null) {
                return ImageIO.read(recurso);
            }
        } catch (IOException e) {
            System.err.println("Falha ao carregar imagem: " + caminho);
        }
        return null;
    }
}
