package br.folclore.save;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class GerenciadorDeSave {
    public static final String PASTA = "saves";
    public static final String ARQUIVO = "saves/save1.properties";
    public static final int VERSAO = 1;

    private GerenciadorDeSave() {
    }

    public static boolean salvar(Map<String, String> dados) throws IOException {
        Objects.requireNonNull(dados, "O estado a salvar não pode ser nulo.");
        Files.createDirectories(Path.of(PASTA));

        Properties propriedades = new Properties();
        dados.forEach(propriedades::setProperty);
        try (Writer escritor = new OutputStreamWriter(
                Files.newOutputStream(Path.of(ARQUIVO)), StandardCharsets.UTF_8)) {
            propriedades.store(escritor, "RPG do Folclore Brasileiro");
        }
        return true;
    }
}
