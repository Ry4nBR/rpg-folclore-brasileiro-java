package br.folclore.modelo;

public enum Tipo {
    VENTO("Vento"),
    FISICO("Físico"),
    PSIQUICO("Psíquico"),
    FOGO("Fogo"),
    PLANTA("Planta"),
    AGUA("Água");

    private final String rotulo;

    private Tipo(String rotulo) {
        this.rotulo = rotulo;
    }

    public String getRotulo() {
        return rotulo;
    }
}
