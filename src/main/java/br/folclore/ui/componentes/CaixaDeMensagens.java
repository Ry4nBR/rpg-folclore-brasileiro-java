package br.folclore.ui.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CaixaDeMensagens {
    private static final int RAIO_CANTO = 16;
    private static final int MARGEM = 12;

    private final int x;
    private final int y;
    private final int largura;
    private final int altura;
    private String texto = "";

    public CaixaDeMensagens(int x, int y, int largura, int altura) {
        if (largura <= 0 || altura <= 0) {
            throw new IllegalArgumentException("A largura e a altura devem ser positivas.");
        }
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }

    public void definirTexto(String texto) {
        this.texto = Objects.requireNonNull(texto, "O texto não pode ser nulo.");
    }

    public void desenhar(Graphics2D graphics) {
        Graphics2D contexto = (Graphics2D) graphics.create();
        try {
            contexto.setColor(Color.WHITE);
            contexto.fillRoundRect(x, y, largura, altura, RAIO_CANTO, RAIO_CANTO);

            contexto.setColor(Color.BLACK);
            contexto.setStroke(new BasicStroke(2));
            contexto.drawRoundRect(x, y, largura - 1, altura - 1, RAIO_CANTO, RAIO_CANTO);
            if (largura > 9 && altura > 9) {
                contexto.drawRoundRect(x + 4, y + 4, largura - 9, altura - 9,
                        RAIO_CANTO - 4, RAIO_CANTO - 4);
            }

            contexto.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
            FontMetrics metricas = contexto.getFontMetrics();
            int larguraTexto = Math.max(1, largura - MARGEM * 2);
            List<String> linhas = quebrarLinhas(texto, metricas, larguraTexto);
            int limiteInferior = y + altura - MARGEM;
            int linhaY = y + MARGEM + metricas.getAscent();
            contexto.clipRect(x + MARGEM, y + MARGEM, larguraTexto, Math.max(0, altura - MARGEM * 2));
            for (String linha : linhas) {
                if (linhaY <= limiteInferior) {
                    contexto.drawString(linha, x + MARGEM, linhaY);
                    linhaY += metricas.getHeight();
                }
            }
        } finally {
            contexto.dispose();
        }
    }

    private List<String> quebrarLinhas(String conteudo, FontMetrics metricas, int larguraMaxima) {
        List<String> linhas = new ArrayList<>();
        for (String paragrafo : conteudo.split("\\R", -1)) {
            StringBuilder linha = new StringBuilder();
            for (String palavra : paragrafo.split("\\s+")) {
                if (palavra.isEmpty()) {
                    continue;
                }
                if (linha.isEmpty()) {
                    adicionarPalavra(linhas, linha, palavra, metricas, larguraMaxima);
                    continue;
                }

                String candidata = linha + " " + palavra;
                if (metricas.stringWidth(candidata) <= larguraMaxima) {
                    linha.append(' ').append(palavra);
                } else {
                    linhas.add(linha.toString());
                    linha.setLength(0);
                    adicionarPalavra(linhas, linha, palavra, metricas, larguraMaxima);
                }
            }
            linhas.add(linha.toString());
        }
        return linhas;
    }

    private void adicionarPalavra(List<String> linhas, StringBuilder linha, String palavra,
                                  FontMetrics metricas, int larguraMaxima) {
        StringBuilder trecho = new StringBuilder();
        for (int indice = 0; indice < palavra.length();) {
            int pontoCodigo = palavra.codePointAt(indice);
            String caractere = new String(Character.toChars(pontoCodigo));
            if (!trecho.isEmpty() && metricas.stringWidth(trecho + caractere) > larguraMaxima) {
                linhas.add(trecho.toString());
                trecho.setLength(0);
            }
            trecho.append(caractere);
            indice += Character.charCount(pontoCodigo);
        }
        linha.append(trecho);
    }
}
