package br.folclore.modelo;

/**
 * Abstração: base comum de todo combatente
 */
public abstract class Personagem {
    private final String nome;
    private final Tipo tipo;
    private int pvMaximo;
    private int pvAtual;
    private int ataque;
    private int defesa;
    private int velocidade;

    protected Personagem(String nome, Tipo tipo, int pvMaximo, int ataque, int defesa, int velocidade) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo.");
        }
        if (pvMaximo <= 0) {
            throw new IllegalArgumentException("Os pontos de vida máximos devem ser maiores que zero.");
        }
        if (ataque < 0 || defesa < 0 || velocidade < 0) {
            throw new IllegalArgumentException("Ataque, defesa e velocidade não podem ser negativos.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.pvMaximo = pvMaximo;
        this.pvAtual = pvMaximo;
        this.ataque = ataque;
        this.defesa = defesa;
        this.velocidade = velocidade;
    }

    public String getNome() {
        return nome;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public int getPvMaximo() {
        return pvMaximo;
    }

    public int getPvAtual() {
        return pvAtual;
    }
}
