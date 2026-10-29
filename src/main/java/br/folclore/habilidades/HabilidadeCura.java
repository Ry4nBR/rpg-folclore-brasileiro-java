package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public class HabilidadeCura extends HabilidadeBase {
    private final int percentual;

    public HabilidadeCura(String nome, int usosMaximos, int percentual) {
        super(nome, usosMaximos);
        this.percentual = percentual;
    }

    @Override
    public ResultadoAcao executar(Personagem usuario, Personagem alvo) {
        if (!temUsosRestantes()) {
            return new ResultadoAcao(
                    ResultadoAcao.Efeito.FALHA,
                    0,
                    "Sem usos restantes de " + getNome() + "!");
        }

        consumirUso();
        int curado = usuario.curarPercentualDaVidaMaxima(percentual);

        return new ResultadoAcao(
                ResultadoAcao.Efeito.CURA,
                curado,
                usuario.getNome() + " usou " + getNome() + " e recuperou " + curado + " de PV!");
    }
}
