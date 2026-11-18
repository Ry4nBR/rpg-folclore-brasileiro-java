package br.folclore.ui.telas;

import br.folclore.modelo.CatalogoDeHerois;
import br.folclore.modelo.Personagem;
import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Graphics2D;
import java.util.List;
import java.util.function.Consumer;

public class TelaSelecaoPersonagem implements Tela {

    private final GerenciadorDeTelas gerenciador;
    private final Consumer<Personagem> aoConfirmar;
    private final List<Personagem> opcoes = CatalogoDeHerois.criarTodos();

    public TelaSelecaoPersonagem(GerenciadorDeTelas gerenciador, Consumer<Personagem> aoConfirmar) {
        this.gerenciador = gerenciador;
        this.aoConfirmar = aoConfirmar;
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
