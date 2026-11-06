package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.ResultadoAcao;

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
    private Habilidade habilidade;

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

    public boolean estaVivo() {
        return pvAtual > 0;
    }

    public int receberDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("O dano não pode ser negativo.");
        }

        int pvAntesDoDano = pvAtual;
        pvAtual = Math.max(0, pvAtual - dano);
        return pvAntesDoDano - pvAtual;
    }

    public int curar(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A cura não pode ser negativa.");
        }

        int pvAntesDaCura = pvAtual;
        pvAtual = (int) Math.min(pvMaximo, (long) pvAtual + quantidade);
        return pvAtual - pvAntesDaCura;
    }

    public int curarPercentualDaVidaMaxima(int percentual) {
        long valor = Math.max(1L, (long) pvMaximo * percentual / 100);
        int quantidade = (int) Math.min(pvMaximo, valor);
        return curar(quantidade);
    }

    /**
     * Calcula o dano como max(1, ataque do atacante + poder base - defesa do alvo).
     */
    public int calcularDano(Personagem alvo, int poderBase) {
        return Math.max(1, getAtaque() + poderBase - alvo.getDefesa());
    }

    /**
     * Executa o ataque básico contra o alvo e retorna o dano causado.
     */
    public abstract int atacar(Personagem alvo);

    public abstract String getNomeAtaqueBasico();

    public String resumoDeStatus() {
        return getNome() + " (" + getTipo().getRotulo() + ") | PV "
                + getPvAtual() + "/" + getPvMaximo()
                + " | ATQ " + getAtaque() + " | DEF " + getDefesa();
    }

    public Habilidade getHabilidade() {
        return habilidade;
    }

    protected void definirHabilidade(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("A habilidade não pode ser nula.");
        }

        this.habilidade = habilidade;
    }

    public ResultadoAcao usarHabilidade(Personagem alvo) {
        if (habilidade == null) {
            throw new IllegalStateException("O personagem não possui uma habilidade.");
        }

        return habilidade.executar(this, alvo);
    }
}
