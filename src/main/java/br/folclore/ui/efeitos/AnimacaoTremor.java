package br.folclore.ui.efeitos;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.function.Supplier;

public final class AnimacaoTremor implements Animacao {
    public static final int DURACAO_QUADROS = 15;
    private static final int AMPLITUDE = 8;

    private final Supplier<BufferedImage> fornecedorSprite;
    private final int x;
    private final int y;
    private final int tamanho;
    private final boolean espelhado;
    private int quadroAtual;

    public AnimacaoTremor(BufferedImage sprite, int x, int y, int tamanho,
                          boolean espelhado) {
        this(() -> sprite, x, y, tamanho, espelhado);
    }

    public AnimacaoTremor(Supplier<BufferedImage> fornecedorSprite, int x, int y, int tamanho,
                          boolean espelhado) {
        if (fornecedorSprite == null || fornecedorSprite.get() == null) {
            throw new IllegalArgumentException("O sprite não pode ser nulo.");
        }
        if (tamanho <= 0) {
            throw new IllegalArgumentException("O tamanho do sprite deve ser positivo.");
        }

        this.fornecedorSprite = fornecedorSprite;
        this.x = x;
        this.y = y;
        this.tamanho = tamanho;
        this.espelhado = espelhado;
    }

    @Override
    public void atualizar() {
        if (!terminou()) {
            quadroAtual++;
        }
    }

    @Override
    public void desenhar(Graphics2D g) {
        BufferedImage sprite = fornecedorSprite.get();
        int deslocamentoX = (int) Math.round(AMPLITUDE
                * Math.sin(2 * Math.PI * quadroAtual / DURACAO_QUADROS));
        int posicaoX = x + deslocamentoX;
        if (espelhado) {
            g.drawImage(sprite, posicaoX + tamanho, y, posicaoX, y + tamanho,
                    0, 0, sprite.getWidth(), sprite.getHeight(), null);
        } else {
            g.drawImage(sprite, posicaoX, y, tamanho, tamanho, null);
        }
    }

    @Override
    public boolean terminou() {
        return quadroAtual >= DURACAO_QUADROS;
    }
}
