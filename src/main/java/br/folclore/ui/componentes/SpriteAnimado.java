package br.folclore.ui.componentes;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import org.w3c.dom.Node;

/** Quadros compostos uma vez; a reproducao usa o tempo real, em loop. */
public final class SpriteAnimado {
    private final List<BufferedImage> quadros;
    private final List<Long> finaisMs;
    private final long duracaoMs;
    private final long inicioNanos = System.nanoTime();

    private SpriteAnimado(List<BufferedImage> quadros, List<Long> finaisMs) {
        this.quadros = List.copyOf(quadros);
        this.finaisMs = List.copyOf(finaisMs);
        this.duracaoMs = finaisMs.get(finaisMs.size() - 1);
    }

    public static SpriteAnimado carregar(InputStream entrada) throws IOException {
        try (ImageInputStream fluxo = ImageIO.createImageInputStream(entrada)) {
            ImageReader leitor = ImageIO.getImageReadersByFormatName("gif").next();
            try {
                leitor.setInput(fluxo);
                Node metadados = leitor.getStreamMetadata().getAsTree("javax_imageio_gif_stream_1.0");
                Node tela = filho(metadados, "LogicalScreenDescriptor");
                int largura = inteiro(tela, "logicalScreenWidth");
                int altura = inteiro(tela, "logicalScreenHeight");
                Color fundo = corDeFundo(metadados);
                BufferedImage composicao = new BufferedImage(largura, altura, BufferedImage.TYPE_INT_ARGB);
                List<BufferedImage> quadros = new ArrayList<>();
                List<Long> finais = new ArrayList<>();
                long tempo = 0;
                int quantidade = leitor.getNumImages(true);
                for (int i = 0; i < quantidade; i++) {
                    Node dados = leitor.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0");
                    Node descritor = filho(dados, "ImageDescriptor");
                    Node controle = filho(dados, "GraphicControlExtension");
                    int x = inteiro(descritor, "imageLeftPosition");
                    int y = inteiro(descritor, "imageTopPosition");
                    String descarte = atributo(controle, "disposalMethod");
                    boolean transparente = "TRUE".equals(atributo(controle, "transparentColorFlag"));
                    if (i == 0 && !transparente) {
                        preencher(composicao, 0, 0, largura, altura, fundo);
                    }
                    BufferedImage anterior = "restoreToPrevious".equals(descarte) ? copiar(composicao) : null;
                    BufferedImage parcial = leitor.read(i);
                    Graphics2D g = composicao.createGraphics();
                    try {
                        g.drawImage(parcial, x, y, null);
                    } finally {
                        g.dispose();
                    }
                    quadros.add(copiar(composicao));
                    int atraso = controle == null ? 0 : inteiro(controle, "delayTime");
                    // GIF usa centesimos de segundo; atraso zero recebe 100 ms.
                    tempo += atraso > 0 ? atraso * 10L : 100L;
                    finais.add(tempo);
                    if ("restoreToBackgroundColor".equals(descarte)) {
                        preencher(composicao, x, y, inteiro(descritor, "imageWidth"),
                                inteiro(descritor, "imageHeight"), transparente ? new Color(0, true) : fundo);
                    } else if (anterior != null) {
                        composicao = anterior;
                    }
                }
                if (quadros.isEmpty()) {
                    throw new IOException("GIF sem quadros");
                }
                return new SpriteAnimado(quadros, finais);
            } finally {
                leitor.dispose();
            }
        }
    }

    BufferedImage atual() {
        return quadroEm((System.nanoTime() - inicioNanos) / 1_000_000L);
    }

    public BufferedImage quadroEm(long tempoMs) {
        long posicao = Math.floorMod(tempoMs, duracaoMs);
        for (int i = 0; i < finaisMs.size(); i++) {
            if (posicao < finaisMs.get(i)) {
                return quadros.get(i);
            }
        }
        return quadros.get(0);
    }

    public long getDuracaoMs() {
        return duracaoMs;
    }

    private static BufferedImage copiar(BufferedImage origem) {
        BufferedImage copia = new BufferedImage(origem.getWidth(), origem.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = copia.createGraphics();
        try {
            g.drawImage(origem, 0, 0, null);
        } finally {
            g.dispose();
        }
        return copia;
    }

    private static void preencher(BufferedImage imagem, int x, int y, int largura, int altura, Color cor) {
        Graphics2D g = imagem.createGraphics();
        try {
            g.setComposite(AlphaComposite.Src);
            g.setColor(cor);
            g.fillRect(x, y, largura, altura);
        } finally {
            g.dispose();
        }
    }

    private static Color corDeFundo(Node dados) {
        Node tabela = filho(dados, "GlobalColorTable");
        if (tabela != null) {
            String indice = atributo(tabela, "backgroundColorIndex");
            for (Node entrada = tabela.getFirstChild(); entrada != null; entrada = entrada.getNextSibling()) {
                if (indice.equals(atributo(entrada, "index"))) {
                    return new Color(inteiro(entrada, "red"), inteiro(entrada, "green"), inteiro(entrada, "blue"));
                }
            }
        }
        return new Color(0, true);
    }

    private static Node filho(Node pai, String nome) {
        for (Node node = pai.getFirstChild(); node != null; node = node.getNextSibling()) {
            if (nome.equals(node.getNodeName())) {
                return node;
            }
        }
        return null;
    }

    private static String atributo(Node node, String nome) {
        return node == null ? "" : node.getAttributes().getNamedItem(nome).getNodeValue();
    }

    private static int inteiro(Node node, String nome) {
        return Integer.parseInt(atributo(node, nome));
    }
}
