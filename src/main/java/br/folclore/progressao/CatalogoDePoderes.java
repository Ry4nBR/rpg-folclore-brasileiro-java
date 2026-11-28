package br.folclore.progressao;

import br.folclore.habilidades.Habilidade;
import br.folclore.habilidades.HabilidadeCura;
import br.folclore.habilidades.HabilidadeDano;
import java.util.List;

public final class CatalogoDePoderes {
    private CatalogoDePoderes() {
    }

    public static List<Habilidade> criarIniciais(String personagem) {
        List<String> nomes = switch (personagem) {
            case "Saci" -> List.of("Chute Furacão", "Vento Revigorante", "Assobio");
            case "Lobisomem" -> List.of("Uivo Brutal", "Mordida Lunar", "Vigor da Lua");
            case "Negrinho do Pastoreio" -> List.of("Objetos Perdidos", "Luz da Vela", "Amparo do Pastoreio");
            case "Mãe de Ouro" -> List.of("Chama Dourada", "Brilho Incandescente", "Centelha de Ouro");
            case "Batatão" -> List.of("Rolo de Fogo", "Explosão de Brasas", "Sopro de Fumaça");
            case "Mula sem Cabeça" -> List.of("Galope Infernal", "Relincho Ardente", "Labareda Maldita");
            case "Curupira" -> List.of("Pegadas Invertidas", "Raízes da Floresta", "Seiva Protetora");
            case "Caipora" -> List.of("Cipó Enrolante", "Investida do Caititu", "Ervas da Mata");
            case "Capelobo" -> List.of("Uivo da Mata", "Garras do Brejo", "Investida Feral");
            case "Iara" -> List.of("Águas Curativas", "Canto das Águas", "Onda Encantada");
            case "Boto-cor-de-rosa" -> List.of("Redemoinho Rosado", "Salto das Águas", "Beijo do Rio");
            case "Honorato" -> List.of("Maré Fúria", "Abraço da Cobra", "Tsunami Amazônico");
            default -> throw new IllegalArgumentException("Personagem desconhecido: " + personagem);
        };
        return nomes.stream().map(CatalogoDePoderes::criarPorNome).toList();
    }

    public static Habilidade criarPorNome(String nome) {
        if (nome == null) {
            throw new IllegalArgumentException("O nome do poder não pode ser nulo.");
        }
        return switch (nome) {
            case "Chute Furacão" -> new HabilidadeDano("Chute Furacão", 4, 12);
            case "Ventos Revigorantes", "Vento Revigorante" -> new HabilidadeCura("Vento Revigorante", 4, 5);
            case "Assobio" -> new HabilidadeDano("Assobio", 6, 8);
            case "Uivo Brutal" -> new HabilidadeDano("Uivo Brutal", 3, 12);
            case "Mordida Lunar" -> new HabilidadeDano("Mordida Lunar", 4, 10);
            case "Vigor da Lua" -> new HabilidadeCura("Vigor da Lua", 3, 15);
            case "Objetos Perdidos" -> new HabilidadeDano("Objetos Perdidos", 3, 11);
            case "Luz da Vela" -> new HabilidadeDano("Luz da Vela", 4, 9);
            case "Amparo do Pastoreio" -> new HabilidadeCura("Amparo do Pastoreio", 3, 15);
            case "Chama Dourada" -> new HabilidadeDano("Chama Dourada", 3, 4);
            case "Brilho Incandescente" -> new HabilidadeDano("Brilho Incandescente", 4, 3);
            case "Centelha de Ouro" -> new HabilidadeDano("Centelha de Ouro", 5, 2);
            case "Rolo de Fogo" -> new HabilidadeDano("Rolo de Fogo", 3, 5);
            case "Explosão de Brasas" -> new HabilidadeDano("Explosão de Brasas", 3, 6);
            case "Sopro de Fumaça" -> new HabilidadeDano("Sopro de Fumaça", 5, 3);
            case "Galope Infernal" -> new HabilidadeDano("Galope Infernal", 3, 7);
            case "Relincho Ardente" -> new HabilidadeDano("Relincho Ardente", 4, 5);
            case "Labareda Maldita" -> new HabilidadeDano("Labareda Maldita", 3, 8);
            case "Pegadas Invertidas" -> new HabilidadeDano("Pegadas Invertidas", 3, 10);
            case "Raízes da Floresta" -> new HabilidadeDano("Raízes da Floresta", 4, 8);
            case "Seiva Protetora" -> new HabilidadeCura("Seiva Protetora", 3, 15);
            case "Cipó Enrolante" -> new HabilidadeDano("Cipó Enrolante", 3, 11);
            case "Investida do Caititu" -> new HabilidadeDano("Investida do Caititu", 4, 9);
            case "Ervas da Mata" -> new HabilidadeCura("Ervas da Mata", 3, 15);
            case "Uivo da Mata" -> new HabilidadeDano("Uivo da Mata", 3, 16);
            case "Garras do Brejo" -> new HabilidadeDano("Garras do Brejo", 4, 12);
            case "Investida Feral" -> new HabilidadeDano("Investida Feral", 3, 14);
            case "Águas Curativas" -> new HabilidadeCura("Águas Curativas", 2, 10);
            case "Canto das Águas" -> new HabilidadeDano("Canto das Águas", 4, 10);
            case "Onda Encantada" -> new HabilidadeDano("Onda Encantada", 3, 12);
            case "Redemoinho Rosado" -> new HabilidadeDano("Redemoinho Rosado", 3, 11);
            case "Salto das Águas" -> new HabilidadeDano("Salto das Águas", 4, 9);
            case "Beijo do Rio" -> new HabilidadeCura("Beijo do Rio", 3, 15);
            case "Maré Fúria" -> new HabilidadeDano("Maré Fúria", 3, 17);
            case "Abraço da Cobra" -> new HabilidadeDano("Abraço da Cobra", 4, 13);
            case "Tsunami Amazônico" -> new HabilidadeDano("Tsunami Amazônico", 2, 19);
            default -> throw new IllegalArgumentException("Poder desconhecido: " + nome);
        };
    }
}
