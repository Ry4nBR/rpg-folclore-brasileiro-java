package br.folclore.demo;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.batalha.ResultadoBatalha;
import br.folclore.modelo.Lobisomem;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Saci;

public final class DemoBatalhaDerrota {
    private DemoBatalhaDerrota() {
    }

    public static void main(String[] args) {
        Saci saci = new Saci();
        saci.receberDano(59);
        Batalha batalhaDerrota = new Batalha(saci, new MulaSemCabeca());
        imprimirMensagens(batalhaDerrota);
        batalhaDerrota.acaoAtacar();
        imprimirMensagens(batalhaDerrota);
        batalhaDerrota.executarTurnoDoChefe();
        imprimirMensagens(batalhaDerrota);
        if (batalhaDerrota.getResultado() != ResultadoBatalha.DERROTA) {
            throw new AssertionError("A batalha deveria terminar em derrota.");
        }
        System.out.println(batalhaDerrota.getResultado());

        Batalha batalhaHabilidade = new Batalha(new Lobisomem(), new MulaSemCabeca());
        imprimirMensagens(batalhaHabilidade);
        if (!batalhaHabilidade.acaoHabilidade()) {
            throw new AssertionError("O primeiro uso da habilidade deveria ser aceito.");
        }
        imprimirMensagens(batalhaHabilidade);
        batalhaHabilidade.executarTurnoDoChefe();
        imprimirMensagens(batalhaHabilidade);
        if (!batalhaHabilidade.acaoHabilidade()) {
            throw new AssertionError("O segundo uso da habilidade deveria ser aceito.");
        }
        imprimirMensagens(batalhaHabilidade);
        batalhaHabilidade.executarTurnoDoChefe();
        imprimirMensagens(batalhaHabilidade);
        if (batalhaHabilidade.acaoHabilidade()
                || batalhaHabilidade.getEstado() != EstadoBatalha.ESCOLHA_DO_JOGADOR) {
            throw new AssertionError("O terceiro uso deve ser recusado sem consumir o turno.");
        }
        imprimirMensagens(batalhaHabilidade);
    }

    private static void imprimirMensagens(Batalha batalha) {
        for (String mensagem : batalha.consumirMensagens()) {
            System.out.println(mensagem);
        }
    }
}
