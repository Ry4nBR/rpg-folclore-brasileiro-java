package br.folclore.ui.componentes;

public final class LinhaDeDialogo {
    private final String idRetrato;
    private final String falante;
    private final String texto;

    public LinhaDeDialogo(String idRetrato, String falante, String texto) {
        if (falante == null) {
            throw new IllegalArgumentException("O falante não pode ser nulo.");
        }
        if (texto == null) {
            throw new IllegalArgumentException("O texto do diálogo não pode ser nulo.");
        }

        this.idRetrato = idRetrato;
        this.falante = falante;
        this.texto = texto;
    }

    public String getIdRetrato() {
        return idRetrato;
    }

    public String getFalante() {
        return falante;
    }

    public String getTexto() {
        return texto;
    }
}
