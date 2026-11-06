package br.folclore.demo;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Personagem;

public final class DemoBatalhaVitoria {
    private DemoBatalhaVitoria() {
    }

    public static void main(String[] args) {
        for (Personagem heroi : CatalogoDeHerois.criarTodos()) {
            Batalha batalha = new Batalha(heroi, new MulaSemCabeca());
            imprimirMensagens(batalha);

            while (!batalha.estaEncerrada()) {
                if (batalha.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR) {
                    batalha.acaoAtacar();
                } else if (batalha.getEstado() == EstadoBatalha.TURNO_DO_CHEFE) {
                    batalha.executarTurnoDoChefe();
                }
                imprimirMensagens(batalha);
            }

            System.out.println("Resultado: " + batalha.getResultado());
            System.out.println("Turnos: " + batalha.getTurno());
            System.out.println("PV restante: " + heroi.getPvAtual());
            if (batalha.getResultado() != ResultadoBatalha.VITORIA) {
                throw new AssertionError("O herói deveria vencer a batalha.");
            }
        }
    }

    private static void imprimirMensagens(Batalha batalha) {
        for (String mensagem : batalha.consumirMensagens()) {
            System.out.println(mensagem);
        }
    }
}
