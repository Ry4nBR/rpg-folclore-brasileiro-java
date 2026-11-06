package br.folclore.modelo;

/**
 * Abstração: base comum de todo combatente.
 */
public abstract class Personagem {
    private final String nome;
    private final Tipo tipo;
    private int pvMaximo;
    private int pvAtual;
    private final int ataque;
    private final int defesa;

    protected Personagem(String nome, Tipo tipo, int pvMaximo, int ataque, int defesa) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome não pode ser vazio.");
        }
        if (tipo == null) {
            throw new IllegalArgumentException("O tipo não pode ser nulo.");
        }
        if (pvMaximo <= 0) {
            throw new IllegalArgumentException("O PV máximo deve ser positivo.");
        }
        if (ataque < 0 || defesa < 0) {
            throw new IllegalArgumentException("Ataque e defesa não podem ser negativos.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.pvMaximo = pvMaximo;
        this.pvAtual = pvMaximo;
        this.ataque = ataque;
        this.defesa = defesa;
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

    public int getAtaque() {
        return ataque;
    }

    public int getDefesa() {
        return defesa;
    }
}
