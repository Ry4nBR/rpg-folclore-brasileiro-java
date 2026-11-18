package br.folclore.ia;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.HabilidadeCura;
import br.folclore.modelo.Personagem;

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

        return DecisaoIA.atacar();
    }
}
