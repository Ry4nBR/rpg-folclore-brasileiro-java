package br.folclore.ui.telas;

import br.folclore.batalha.Batalha;
import br.folclore.batalha.EstadoBatalha;
import br.folclore.modelo.MaeDeOuro;
import br.folclore.modelo.MulaSemCabeca;
import br.folclore.modelo.Personagem;
import br.folclore.modelo.Saci;
import br.folclore.ui.GerenciadorDeTelas;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public final class TelaBatalhaVerificacao {
    public static void main(String[] args) throws Exception {
        Saci saci = new Saci();
        Batalha batalha = new Batalha(saci, new MaeDeOuro());
        TelaBatalha tela = criar(batalha);
        salvar(tela, "menu-batalha");
        tela.aoClicar(600, 430);
        conferir(batalha.getInimigo().getPvAtual() == batalha.getInimigo().getPvMaximo(),
                "Abrir Ataque nao causa dano");
        salvar(tela, "menu-quatro-golpes");
        tela.aoClicar(600, 525);
        tela.aoClicar(600, 485);
        salvar(tela, "status-combatentes");
        tela.aoClicar(600, 430);
        conferir(batalha.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR, "Status bloqueia cliques no fundo");
        tela.aoPressionarTecla(KeyEvent.VK_ESCAPE);
        tela.aoClicar(840, 430);
        salvar(tela, "mochila-batalha");
        tela.aoPressionarTecla(KeyEvent.VK_A);
        conferir(batalha.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR, "Mochila nao consome turno");
        tela.aoPressionarTecla(KeyEvent.VK_ESCAPE);
        tela.aoPressionarTecla(KeyEvent.VK_A);
        tela.aoPressionarTecla(KeyEvent.VK_ESCAPE);
        tela.aoPressionarTecla(KeyEvent.VK_F);
        conferir(batalha.getEstado() == EstadoBatalha.FUGA, "Fugir encerra luta comum");

        for (int slot = 0; slot < 4; slot++) {
            Saci jogador = new Saci();
            jogador.receberDano(10);
            Batalha combate = new Batalha(jogador, new MaeDeOuro());
            TelaBatalha interfaceBatalha = criar(combate);
            interfaceBatalha.aoClicar(600, 430);
            int usosAntes = slot == 0 ? 0 : jogador.getHabilidades().get(slot - 1).getUsosRestantes();
            interfaceBatalha.aoClicar(slot % 2 == 0 ? 600 : 840, slot < 2 ? 430 : 485);
            conferir(combate.getEstado() == EstadoBatalha.TURNO_DO_INIMIGO, "Slot " + slot + " executa acao");
            if (slot > 0) {
                conferir(jogador.getHabilidades().get(slot - 1).getUsosRestantes() == usosAntes - 1,
                        "Botao corresponde ao poder correto");
            }
        }

        Batalha chefe = new Batalha(new Saci(), new MulaSemCabeca());
        TelaBatalha telaChefe = criar(chefe);
        telaChefe.aoClicar(840, 485);
        telaChefe.aoPressionarTecla(KeyEvent.VK_F);
        conferir(chefe.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR, "Chefe impede fuga");

        Saci esgotado = new Saci();
        Personagem alvo = new MaeDeOuro();
        while (esgotado.getHabilidades().get(0).temUsosRestantes()) {
            alvo.restaurarVidaTotal();
            esgotado.usarHabilidade(0, alvo);
        }
        alvo.restaurarVidaTotal();
        Batalha semUsos = new Batalha(esgotado, alvo);
        TelaBatalha telaSemUsos = criar(semUsos);
        telaSemUsos.aoClicar(600, 430);
        telaSemUsos.aoClicar(840, 430);
        telaSemUsos.aoPressionarTecla(KeyEvent.VK_2);
        conferir(semUsos.getEstado() == EstadoBatalha.ESCOLHA_DO_JOGADOR, "Golpe sem usos desabilitado");
        telaSemUsos.aoClicar(600, 430);
        conferir(semUsos.getEstado() == EstadoBatalha.TURNO_DO_INIMIGO, "Ataque basico continua disponivel");
        System.out.println("OK: menu, quatro slots, mochila, status, voltar, fuga e poderes esgotados.");
    }

    private static TelaBatalha criar(Batalha batalha) {
        TelaBatalha tela = new TelaBatalha(new GerenciadorDeTelas(), batalha, resultado -> {});
        tela.aoPressionarTecla(KeyEvent.VK_ENTER);
        tela.aoPressionarTecla(KeyEvent.VK_ENTER);
        return tela;
    }

    private static void salvar(TelaBatalha tela, String nome) throws Exception {
        BufferedImage imagem = new BufferedImage(960, 540, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = imagem.createGraphics();
        try {
            tela.desenhar(g);
        } finally {
            g.dispose();
        }
        ImageIO.write(imagem, "png", new File("target/" + nome + ".png"));
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
