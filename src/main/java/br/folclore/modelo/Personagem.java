package br.folclore.modelo;

/**
 * Abstração: base comum de todo combatente
 */
public abstract class Personagem {
    private final String nome;
    private final Tipo tipo;
    private int pvMaximo;
    private int pvAtual;

    protected Personagem(String nome, Tipo tipo, int pvMaximo) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo.");
        }
        if (pvMaximo <= 0) {
            throw new IllegalArgumentException("Os pontos de vida máximos devem ser maiores que zero.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.pvMaximo = pvMaximo;
        this.pvAtual = pvMaximo;
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }
}
