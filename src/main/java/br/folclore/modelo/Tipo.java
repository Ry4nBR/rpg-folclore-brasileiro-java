package br.folclore.modelo;

public enum Tipo {
    VENTO("Vento"),
    FISICO("Físico"),
    FOGO("Fogo");

    private final String rotulo;

    Tipo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
