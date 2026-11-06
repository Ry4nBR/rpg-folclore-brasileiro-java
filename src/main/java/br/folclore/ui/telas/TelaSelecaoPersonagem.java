package br.folclore.ui.telas;

import br.folclore.habilidades.Habilidade;
import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Tipo;
import br.folclore.ui.ConfiguracoesUi;
import br.folclore.ui.componentes.Botao;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class TelaSelecaoPersonagem implements Tela {
    private static final Color COR_FUNDO = new Color(20, 70, 45);
    private static final Color COR_CARTAO = new Color(36, 52, 48);
    private static final Color COR_VENTO = new Color(0, 210, 220);
    private static final Color COR_FISICO = new Color(240, 130, 45);
    private static final Color COR_SELECAO = new Color(255, 215, 0);
    private static final int LARGURA_CARTAO = 260;
    private static final int ALTURA_CARTAO = 320;
    private static final int[] POSICOES_X = {190, 510};
    private static final int POSICAO_Y = 100;

    private final Consumer<Personagem> aoConfirmar;
    private final Runnable aoVoltar;
    private final List<Personagem> opcoes = CatalogoDeHerois.criarTodos();
    private final Botao botaoConfirmar;
    private final Botao botaoVoltar;
    private int indiceSelecionado = -1;

    public TelaSelecaoPersonagem(Consumer<Personagem> aoConfirmar, Runnable aoVoltar) {
        this.aoConfirmar = Objects.requireNonNull(aoConfirmar, "A ação de confirmar não pode ser nula.");
        this.aoVoltar = Objects.requireNonNull(aoVoltar, "A ação de voltar não pode ser nula.");
        botaoConfirmar = new Botao(380, 450, 200, 50, "Confirmar", this::confirmarSelecao);
        botaoConfirmar.setHabilitado(false);
        botaoVoltar = new Botao(40, 470, 160, 40, "Voltar", aoVoltar);
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D graphics) {
        Graphics2D contexto = (Graphics2D) graphics.create();
        try {
            contexto.setColor(COR_FUNDO);
            contexto.fillRect(0, 0, ConfiguracoesUi.LARGURA, ConfiguracoesUi.ALTURA);
            contexto.setColor(Color.WHITE);
            contexto.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 32));
            desenharCentralizado(contexto, "Escolha seu personagem", 65);

            for (int indice = 0; indice < opcoes.size(); indice++) {
                desenharCartao(contexto, opcoes.get(indice), POSICOES_X[indice], indice);
            }
            botaoConfirmar.desenhar(contexto);
            botaoVoltar.desenhar(contexto);
        } finally {
            contexto.dispose();
        }
    }

    private void desenharCartao(Graphics2D graphics, Personagem personagem, int x, int indice) {
        graphics.setColor(COR_CARTAO);
        graphics.fillRoundRect(x, POSICAO_Y, LARGURA_CARTAO, ALTURA_CARTAO, 18, 18);
        graphics.setStroke(new BasicStroke(indice == indiceSelecionado ? 4 : 2));
        graphics.setColor(indice == indiceSelecionado ? COR_SELECAO : Color.WHITE);
        graphics.drawRoundRect(x, POSICAO_Y, LARGURA_CARTAO, ALTURA_CARTAO, 18, 18);

        int figuraX = x + (LARGURA_CARTAO - 120) / 2;
        int figuraY = POSICAO_Y + 20;
        graphics.setColor(corDaFigura(personagem.getTipo()));
        graphics.fillRect(figuraX, figuraY, 120, 120);

        graphics.setColor(Color.WHITE);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
        desenharCentralizado(graphics, personagem.getNome(), POSICAO_Y + 172, x, LARGURA_CARTAO);
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 16));
        desenharCentralizado(graphics, personagem.getTipo().getRotulo(), POSICAO_Y + 196, x, LARGURA_CARTAO);

        graphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        int textoX = x + 14;
        graphics.drawString("PV: " + personagem.getPvMaximo(), textoX, POSICAO_Y + 224);
        graphics.drawString("ATQ: " + personagem.getAtaque(), textoX, POSICAO_Y + 246);
        graphics.drawString("DEF: " + personagem.getDefesa(), textoX, POSICAO_Y + 268);

        Habilidade habilidade = personagem.getHabilidade();
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 13));
        graphics.drawString("Habilidade: " + habilidade.getNome(), textoX, POSICAO_Y + 293);
        graphics.drawString("(" + habilidade.getUsosMaximos() + " usos)", textoX, POSICAO_Y + 311);
    }

    private Color corDaFigura(Tipo tipo) {
        return switch (tipo) {
            case VENTO -> COR_VENTO;
            case FISICO -> COR_FISICO;
            case FOGO -> Color.RED;
        };
    }

    private void desenharCentralizado(Graphics2D graphics, String texto, int y) {
        desenharCentralizado(graphics, texto, y, 0, ConfiguracoesUi.LARGURA);
    }

    private void desenharCentralizado(Graphics2D graphics, String texto, int y, int x, int largura) {
        FontMetrics metricas = graphics.getFontMetrics();
        int textoX = x + (largura - metricas.stringWidth(texto)) / 2;
        graphics.drawString(texto, textoX, y);
    }

    @Override
    public void aoClicar(int x, int y) {
        for (int indice = 0; indice < opcoes.size(); indice++) {
            if (contemNoCartao(indice, x, y)) {
                selecionar(indice);
                break;
            }
        }
        botaoConfirmar.clicar(x, y);
        botaoVoltar.clicar(x, y);
    }

    @Override
    public void aoMoverMouse(int x, int y) {
        botaoConfirmar.atualizarHover(x, y);
        botaoVoltar.atualizarHover(x, y);
    }

    @Override
    public void aoPressionarTecla(int codigo) {
        if (codigo == KeyEvent.VK_LEFT) {
            selecionar(indiceSelecionado <= 0 ? opcoes.size() - 1 : indiceSelecionado - 1);
        } else if (codigo == KeyEvent.VK_RIGHT) {
            selecionar(indiceSelecionado < 0 || indiceSelecionado == opcoes.size() - 1
                    ? 0 : indiceSelecionado + 1);
        } else if (codigo == KeyEvent.VK_ENTER) {
            confirmarSelecao();
        } else if (codigo == KeyEvent.VK_ESCAPE) {
            aoVoltar.run();
        }
    }

    private boolean contemNoCartao(int indice, int x, int y) {
        return x >= POSICOES_X[indice] && x < POSICOES_X[indice] + LARGURA_CARTAO
                && y >= POSICAO_Y && y < POSICAO_Y + ALTURA_CARTAO;
    }

    private void selecionar(int indice) {
        indiceSelecionado = indice;
        botaoConfirmar.setHabilitado(true);
    }

    private void confirmarSelecao() {
        if (indiceSelecionado >= 0) {
            aoConfirmar.accept(opcoes.get(indiceSelecionado));
        }
    }
}
