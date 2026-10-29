package br.folclore.modelo;

/**
 * Abstração: base comum de todo combatente
 */
public abstract class Personagem {
    private final String nome;
    private final Tipo tipo;

    protected Personagem(String nome, Tipo tipo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo.");
        }

        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
