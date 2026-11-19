package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.ResultadoAcao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Abstração: base comum de todo combatente
 */
public abstract class Personagem {
    public static final int MAX_SLOTS = 4;
    public static final int XP_COMUM = 20;
    public static final int XP_CHEFE = 50;
    public static final int CUSTO_MELHORIA_XP = 30;

    private final List<Habilidade> habilidades = new ArrayList<>();
    private final List<Habilidade> poderesLiberados = new ArrayList<>();
    private final String nome;
    private final Tipo tipo;
    private int pvMaximo;
    private int pvAtual;
    private int ataque;
    private int defesa;
    private int velocidade;
    private int xp;
    private int slotsDeHabilidade = 2;

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

    public List<Habilidade> getPoderesLiberados() {
        return Collections.unmodifiableList(poderesLiberados);
    }

    public int getSlotsDeHabilidade() {
        return slotsDeHabilidade;
    }

    public boolean desbloquearSlot() {
        if (slotsDeHabilidade >= MAX_SLOTS) {
            return false;
        }
        slotsDeHabilidade++;
        return true;
    }

    public void restaurarUsosDasHabilidades() {
        for (Habilidade habilidade : habilidades) {
            habilidade.restaurarUsos();
        }
    }

    public void revigorar() {
        restaurarVidaTotal();
        restaurarUsosDasHabilidades();
    }

    protected void adicionarHabilidade(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("A habilidade não pode ser nula.");
        }
        if (habilidades.size() >= slotsDeHabilidade - 1) {
            throw new IllegalArgumentException("O personagem não tem slots livres para equipar outra habilidade.");
        }

        habilidades.add(habilidade);
        poderesLiberados.add(habilidade);
    }

    public boolean liberarPoder(Habilidade habilidade) {
        if (habilidade == null) {
            throw new IllegalArgumentException("O poder liberado não pode ser nulo.");
        }
        boolean jaLiberado = poderesLiberados.stream()
                .anyMatch(poder -> Objects.equals(poder.getNome(), habilidade.getNome()));
        if (jaLiberado) {
            return false;
        }

        habilidade.restaurarUsos();
        poderesLiberados.add(habilidade);
        if (habilidades.size() < slotsDeHabilidade - 1) {
            habilidades.add(habilidade);
        }
        return true;
    }

    public boolean podeTrocarPoderes() {
        return poderesLiberados.size() >= 4;
    }

    public boolean trocarPoderEquipado(int indiceEquipado, Habilidade nova) {
        if (!podeTrocarPoderes()
                || indiceEquipado < 0
                || indiceEquipado >= habilidades.size()
                || !poderesLiberados.contains(nova)) {
            return false;
        }

        habilidades.set(indiceEquipado, nova);
        return true;
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

    public void restaurarVidaTotal() {
        pvAtual = pvMaximo;
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

    public int getXp() {
        return xp;
    }

    public void ganharExperiencia(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A experiência ganha deve ser maior que zero.");
        }
        xp += quantidade;
    }

    public boolean gastarExperiencia(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A experiência gasta deve ser maior que zero.");
        }
        if (xp < quantidade) {
            return false;
        }
        xp -= quantidade;
        return true;
    }

    public boolean podeMelhorar() {
        return xp >= CUSTO_MELHORIA_XP;
    }

    public boolean melhorarAtributo(Atributo atributo) {
        if (atributo == null) {
            throw new IllegalArgumentException("O atributo não pode ser nulo.");
        }
        if (!gastarExperiencia(CUSTO_MELHORIA_XP)) {
            return false;
        }

        switch (atributo) {
            case PV -> {
                pvMaximo += 5;
                pvAtual += 5;
            }
            case ATAQUE -> ataque += 1;
            case DEFESA -> defesa += 1;
            case VELOCIDADE -> velocidade += 1;
        }
        return true;
    }

    public boolean estaVivo() {
        return pvAtual > 0;
    }

    public boolean ehChefe() {
        return false;
    }

    public int getXpRecompensa() {
        return ehChefe() ? XP_CHEFE : XP_COMUM;
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

    public Map<String, String> exportarEstado() {
        Map<String, String> estado = new LinkedHashMap<>();
        estado.put("nome", nome);
        estado.put("pv_atual", Integer.toString(pvAtual));
        estado.put("pv_maximo", Integer.toString(pvMaximo));
        estado.put("ataque", Integer.toString(ataque));
        estado.put("defesa", Integer.toString(defesa));
        estado.put("velocidade", Integer.toString(velocidade));
        estado.put("xp", Integer.toString(xp));
        estado.put("slots", Integer.toString(slotsDeHabilidade));
        estado.put("equipadas", habilidades.stream()
                .map(Habilidade::getNome)
                .collect(Collectors.joining(";")));
        estado.put("liberados", poderesLiberados.stream()
                .map(Habilidade::getNome)
                .collect(Collectors.joining(";")));
        return estado;
    }

    public void restaurarEstado(Map<String, String> dados) {
        if (dados == null) {
            throw new IllegalArgumentException("O estado do personagem não pode ser nulo.");
        }
        if (!nome.equals(dados.get("nome"))) {
            throw new IllegalArgumentException("O estado salvo pertence a outro personagem.");
        }

        int pvAtualSalvo = lerInteiro(dados, "pv_atual");
        int pvMaximoSalvo = lerInteiro(dados, "pv_maximo");
        int ataqueSalvo = lerInteiro(dados, "ataque");
        int defesaSalva = lerInteiro(dados, "defesa");
        int velocidadeSalva = lerInteiro(dados, "velocidade");
        int xpSalvo = lerInteiro(dados, "xp");
        int slotsSalvos = lerInteiro(dados, "slots");

        if (pvMaximoSalvo <= 0 || pvAtualSalvo < 0 || pvAtualSalvo > pvMaximoSalvo) {
            throw new IllegalArgumentException("Os pontos de vida salvos não são válidos.");
        }
        if (ataqueSalvo < 0 || defesaSalva < 0 || velocidadeSalva < 0 || xpSalvo < 0) {
            throw new IllegalArgumentException("Os atributos e a experiência não podem ser negativos.");
        }
        if (slotsSalvos < 2 || slotsSalvos > MAX_SLOTS) {
            throw new IllegalArgumentException("A quantidade de slots salva não é válida.");
        }

        pvAtual = pvAtualSalvo;
        pvMaximo = pvMaximoSalvo;
        ataque = ataqueSalvo;
        defesa = defesaSalva;
        velocidade = velocidadeSalva;
        xp = xpSalvo;
        slotsDeHabilidade = slotsSalvos;
    }

    private static int lerInteiro(Map<String, String> dados, String chave) {
        String valor = dados.get(chave);
        if (valor == null) {
            throw new IllegalArgumentException("O campo obrigatório '" + chave + "' está ausente.");
        }
        try {
            return Integer.parseInt(valor);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("O campo '" + chave + "' não contém um inteiro válido.", e);
        }
    }
}
