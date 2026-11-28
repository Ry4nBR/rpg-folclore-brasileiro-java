package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.modelo.MaeDeOuro;
import br.folclore.modelo.Saci;
import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.SpriteAnimado;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.lang.reflect.Field;
import java.util.Map;
import javax.imageio.ImageIO;

public final class AnimacoesSaciVerificacao {
    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws Exception {
        String[] caminhos = {"saci_ataque.gif", "saci_habilidade.gif",
                "saci_vento_revigorante.gif", "saci_assobio.gif"};
        Field cache = TelaBatalha.class.getDeclaredField("animacoesSaci");
        cache.setAccessible(true);
        Field ativa = TelaBatalha.class.getDeclaredField("animacaoHabilidadeSaci");
        ativa.setAccessible(true);
        for (int slot = 0; slot < caminhos.length; slot++) {
            Saci saci = new Saci();
            saci.receberDano(10);
            TelaBatalha tela = new TelaBatalha(new GerenciadorDeTelas(),
                    new Batalha(saci, new MaeDeOuro()), resultado -> {});
            tela.aoPressionarTecla(KeyEvent.VK_ENTER);
            tela.aoPressionarTecla(KeyEvent.VK_ENTER);
            tela.aoPressionarTecla(KeyEvent.VK_A);
            tela.aoPressionarTecla(KeyEvent.VK_1 + slot);
            Map<String, SpriteAnimado> carregadas = (Map<String, SpriteAnimado>) cache.get(tela);
            SpriteAnimado sprite = carregadas.get("/efeitos/" + caminhos[slot]);
            conferir(sprite != null && carregadas.size() == 1, "GIF correto para o slot " + slot);
            conferir(ativa.get(tela) != null, "Animacao iniciada");
            salvar(tela, "target/saci-golpe-" + (slot + 1) + ".png");
            int usos = saci.getHabilidades().get(0).getUsosRestantes();
            tela.aoPressionarTecla(KeyEvent.VK_A);
            tela.aoPressionarTecla(KeyEvent.VK_2);
            conferir(saci.getHabilidades().get(0).getUsosRestantes() == usos, "Bloqueia acao durante GIF");
            long limite = System.nanoTime() + (sprite.getDuracaoMs() + 2000) * 1_000_000L;
            while (ativa.get(tela) != null && System.nanoTime() < limite) {
                tela.atualizar();
                Thread.sleep(10);
            }
            conferir(ativa.get(tela) == null, "GIF termina e restaura o sprite normal");
            System.out.println("OK: " + caminhos[slot] + ", " + sprite.getDuracaoMs() + " ms.");
        }
    }

    private static void salvar(TelaBatalha tela, String caminho) throws Exception {
        BufferedImage imagem = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagem.createGraphics();
        try {
            tela.desenhar(g);
        } finally {
            g.dispose();
        }
        ImageIO.write(imagem, "png", new File(caminho));
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
