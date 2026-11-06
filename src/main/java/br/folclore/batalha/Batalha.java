package br.folclore.batalha;

import br.folclore.modelo.Personagem;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa a batalha, na qual a ordem de turno é fixa: primeiro o jogador, depois o chefe.
 */
public class Batalha {
    private final Personagem jogador;
    private final Personagem chefe;
    private EstadoBatalha estado = EstadoBatalha.ESCOLHA_DO_JOGADOR;
    private int turno = 1;
    private final List<String> mensagens = new ArrayList<>();

    public Batalha(Personagem jogador, Personagem chefe) {
        if (jogador == null) {
            throw new IllegalArgumentException("O jogador não pode ser nulo.");
        }
        if (chefe == null) {
            throw new IllegalArgumentException("O chefe não pode ser nulo.");
        }

        this.jogador = jogador;
        this.chefe = chefe;
        registrar("O chefe " + chefe.getNome() + " desafia você!");
    }

    public Personagem getJogador() {
        return jogador;
    }

    public Personagem getChefe() {
        return chefe;
    }

    public EstadoBatalha getEstado() {
        return estado;
    }

    public int getTurno() {
        return turno;
    }

    private void exigirEstado(EstadoBatalha esperado) {
        if (estado != esperado) {
            throw new IllegalStateException("A ação não pode ser executada no estado " + estado + ".");
        }
    }

    public void acaoAtacar() {
        exigirEstado(EstadoBatalha.ESCOLHA_DO_JOGADOR);
        int dano = jogador.atacar(chefe);
        registrar(jogador.getNome() + " usou " + jogador.getNomeAtaqueBasico()
                + " e causou " + dano + " de dano!");
        aposAcaoDoJogador();
    }

    private void aposAcaoDoJogador() {
        if (!chefe.estaVivo()) {
            registrar(chefe.getNome() + " caiu em combate!");
            estado = EstadoBatalha.VITORIA;
        } else {
            estado = EstadoBatalha.TURNO_DO_CHEFE;
        }
    }

    private void registrar(String msg) {
        mensagens.add(msg);
    }

    public List<String> consumirMensagens() {
        List<String> pendentes = new ArrayList<>(mensagens);
        mensagens.clear();
        return pendentes;
    }
}
