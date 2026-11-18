package br.folclore.batalha;

import br.folclore.habilidades.ResultadoAcao;
import br.folclore.ia.DecisaoIA;
import br.folclore.ia.IaPadrao;
import br.folclore.ia.InteligenciaArtificial;
import br.folclore.ia.TipoDeDecisao;
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
    private final InteligenciaArtificial ia;
    private EstadoBatalha estado = EstadoBatalha.INICIO;
    private final boolean fugaPermitida;
    private final List<String> registro = new ArrayList<>();
    private int turno = 1;

    public Batalha(Personagem jogador, Personagem inimigo) {
        this(jogador, inimigo, new IaPadrao());
    }

    public Batalha(Personagem jogador, Personagem inimigo, InteligenciaArtificial ia) {
        if (jogador == null || inimigo == null) {
            throw new IllegalArgumentException("Os combatentes não podem ser nulos.");
        }
        if (ia == null) {
            throw new IllegalArgumentException("A inteligência artificial não pode ser nula.");
        }

        this.jogador = jogador;
        this.inimigo = inimigo;
        this.ia = ia;
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
        aposAcaoDoJogador();
    }

    public String acaoVerStatus() {
        exigirEstado(EstadoBatalha.ESCOLHA_DO_JOGADOR);
        return inimigo.resumoDeStatus();
    }

    public boolean acaoFugir() {
        if (!fugaPermitida) {
            registrar("Não é possível fugir de um chefe!");
            return false;
        }
        registrar("Você fugiu da batalha!");
        estado = EstadoBatalha.FUGA;
        return true;
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
        DecisaoIA decisao = ia.decidir(inimigo, jogador, turno);
        if (decisao.getTipo() == TipoDeDecisao.ATACAR) {
            int dano = inimigo.atacar(jogador);
            registrar(inimigo.getNome() + " usou " + inimigo.getNomeAtaqueBasico()
                    + " e causou " + dano + " de dano!");
        } else {
            ResultadoAcao resultado = inimigo.usarHabilidade(decisao.getIndiceHabilidade(), jogador);
            registrar(resultado.getMensagem());
        }

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

    public ResultadoBatalha getResultado() {
        return switch (estado) {
            case VITORIA -> ResultadoBatalha.VITORIA;
            case DERROTA -> ResultadoBatalha.DERROTA;
            case FUGA -> ResultadoBatalha.FUGA;
            default -> null;
        };
    }

    public boolean estaEncerrada() {
        return estado == EstadoBatalha.VITORIA
                || estado == EstadoBatalha.DERROTA
                || estado == EstadoBatalha.FUGA;
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
