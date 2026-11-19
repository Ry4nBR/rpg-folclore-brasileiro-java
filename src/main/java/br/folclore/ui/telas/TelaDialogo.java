package br.folclore.ui.telas;

import br.folclore.ui.GerenciadorDeTelas;
import br.folclore.ui.componentes.LinhaDeDialogo;
import java.awt.Graphics2D;
import java.util.List;

public class TelaDialogo implements Tela {
    private final GerenciadorDeTelas gerenciador;
    private final Tela telaDeFundo;
    private final List<LinhaDeDialogo> linhas;
    private final Runnable aoTerminar;
    private int indiceLinhaAtual;

    public TelaDialogo(GerenciadorDeTelas gerenciador, Tela telaDeFundo,
                       List<LinhaDeDialogo> linhas, Runnable aoTerminar) {
        if (gerenciador == null) {
            throw new IllegalArgumentException("O gerenciador de telas não pode ser nulo.");
        }
        if (linhas == null || linhas.isEmpty()) {
            throw new IllegalArgumentException("O diálogo deve conter ao menos uma linha.");
        }
        if (aoTerminar == null) {
            throw new IllegalArgumentException("A ação de término não pode ser nula.");
        }

        this.gerenciador = gerenciador;
        this.telaDeFundo = telaDeFundo;
        this.linhas = List.copyOf(linhas);
        this.aoTerminar = aoTerminar;
    }

    @Override
    public void atualizar() {
    }

    @Override
    public void desenhar(Graphics2D g) {
    }

    @Override
    public void aoClicar(int x, int y) {
    }

    @Override
    public void aoMoverMouse(int x, int y) {
    }

    @Override
    public void aoPressionarTecla(int codigo) {
    }
}
