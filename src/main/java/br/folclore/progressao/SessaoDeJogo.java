package br.folclore.progressao;

public class SessaoDeJogo {
    private final Equipe equipe;
    private int ginasiosVencidos = 0;
    private int encontrosVencidos = 0;
    private Ginasio ginasioAtual;

    public SessaoDeJogo(Equipe equipe) {
        if (equipe == null) {
            throw new IllegalArgumentException("A equipe não pode ser nula.");
        }

        this.equipe = equipe;
    }

    public Equipe getEquipe() {
        return equipe;
    }

    public int getGinasiosVencidos() {
        return ginasiosVencidos;
    }

    public int getEncontrosVencidos() {
        return encontrosVencidos;
    }

    public Ginasio getGinasioAtual() {
        return ginasioAtual;
    }

    public boolean portaLiberada(int indiceGinasio) {
        return indiceGinasio >= 0
                && indiceGinasio < 3
                && indiceGinasio <= ginasiosVencidos;
    }
}
