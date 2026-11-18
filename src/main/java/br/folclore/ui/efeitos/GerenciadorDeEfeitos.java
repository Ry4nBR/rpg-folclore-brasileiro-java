package br.folclore.ui.efeitos;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class GerenciadorDeEfeitos {
    private final List<Animacao> animacoes = new ArrayList<>();

    public void adicionar(Animacao animacao) {
        if (animacao == null) {
            throw new IllegalArgumentException("A animação não pode ser nula.");
        }
        animacoes.add(animacao);
    }

    public void atualizar() {
        Iterator<Animacao> iterator = animacoes.iterator();
        while (iterator.hasNext()) {
            Animacao animacao = iterator.next();
            animacao.atualizar();
            if (animacao.terminou()) {
                iterator.remove();
            }
        }
    }

    public void desenhar(Graphics2D g) {
        for (Animacao animacao : animacoes) {
            animacao.desenhar(g);
        }
    }

    public boolean temAtivas() {
        return !animacoes.isEmpty();
    }
}
