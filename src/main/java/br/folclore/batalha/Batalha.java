package br.folclore.batalha;

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
