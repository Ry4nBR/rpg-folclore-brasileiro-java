package br.folclore.ui.efeitos;

import br.folclore.batalha.Batalha;
import br.folclore.modelo.MaeDeOuro;
import br.folclore.modelo.Saci;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.SpriteAnimado;
import br.folclore.ui.telas.TelaBatalha;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/** Verificacao executavel sem bibliotecas externas. */
public final class AnimacaoGifVerificacao {
    public static void main(String[] args) throws Exception {
        SpriteAnimado sprite;
        try (InputStream entrada = AnimacaoGifVerificacao.class.getResourceAsStream("/efeitos/saci_habilidade.gif")) {
            conferir(entrada != null, "GIF da habilidade incluido nos recursos");
            sprite = SpriteAnimado.carregar(entrada);
        }
        conferir(sprite.quadroEm(0).getWidth() == 362, "Arquivo corresponde a referencia");
        long[] relogio = {0};
        AnimacaoGif animacao = new AnimacaoGif(sprite, 0, 0, 362, false, () -> relogio[0]);
        conferir(!animacao.terminou(), "Comeca ativa");
        conferir(desenhar(animacao).getRGB(181, 181) == sprite.quadroEm(0).getRGB(181, 181),
                "Comeca no primeiro quadro");
        relogio[0] = (sprite.getDuracaoMs() - 1) * 1_000_000;
        animacao.atualizar();
        conferir(!animacao.terminou(), "Ultimo quadro mantido ate o fim");
        GerenciadorDeEfeitos efeitos = new GerenciadorDeEfeitos();
        efeitos.adicionar(animacao);
        relogio[0] += 1_000_000;
        efeitos.atualizar();
        conferir(animacao.terminou() && !efeitos.temAtivas(), "Encerra apos um ciclo completo");
        conferir(desenhar(animacao).getRGB(181, 181) == 0, "Nao desenha depois de terminar");
        AnimacaoGif repeticao = new AnimacaoGif(sprite, 0, 0, 362, false, () -> relogio[0]);
        conferir(!repeticao.terminou(), "Novo uso reinicia a reproducao");
        conferir(desenhar(repeticao).getRGB(181, 181) == sprite.quadroEm(0).getRGB(181, 181),
                "Novo uso comeca no primeiro quadro");

        Saci saci = new Saci();
        saci.receberDano(20);
        Batalha batalha = new Batalha(saci, new MaeDeOuro());
        TelaBatalha tela = new TelaBatalha(new GerenciadorDeTelas(), batalha, resultado -> {});
        Method usar = TelaBatalha.class.getDeclaredMethod("usarHabilidade", int.class);
        usar.setAccessible(true);
        int pvAntes = saci.getPvAtual();
        int usosAntes = saci.getHabilidades().get(0).getUsosRestantes();
        usar.invoke(tela, 0);
        Field efeito = TelaBatalha.class.getDeclaredField("animacaoHabilidadeSaci");
        efeito.setAccessible(true);
        conferir(efeito.get(tela) instanceof AnimacaoGif, "Habilidade inicia o GIF exclusivo");
        conferir(saci.getPvAtual() > pvAntes, "Cura original preservada");
        conferir(saci.getHabilidades().get(0).getUsosRestantes() == usosAntes - 1, "Consome um uso");
        usar.invoke(tela, 0);
        conferir(saci.getHabilidades().get(0).getUsosRestantes() == usosAntes - 1,
                "Nao permite ativar novamente durante o efeito");
        BufferedImage batalhaRenderizada = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = batalhaRenderizada.createGraphics();
        try {
            tela.desenhar(g);
        } finally {
            g.dispose();
        }
        javax.imageio.ImageIO.write(batalhaRenderizada, "png", new java.io.File("target/saci-habilidade-preview.png"));
        System.out.println("OK: GIF de habilidade (" + sprite.getDuracaoMs()
                + " ms), ciclo unico, reinicio, integracao da cura e bloqueio de repeticao.");
    }

    private static BufferedImage desenhar(AnimacaoGif animacao) {
        BufferedImage imagem = new BufferedImage(362, 362, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagem.createGraphics();
        try {
            animacao.desenhar(g);
        } finally {
            g.dispose();
        }
        return imagem;
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
