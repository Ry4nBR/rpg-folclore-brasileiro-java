package br.folclore.ui.componentes;

import java.text.Normalizer;
import java.util.Locale;

public final class NormalizadorDeNomes {

    private NormalizadorDeNomes() {
    }

    public static String paraId(String nome) {
        String semAcentos = Normalizer.normalize(nome, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");
        return semAcentos.toLowerCase(Locale.ROOT).replaceAll("(?U)[\\s-]+", "_");
    }
}
