package br.folclore.habilidades;

public final class ResultadoAcao {
    public enum Efeito {
        DANO,
        CURA,
        FALHA
    }

    private final Efeito efeito;
    private final int valor;
    private final String mensagem;

    public ResultadoAcao(Efeito efeito, int valor, String mensagem) {
        this.efeito = efeito;
        this.valor = valor;
        this.mensagem = mensagem;
    }
}
