package br.folclore.ia;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.HabilidadeCura;
import br.folclore.habilidades.HabilidadeDano;
import br.folclore.modelo.Personagem;

/**
 * Decide as ações do inimigo nesta ordem de prioridade:
 * <ol>
 * <li>Usa uma habilidade de cura disponível se estiver com até 30% dos PV máximos.</li>
 * <li>Em turnos ímpares, usa uma habilidade de dano disponível.</li>
 * <li>Nos demais casos, executa o ataque básico.</li>
 * </ol>
 */
public class IaPadrao implements InteligenciaArtificial {
    @Override
    public DecisaoIA decidir(Personagem inimigo, Personagem jogador, int turno) {
        if ((long) inimigo.getPvAtual() * 100 <= (long) inimigo.getPvMaximo() * 30) {
            for (int indice = 0; indice < inimigo.getHabilidades().size(); indice++) {
                Habilidade habilidade = inimigo.getHabilidades().get(indice);
                if (habilidade instanceof HabilidadeCura && habilidade.temUsosRestantes()) {
                    return DecisaoIA.habilidade(indice);
                }
            }
        }

        if (turno % 2 != 0) {
            for (int indice = 0; indice < inimigo.getHabilidades().size(); indice++) {
                Habilidade habilidade = inimigo.getHabilidades().get(indice);
                if (habilidade instanceof HabilidadeDano && habilidade.temUsosRestantes()) {
                    return DecisaoIA.habilidade(indice);
                }
            }
        }

        return DecisaoIA.atacar();
    }
}
