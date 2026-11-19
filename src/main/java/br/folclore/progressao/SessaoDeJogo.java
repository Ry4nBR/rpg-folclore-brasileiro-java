package br.folclore.progressao;

import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.Personagem;

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

    public void entrarNoGinasio(int indice) {
        if (!portaLiberada(indice)) {
            throw new IllegalStateException("A porta do ginásio está trancada.");
        }

        ginasioAtual = switch (indice) {
            case 0 -> CatalogoDeGinasios.criarGinasioDeFogo();
            case 1 -> CatalogoDeGinasios.criarGinasioDePlanta();
            case 2 -> CatalogoDeGinasios.criarGinasioDeAgua();
            default -> throw new IllegalArgumentException("Índice de ginásio inválido.");
        };
        encontrosVencidos = 0;
    }

    public Personagem getProximoEncontro() {
        if (ginasioAtual == null || encontrosVencidos >= ginasioAtual.getTotalDeEncontros()) {
            return null;
        }

        return ginasioAtual.getEncontros().get(encontrosVencidos);
    }

    public boolean registrarVitoriaNoEncontro() {
        if (ginasioAtual == null || encontrosVencidos >= ginasioAtual.getTotalDeEncontros()) {
            throw new IllegalStateException("Não há encontro ativo para registrar como vitória.");
        }

        encontrosVencidos++;
        if (encontrosVencidos == ginasioAtual.getTotalDeEncontros()) {
            ginasiosVencidos++;
            if (ginasiosVencidos == 1 || ginasiosVencidos == 2) {
                for (Personagem inicial : CatalogoDeHerois.criarTodos()) {
                    boolean jaRecrutado = equipe.getMembros().stream()
                            .anyMatch(membro -> membro.getNome().equals(inicial.getNome()));
                    if (!jaRecrutado) {
                        equipe.recrutar(inicial);
                        break;
                    }
                }
            }
            return true;
        }

        return false;
    }
}
