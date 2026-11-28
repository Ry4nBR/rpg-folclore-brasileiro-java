package br.folclore.modelo;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.HabilidadeCura;
import br.folclore.progressao.CatalogoDePoderes;
import java.util.List;
import java.util.Map;

public final class GolpesVerificacao {
    public static void main(String[] args) {
        List<Personagem> personagens = List.of(new Saci(), new Lobisomem(), new NegrinhoDoPastoreio(),
                new MaeDeOuro(), new Batatao(), new MulaSemCabeca(), new Curupira(), new Caipora(),
                new Capelobo(), new Iara(), new BotoCorDeRosa(), new Honorato());
        for (Personagem personagem : personagens) {
            for (Habilidade habilidade : personagem.getHabilidades()) {
                conferir(!habilidade.getNome().contains("?"), "Nome do golpe preserva os acentos");
            }
            conferir(personagem.getHabilidades().size() == 3 && personagem.getSlotsDeHabilidade() == 4,
                    "Quatro golpes de " + personagem.getNome());
            Personagem alvo = new Honorato();
            int pvAntes = alvo.getPvAtual();
            int previsto = personagem.preverDanoAtaqueBasico(alvo);
            conferir(alvo.getPvAtual() == pvAntes, "Previsao do ataque nao altera PV");
            conferir(personagem.atacar(alvo) == previsto, "Dano do ataque corresponde a previsao");
            personagem.receberDano(10);
            for (Habilidade habilidade : personagem.getHabilidades()) {
                alvo.restaurarVidaTotal();
                int usos = habilidade.getUsosRestantes();
                int pvPersonagem = personagem.getPvAtual();
                int dano = habilidade.preverDano(personagem, alvo);
                String descricao = habilidade.descreverEfeito(personagem, alvo);
                conferir(usos == habilidade.getUsosRestantes() && pvPersonagem == personagem.getPvAtual()
                        && alvo.getPvAtual() == alvo.getPvMaximo(), "Previsao nao altera estado");
                var resultado = habilidade.executar(personagem, alvo);
                conferir(habilidade.getUsosRestantes() == usos - 1, "Consumo de usos");
                if (habilidade instanceof HabilidadeCura) {
                    conferir(descricao.equals("Cura: " + (personagem.getPvAtual() - pvPersonagem) + " PV"),
                            "Previsao de cura correta");
                } else {
                    conferir(resultado.getValor() == dano, "Dano real corresponde a previsao");
                }
                conferir(CatalogoDePoderes.criarPorNome(habilidade.getNome()).getNome().equals(habilidade.getNome()),
                        "Poder registrado para restaurar saves");
            }
        }

        Saci original = new Saci();
        original.ganharExperiencia(40);
        Map<String, String> legado = original.exportarEstado();
        legado.put("slots", "2");
        legado.put("equipadas", "Ventos Revigorantes");
        legado.put("liberados", "Ventos Revigorantes;Uivo Brutal");
        Saci restaurado = new Saci();
        restaurado.restaurarEstado(legado);
        conferir(restaurado.getHabilidades().size() == 3 && restaurado.getSlotsDeHabilidade() == 4,
                "Save antigo recebe os golpes faltantes");
        conferir(restaurado.getXp() == 40 && restaurado.getPoderesLiberados().stream()
                .anyMatch(p -> p.getNome().equals("Uivo Brutal")), "Progresso antigo preservado");
        Map<String, String> salvo = restaurado.exportarEstado();
        Saci recarregado = new Saci();
        recarregado.restaurarEstado(salvo);
        conferir(salvo.equals(recarregado.exportarEstado()), "Save novo tem restauracao estavel");
        System.out.println("OK: quatro golpes em 12 personagens, previsoes, usos e saves antigos/novos.");
    }

    private static void conferir(boolean condicao, String mensagem) {
        if (!condicao) {
            throw new AssertionError(mensagem);
        }
    }
}
