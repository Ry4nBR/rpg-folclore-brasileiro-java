package br.folclore.demo;

import br.folclore.modelo.Lobisomem;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Saci;
import java.util.List;

public final class DemoPilares {
    private DemoPilares() {
    }

    public static void main(String[] args) {
        List<Personagem> personagens = List.of(
                new Saci(),
                new Lobisomem(),
                new MulaSemCabeca()
        );

        for (Personagem personagem : personagens) {
            Lobisomem alvo = new Lobisomem();
            System.out.println(personagem.resumoDeStatus());
            System.out.println("Ataque básico: " + personagem.getNomeAtaqueBasico());
            System.out.println("Dano causado: " + personagem.atacar(alvo));
            System.out.println(personagem.usarHabilidade(alvo).getMensagem());
        }
    }
}
