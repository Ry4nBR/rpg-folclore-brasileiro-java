package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.ResultadoAcao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Abstração: base comum de todo combatente
 */
public abstract class Personagem {
    public static final int MAX_HABILIDADES_EQUIPADAS = 3;

    private final List<Habilidade> habilidades = new ArrayList<>();
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

    public List<Habilidade> getHabilidades() {
        return Collections.unmodifiableList(habilidades);
    }

    protected void adicionarHabilidade(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("A habilidade não pode ser nula.");
        }
        if (habilidades.size() >= MAX_HABILIDADES_EQUIPADAS) {
            throw new IllegalArgumentException("O personagem não pode equipar mais de "
                    + MAX_HABILIDADES_EQUIPADAS + " habilidades.");
        }

        habilidades.add(habilidade);
    }

    public ResultadoAcao usarHabilidade(int indice, Personagem alvo) {
        if (indice < 0 || indice >= habilidades.size()) {
            throw new IllegalArgumentException("Índice de habilidade inválido.");
        }

        return habilidades.get(indice).executar(this, alvo);
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

    public int getVelocidade() {
        return velocidade;
    }

    public boolean estaVivo() {
        return pvAtual > 0;
    }

    public int receberDano(int dano) {
        if (dano < 0) {
            throw new IllegalArgumentException("O dano não pode ser negativo.");
        }

        int pvAnterior = pvAtual;
        pvAtual = Math.max(0, pvAtual - dano);
        return pvAnterior - pvAtual;
    }

    public int curar(int quantidade) {
        if (quantidade < 0) {
            throw new IllegalArgumentException("A quantidade de cura não pode ser negativa.");
        }

        int pvAnterior = pvAtual;
        pvAtual = (int) Math.min(pvMaximo, (long) pvAtual + quantidade);
        return pvAtual - pvAnterior;
    }

    public int curarPercentualDaVidaMaxima(int percentual) {
        int valor = Math.max(1, pvMaximo * percentual / 100);
        return curar(valor);
    }

    public int calcularDano(Personagem alvo, int poderBase) {
        return Math.max(1, getAtaque() + poderBase - alvo.getDefesa());
    }

    /**
     * Define o ataque básico de cada subclasse por meio de polimorfismo.
     *
     * @param alvo personagem que receberá o ataque
     * @return dano causado ao alvo
     */
    public abstract int atacar(Personagem alvo);

    public abstract String getNomeAtaqueBasico();

    public String resumoDeStatus() {
        return getNome() + " (" + getTipo().getRotulo() + ") | PV " + getPvAtual() + "/" + getPvMaximo()
                + " | ATQ " + getAtaque() + " | DEF " + getDefesa() + " | VEL " + getVelocidade();
    }
}
