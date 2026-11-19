package br.folclore.save;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;

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

    public static Optional<Map<String, String>> carregar() throws IOException {
        Path caminho = Path.of(ARQUIVO);
        if (!Files.exists(caminho)) {
            return Optional.empty();
        }

        Properties propriedades = new Properties();
        try (Reader leitor = new InputStreamReader(Files.newInputStream(caminho), StandardCharsets.UTF_8)) {
            propriedades.load(leitor);
        }

        Map<String, String> dados = new LinkedHashMap<>();
        Set<String> nomes = propriedades.stringPropertyNames();
        for (String nome : nomes) {
            dados.put(nome, propriedades.getProperty(nome));
        }
        return Optional.of(dados);
    }

    public static boolean existeSave() {
        return Files.exists(Path.of(ARQUIVO));
    }
}
