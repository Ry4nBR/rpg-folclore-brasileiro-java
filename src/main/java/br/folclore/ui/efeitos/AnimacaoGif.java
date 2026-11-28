package br.folclore.ui.efeitos;

import br.folclore.ui.componentes.SpriteAnimado;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Objects;
import java.util.function.LongSupplier;

/** Reproduz um ciclo completo do GIF, reiniciando no primeiro quadro a cada uso. */
public final class AnimacaoGif implements Animacao {
    private final SpriteAnimado sprite;
    private final int x;
    private final int y;
    private final int tamanho;
    private final boolean espelhado;
    private final LongSupplier relogio;
    private final long inicioNanos;
    private long decorridoMs;

    public AnimacaoGif(SpriteAnimado sprite, int x, int y, int tamanho, boolean espelhado) {
        this(sprite, x, y, tamanho, espelhado, System::nanoTime);
    }

    AnimacaoGif(SpriteAnimado sprite, int x, int y, int tamanho, boolean espelhado,
                LongSupplier relogio) {
        this.sprite = Objects.requireNonNull(sprite);
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho do sprite deve ser positivo.");
        }
        this.x = x;
        this.y = y;
        this.tamanho = tamanho;
        this.espelhado = espelhado;
        this.relogio = Objects.requireNonNull(relogio);
        this.inicioNanos = relogio.getAsLong();
    }

    @Override
    public void atualizar() {
        decorridoMs = Math.max(0, (relogio.getAsLong() - inicioNanos) / 1_000_000L);
    }

    @Override
    public void desenhar(Graphics2D g) {
        if (terminou()) {
            return;
        }
        BufferedImage quadro = sprite.quadroEm(decorridoMs);
        int esquerda = espelhado ? x + tamanho : x;
        int direita = espelhado ? x : x + tamanho;
        g.drawImage(quadro, esquerda, y, direita, y + tamanho,
                0, 0, quadro.getWidth(), quadro.getHeight(), null);
    }

    @Override
    public boolean terminou() {
        return decorridoMs >= sprite.getDuracaoMs();
    }
}
