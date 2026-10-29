package br.folclore.modelo;

public class NegrinhoDoPastoreio extends Personagem {

    public NegrinhoDoPastoreio() {
        super("Negrinho do Pastoreio", Tipo.PSIQUICO, 45, 10, 4, 9);
    }

    @Override
    public int atacar(Personagem alvo) {
        return 0;
    }

    @Override
    public String getNomeAtaqueBasico() {
        return "";
    }
}
