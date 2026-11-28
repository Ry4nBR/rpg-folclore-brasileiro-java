package br.folclore.ui.componentes;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriter;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;
import org.w3c.dom.Node;
import br.folclore.ui.efeitos.AnimacaoDeslocamento;
import br.folclore.ui.efeitos.AnimacaoTremor;

/** Verificacao executavel sem dependencias: rode esta classe depois de test-compile. */
public final class SpriteAnimadoVerificacao {
    public static void main(String[] args) throws Exception {
        verificarComposicaoETempos();
        verificarSaci();
        verificarEfeitos();
        System.out.println("OK: tempos, loop, transparencia, descarte, GIF do Saci e efeitos.");
    }

    private static void verificarComposicaoETempos() throws Exception {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        ImageWriter escritor = ImageIO.getImageWritersByFormatName("gif").next();
        try (ImageOutputStream saida = ImageIO.createImageOutputStream(bytes)) {
            escritor.setOutput(saida);
            escritor.prepareWriteSequence(null);
            escrever(escritor, 3, Color.RED, 0, 2, "doNotDispose");
            escrever(escritor, 1, Color.BLUE, 1, 3, "restoreToPrevious");
            escrever(escritor, 1, Color.GREEN, 2, 4, "restoreToBackgroundColor");
            escrever(escritor, 1, Color.YELLOW, 0, 5, "doNotDispose");
            escritor.endWriteSequence();
        } finally {
            escritor.dispose();
        }
        SpriteAnimado animacao = SpriteAnimado.carregar(new ByteArrayInputStream(bytes.toByteArray()));
        conferir(animacao.quadroEm(0).getRGB(1, 0) == Color.RED.getRGB(), "Primeiro quadro");
        conferir(animacao.quadroEm(19) == animacao.quadroEm(0), "Duracao do primeiro quadro");
        conferir(animacao.quadroEm(20).getRGB(1, 0) == Color.BLUE.getRGB(), "Deslocamento do quadro parcial");
        conferir(animacao.quadroEm(20).getRGB(2, 0) == Color.RED.getRGB(), "Preservacao do fundo");
        conferir(animacao.quadroEm(50).getRGB(1, 0) == Color.RED.getRGB(), "Restauracao do quadro anterior");
        conferir(animacao.quadroEm(50).getRGB(2, 0) == Color.GREEN.getRGB(), "Terceiro quadro");
        conferir(animacao.quadroEm(90).getRGB(0, 0) == Color.YELLOW.getRGB(), "Quarto quadro");
        conferir((animacao.quadroEm(90).getRGB(2, 0) >>> 24) == 0, "Limpeza transparente sem rastros");
        conferir(animacao.quadroEm(140) == animacao.quadroEm(0), "Reinicio do loop");
        conferir(animacao.quadroEm(140 * 1000L + 20) == animacao.quadroEm(20), "Loop prolongado");
    }

    private static void escrever(ImageWriter escritor, int largura, Color cor, int x,
                                 int atraso, String descarte) throws Exception {
        java.awt.image.IndexColorModel paleta = new java.awt.image.IndexColorModel(8, 2,
                new byte[] {0, (byte) cor.getRed()}, new byte[] {0, (byte) cor.getGreen()},
                new byte[] {0, (byte) cor.getBlue()}, 0);
        BufferedImage imagem = new BufferedImage(largura, 1, BufferedImage.TYPE_BYTE_INDEXED, paleta);
        for (int i = 0; i < largura; i++) {
            imagem.getRaster().setSample(i, 0, 0, 1);
        }
        IIOMetadata dados = escritor.getDefaultImageMetadata(new javax.imageio.ImageTypeSpecifier(imagem), null);
        IIOMetadataNode raiz = (IIOMetadataNode) dados.getAsTree("javax_imageio_gif_image_1.0");
        IIOMetadataNode descritor = (IIOMetadataNode) raiz.getElementsByTagName("ImageDescriptor").item(0);
        descritor.setAttribute("imageLeftPosition", Integer.toString(x));
        IIOMetadataNode controle = (IIOMetadataNode) raiz.getElementsByTagName("GraphicControlExtension").item(0);
        controle.setAttribute("delayTime", Integer.toString(atraso));
        controle.setAttribute("disposalMethod", descarte);
        controle.setAttribute("transparentColorFlag", "TRUE");
        controle.setAttribute("transparentColorIndex", "0");
        dados.setFromTree("javax_imageio_gif_image_1.0", raiz);
        escritor.writeToSequence(new IIOImage(imagem, null, dados), null);
    }

    private static void verificarSaci() throws Exception {
        SpriteAnimado animacao;
        try (InputStream entrada = SpriteAnimadoVerificacao.class.getResourceAsStream("/sprites/saci.gif")) {
            conferir(entrada != null, "Recurso saci.gif presente");
            animacao = SpriteAnimado.carregar(entrada);
        }
        try (InputStream entrada = SpriteAnimadoVerificacao.class.getResourceAsStream("/sprites/saci.gif");
             ImageInputStream fluxo = ImageIO.createImageInputStream(entrada)) {
            ImageReader leitor = ImageIO.getImageReadersByFormatName("gif").next();
            try {
                leitor.setInput(fluxo);
                long tempo = 0;
                BufferedImage anterior = null;
                int quantidade = leitor.getNumImages(true);
                for (int i = 0; i < quantidade; i++) {
                    BufferedImage quadro = animacao.quadroEm(tempo);
                    conferir(quadro != anterior, "Avanco do quadro " + i);
                    conferir(quadro.getWidth() == 418 && quadro.getHeight() == 418, "Dimensoes do Saci");
                    Node raiz = leitor.getImageMetadata(i).getAsTree("javax_imageio_gif_image_1.0");
                    for (Node node = raiz.getFirstChild(); node != null; node = node.getNextSibling()) {
                        if ("GraphicControlExtension".equals(node.getNodeName())) {
                            int atraso = Integer.parseInt(node.getAttributes().getNamedItem("delayTime").getNodeValue());
                            tempo += atraso > 0 ? atraso * 10L : 100L;
                        }
                    }
                    conferir(animacao.quadroEm(tempo - 1) == quadro, "Duracao do quadro " + i);
                    anterior = quadro;
                }
                conferir(animacao.quadroEm(tempo) == animacao.quadroEm(0), "Loop do Saci");
                System.out.println("Saci: " + quantidade + " quadros, ciclo de " + tempo + " ms.");
            } finally {
                leitor.dispose();
            }
        }
        conferir(RepositorioDeSprites.obter("saci").getWidth() == 418, "Integracao do repositorio");
        conferir(RepositorioDeSprites.obter("lobisomem") != null, "PNG preservado");
    }

    private static void verificarEfeitos() {
        BufferedImage sprite = new BufferedImage(2, 2, BufferedImage.TYPE_INT_ARGB);
        BufferedImage tela = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        int[] chamadas = {0};
        java.util.function.Supplier<BufferedImage> fornecedor = () -> { chamadas[0]++; return sprite; };
        AnimacaoDeslocamento ataque = new AnimacaoDeslocamento(fornecedor, 0, 0, 50, 50, 48, true);
        AnimacaoTremor dano = new AnimacaoTremor(fornecedor, 0, 0, 48, false);
        int antes = chamadas[0];
        Graphics2D g = tela.createGraphics();
        try {
            ataque.desenhar(g);
            dano.desenhar(g);
            ataque.atualizar();
            dano.atualizar();
            ataque.desenhar(g);
            dano.desenhar(g);
        } finally {
            g.dispose();
        }
        conferir(chamadas[0] == antes + 4, "Efeitos consultam o quadro atual a cada desenho");
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
