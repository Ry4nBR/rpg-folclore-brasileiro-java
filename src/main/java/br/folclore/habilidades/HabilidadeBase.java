package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public abstract class HabilidadeBase implements Habilidade {
    private final String nome;
    private final int usosMaximos;
    private int usosRestantes;

    protected HabilidadeBase(String nome, int usosMaximos) {
        if (usosMaximos <= 0) {
            throw new IllegalArgumentException("Os usos máximos devem ser positivos.");
        }

        this.nome = nome;
        this.usosMaximos = usosMaximos;
        this.usosRestantes = usosMaximos;
    }

    @Override
    public String getNome() {
        return nome;
    }

    @Override
    public int getUsosMaximos() {
        return usosMaximos;
    }

    @Override
    public int getUsosRestantes() {
        return usosRestantes;
    }

    @Override
    public boolean temUsosRestantes() {
        return usosRestantes > 0;
    }

    protected void consumirUso() {
        if (!temUsosRestantes()) {
            throw new IllegalStateException("Não há usos restantes de " + nome + ".");
        }

        usosRestantes--;
    }

    @Override
    public abstract ResultadoAcao executar(Personagem usuario, Personagem alvo);
}
