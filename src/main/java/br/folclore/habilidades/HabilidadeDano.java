package br.folclore.habilidades;

import br.folclore.modelo.Personagem;

public class HabilidadeDano extends HabilidadeBase {
    private final int poderBase;

    public HabilidadeDano(String nome, int usosMaximos, int poderBase) {
        super(nome, usosMaximos);
        this.poderBase = poderBase;
    }

    @Override
    public ResultadoAcao executar(Personagem usuario, Personagem alvo) {
        if (!temUsosRestantes()) {
            return new ResultadoAcao(
                    ResultadoAcao.Efeito.FALHA,
                    0,
                    "Sem usos restantes de " + getNome() + "!"
            );
        }

        consumirUso();
        int dano = usuario.calcularDano(alvo, poderBase);
        int efetivo = alvo.receberDano(dano);
        String mensagem = usuario.getNome() + " usou " + getNome() + "! "
                + alvo.getNome() + " sofreu " + efetivo + " de dano.";
        return new ResultadoAcao(ResultadoAcao.Efeito.DANO, efetivo, mensagem);
    }
}
