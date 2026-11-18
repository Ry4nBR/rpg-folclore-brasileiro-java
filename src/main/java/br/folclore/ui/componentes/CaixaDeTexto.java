package br.folclore.ui.componentes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class CaixaDeTexto {
    private static final int RAIO_CANTO = 18;
    private static final int RECUO_BORDA_INTERNA = 4;
    private static final int MARGEM_TEXTO = 12;
    private static final int RECUO_VERTICAL_TEXTO = 22;
    private static final int TAMANHO_RETRATO = 96;
    private static final int MARGEM_RETRATO = 4;
    private static final int FRAMES_POR_CARACTERE = 2;
    private static final Font FONTE_TEXTO = new Font(Font.MONOSPACED, Font.PLAIN, 16);
    private static final FontMetrics METRICAS_TEXTO = criarMetricasTexto();

    private final int x;
    private final int y;
    private final int largura;
    private final int altura;
    private String textoOriginal = "";
    private List<String> linhas = Collections.emptyList();
    private BufferedImage retrato;
    private int totalCaracteres;
    private int caracteresRevelados;
    private int framesDesdeUltimoCaractere;

    public CaixaDeTexto(int x, int y, int largura, int altura) {
        this.x = x;
        this.y = y;
        this.largura = largura;
        this.altura = altura;
    }

    public void definirTexto(String texto) {
        if (texto == null) {
            throw new IllegalArgumentException("O texto não pode ser nulo.");
        }
        textoOriginal = texto;
        reorganizarTexto();
        caracteresRevelados = 0;
        framesDesdeUltimoCaractere = 0;
    }

    public void definirRetrato(BufferedImage retrato) {
        this.retrato = retrato;
        reorganizarTexto();
        caracteresRevelados = Math.min(caracteresRevelados, totalCaracteres);
    }

    public void atualizar() {
        if (estaCompleta()) {
            return;
        }
        framesDesdeUltimoCaractere++;
        if (framesDesdeUltimoCaractere == FRAMES_POR_CARACTERE) {
            caracteresRevelados++;
            framesDesdeUltimoCaractere = 0;
        }
    }

    public boolean estaCompleta() {
        return caracteresRevelados >= totalCaracteres;
    }

    public void completar() {
        caracteresRevelados = totalCaracteres;
        framesDesdeUltimoCaractere = 0;
    }

    public void desenhar(Graphics2D g) {
        Graphics2D contexto = (Graphics2D) g.create();
        try {
            contexto.setColor(Color.WHITE);
            contexto.fillRoundRect(x, y, largura, altura, RAIO_CANTO, RAIO_CANTO);

            contexto.setColor(Color.BLACK);
            contexto.setStroke(new BasicStroke(2f));
            contexto.drawRoundRect(x, y, largura - 1, altura - 1,
                    RAIO_CANTO, RAIO_CANTO);
            contexto.drawRoundRect(x + RECUO_BORDA_INTERNA, y + RECUO_BORDA_INTERNA,
                    largura - RECUO_BORDA_INTERNA * 2 - 1,
                    altura - RECUO_BORDA_INTERNA * 2 - 1,
                    RAIO_CANTO - RECUO_BORDA_INTERNA,
                    RAIO_CANTO - RECUO_BORDA_INTERNA);

            desenharRetrato(contexto);
            contexto.setFont(FONTE_TEXTO);
            contexto.setColor(Color.BLACK);
            int linhaY = y + RECUO_VERTICAL_TEXTO;
            int limiteInferior = y + altura - MARGEM_TEXTO;
            int caracteresRestantes = caracteresRevelados;
            for (String linha : linhas) {
                if (linhaY > limiteInferior) {
                    break;
                }
                int caracteresNaLinha = linha.codePointCount(0, linha.length());
                int caracteresNaLinhaVisiveis = Math.min(caracteresNaLinha, caracteresRestantes);
                if (caracteresNaLinhaVisiveis > 0) {
                    int fimVisivel = linha.offsetByCodePoints(0, caracteresNaLinhaVisiveis);
                    contexto.drawString(linha.substring(0, fimVisivel), posicaoTextoX(), linhaY);
                }
                caracteresRestantes -= caracteresNaLinhaVisiveis;
                linhaY += METRICAS_TEXTO.getHeight();
            }
        } finally {
            contexto.dispose();
        }
    }

    private void reorganizarTexto() {
        linhas = quebrarLinhas(textoOriginal, METRICAS_TEXTO, larguraDisponivelTexto());
        totalCaracteres = linhas.stream()
                .mapToInt(linha -> linha.codePointCount(0, linha.length())).sum();
    }

    private void desenharRetrato(Graphics2D contexto) {
        if (retrato == null) {
            return;
        }

        int retratoX = x + MARGEM_TEXTO;
        int retratoY = y + (altura - TAMANHO_RETRATO) / 2;
        contexto.setColor(Color.BLACK);
        contexto.fillRect(retratoX, retratoY, TAMANHO_RETRATO, TAMANHO_RETRATO);
        contexto.setColor(Color.WHITE);
        contexto.fillRect(retratoX + MARGEM_RETRATO, retratoY + MARGEM_RETRATO,
                TAMANHO_RETRATO - MARGEM_RETRATO * 2,
                TAMANHO_RETRATO - MARGEM_RETRATO * 2);
        contexto.drawImage(retrato, retratoX + MARGEM_RETRATO, retratoY + MARGEM_RETRATO,
                TAMANHO_RETRATO - MARGEM_RETRATO * 2,
                TAMANHO_RETRATO - MARGEM_RETRATO * 2, null);
    }

    private int posicaoTextoX() {
        return x + MARGEM_TEXTO
                + (retrato == null ? 0 : TAMANHO_RETRATO + MARGEM_TEXTO);
    }

    private int larguraDisponivelTexto() {
        return Math.max(1, x + largura - MARGEM_TEXTO - posicaoTextoX());
    }

    private List<String> quebrarLinhas(String texto, FontMetrics metricas, int larguraDisponivel) {
        List<String> resultado = new ArrayList<>();

        for (String paragrafo : texto.split("\\R", -1)) {
            if (paragrafo.isBlank()) {
                resultado.add("");
                continue;
            }

            StringBuilder linha = new StringBuilder();
            for (String palavra : paragrafo.trim().split("\\s+")) {
                String candidata = linha.length() == 0 ? palavra : linha + " " + palavra;
                if (metricas.stringWidth(candidata) <= larguraDisponivel) {
                    linha.setLength(0);
                    linha.append(candidata);
                    continue;
                }

                if (linha.length() > 0) {
                    resultado.add(linha.toString());
                    linha.setLength(0);
                }
                if (metricas.stringWidth(palavra) <= larguraDisponivel) {
                    linha.append(palavra);
                } else {
                    quebrarPalavra(palavra, larguraDisponivel, metricas, resultado, linha);
                }
            }

            if (linha.length() > 0) {
                resultado.add(linha.toString());
            }
        }
        return resultado;
    }

    private void quebrarPalavra(String palavra, int larguraDisponivel, FontMetrics metricas,
                                List<String> resultado, StringBuilder linha) {
        for (int i = 0; i < palavra.length();) {
            int caractere = palavra.codePointAt(i);
            i += Character.charCount(caractere);
            if (linha.length() > 0
                    && metricas.stringWidth(linha.toString() + new String(Character.toChars(caractere)))
                            > larguraDisponivel) {
                resultado.add(linha.toString());
                linha.setLength(0);
            }
            linha.appendCodePoint(caractere);
        }
    }

    private static FontMetrics criarMetricasTexto() {
        BufferedImage imagem = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        Graphics2D contexto = imagem.createGraphics();
        try {
            return contexto.getFontMetrics(FONTE_TEXTO);
        } finally {
            contexto.dispose();
        }
    }
}
