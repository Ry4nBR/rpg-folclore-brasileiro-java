package br.folclore.habilidades;

public abstract class HabilidadeBase implements Habilidade {
    private final String nome;
    private final int usosMaximos;
    private int usosRestantes;

    protected HabilidadeBase(String nome, int usosMaximos) {
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

    @Override
    public void restaurarUsos() {
        usosRestantes = usosMaximos;
    }

    protected void consumirUso() {
        if (!temUsosRestantes()) {
            throw new IllegalStateException("Não há usos restantes para esta habilidade.");
        }

        usosRestantes--;
    }
}
