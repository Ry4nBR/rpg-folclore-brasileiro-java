package br.folclore.batalha;

import br.folclore.habilidades.ResultadoAcao;
import br.folclore.modelo.Personagem;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Gerencia uma batalha por turnos.
 * A ordem dos turnos é fixa: o jogador age primeiro, depois o inimigo.
 * A velocidade dos combatentes não altera essa ordem.
 */
public class Batalha {
    private final Personagem jogador;
    private final Personagem inimigo;
    private EstadoBatalha estado = EstadoBatalha.INICIO;
    private final boolean fugaPermitida;
    private final List<String> registro = new ArrayList<>();
    private int turno = 1;

    public Batalha(Personagem jogador, Personagem inimigo) {
        if (jogador == null || inimigo == null) {
            throw new IllegalArgumentException("Os combatentes não podem ser nulos.");
        }

        this.jogador = jogador;
        this.inimigo = inimigo;
        this.fugaPermitida = !inimigo.ehChefe();
    }

    public void iniciar() {
        if (inimigo.ehChefe()) {
            registrar("O chefe " + inimigo.getNome() + " desafia você!");
        } else {
            registrar(inimigo.getNome() + " apareceu!");
        }
        estado = EstadoBatalha.ESCOLHA_DO_JOGADOR;
    }

    private void exigirEstado(EstadoBatalha esperado) {
        if (estado != esperado) {
            throw new IllegalStateException("A ação exige o estado " + esperado
                    + ", mas o estado atual é " + estado + ".");
        }
    }

    public void acaoAtacar() {
        exigirEstado(EstadoBatalha.ESCOLHA_DO_JOGADOR);
        int dano = jogador.atacar(inimigo);
        registrar(jogador.getNome() + " usou " + jogador.getNomeAtaqueBasico()
                + " e causou " + dano + " de dano!");
        aposAcaoDoJogador();
    }

    public void acaoHabilidade(int indice) {
        exigirEstado(EstadoBatalha.ESCOLHA_DO_JOGADOR);
        if (indice < 0 || indice >= jogador.getHabilidades().size()) {
            throw new IllegalArgumentException("Índice de habilidade inválido.");
        }
        ResultadoAcao resultado = jogador.usarHabilidade(indice, inimigo);
        registrar(resultado.getMensagem());
        if (resultado.getEfeito() == ResultadoAcao.Efeito.FALHA) {
            return;
        }
    }

    private void aposAcaoDoJogador() {
        if (!inimigo.estaVivo()) {
            registrar(inimigo.getNome() + " foi derrotado!");
            estado = EstadoBatalha.VITORIA;
        } else {
            estado = EstadoBatalha.TURNO_DO_INIMIGO;
        }
    }

    public void executarTurnoDoInimigo() {
        exigirEstado(EstadoBatalha.TURNO_DO_INIMIGO);
        int dano = inimigo.atacar(jogador);
        registrar(inimigo.getNome() + " usou " + inimigo.getNomeAtaqueBasico()
                + " e causou " + dano + " de dano!");
        if (!jogador.estaVivo()) {
            registrar(jogador.getNome() + " foi derrotado!");
            estado = EstadoBatalha.DERROTA;
        } else {
            turno++;
            estado = EstadoBatalha.ESCOLHA_DO_JOGADOR;
        }
    }

    public Personagem getJogador() {
        return jogador;
    }

    public Personagem getInimigo() {
        return inimigo;
    }

    public EstadoBatalha getEstado() {
        return estado;
    }

    public boolean isFugaPermitida() {
        return fugaPermitida;
    }

    private void registrar(String mensagem) {
        registro.add(mensagem);
    }

    public List<String> getRegistro() {
        return Collections.unmodifiableList(new ArrayList<>(registro));
    }

    public int getTurno() {
        return turno;
    }
}
