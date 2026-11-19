package br.folclore.progressao;

import br.folclore.modelo.Batatao;
import br.folclore.modelo.Caipora;
import br.folclore.modelo.Capelobo;
import br.folclore.modelo.Curupira;
import br.folclore.modelo.MaeDeOuro;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Tipo;
import java.util.List;

public final class CatalogoDeGinasios {
    private CatalogoDeGinasios() {
    }

    public static Ginasio criarGinasioDeFogo() {
        return new Ginasio("Ginásio do Fogo", Tipo.FOGO, List.<Personagem>of(
                new MaeDeOuro(),
                new Batatao(),
                new MulaSemCabeca()));
    }

    public static Ginasio criarGinasioDePlanta() {
        return new Ginasio("Ginásio da Planta", Tipo.PLANTA, List.<Personagem>of(
                new Curupira(),
                new Caipora(),
                new Capelobo()));
    }
}
