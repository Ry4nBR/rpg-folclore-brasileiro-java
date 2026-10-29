# Planejamento de Nano-Tarefas — RPG de Batalhas do Folclore Brasileiro

**Dupla:** Ryan Amorim (Back-end, POO, Lógica de Batalha, IA) e Guilherme Aparecido (Front-end, UI, Telas, Save, Áudio)

**Idioma:** jogo exclusivamente em Português do Brasil. Não existem tarefas de multi-idioma, dicionários, arquivos de tradução ou inglês; os textos ficam fixos nas próprias classes.

## Premissas e convenções

- **Stack assumida:** Java 17 + Swing/Java2D + Maven, sem bibliotecas externas (pendências 1 e 2 da documentação). Em C# a estrutura de classes mapeia 1:1.
- **Atalhos de caminho:** `SRC` = `src/main/java/br/folclore/` e `RES` = `src/main/resources/`.
- **Granularidade:** 1 tarefa = 1 commit = 1 tag. IDs no formato `Fase.Bloco.Tarefa`; tags `v0.<fase>.<n>`, fechando em `v1.0.0`.
- **Ordem:** as fases são sequenciais; cada fase só começa com o pré-requisito indicado já mesclado na branch `dev`.
- **Regras adotadas para as pendências da documentação** (registradas em `docs/decisoes.md`, ajustáveis):
  - Dano = `max(1, ataque + poder - defesa)`. Ordem de turno fixa (jogador, depois inimigo); velocidade existe como atributo, sem efeito na ordem.
  - 4 slots de habilidade por personagem (slot 1 = ataque básico). Começa com 2 slots; cada ginásio vencido libera +1 até o máximo de 4.
  - XP: 20 por inimigo comum e 50 por chefe. Melhoria custa 30 XP por ponto (PV +5; Ataque, Defesa e Velocidade +1).
  - Vitória sobre um inimigo libera o poder dele. Troca de poderes equipados só após 4 poderes liberados.
  - Fuga só em encontros comuns (sempre bem-sucedida). Derrota: volta ao spawn médico com tudo restaurado e o ginásio atual reiniciado.
  - Equipe: começa só com o personagem escolhido; vencer os ginásios do Fogo e da Planta recruta os outros dois iniciais; a 4ª vaga fica reservada e vazia no protótipo. Troca de titular só fora de batalha.
  - Cada ginásio tem 2 criaturas comuns e depois o chefe. Save em arquivo `.properties`.

---

## Fase 1 — Fundação do projeto (Ryan Amorim)

### 1.1 Repositório e build

- Tarefa: 1.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `.gitignore`
- Ação: Executar `git init`; criar `.gitignore` ignorando `target/`, `out/`, `.idea/`, `*.iml`, `.vscode/`, `*.class` e `saves/`; após o commit, criar a branch `dev`.
- Commit: `chore: inicializacao do repositorio com gitignore`
- Tag: `v0.4.1`

---

- Tarefa: 1.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `README.md`
- Ação: Criar README com título do jogo, descrição de uma linha, aviso «Jogo exclusivamente em Português do Brasil» e seções vazias «Como executar» e «Equipe».
- Commit: `docs: criacao do readme inicial do projeto`
- Tag: `v0.4.2`

---

- Tarefa: 1.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `pom.xml`
- Ação: Criar `pom.xml` com groupId `br.folclore`, artifactId `rpg-folclore`, `maven.compiler.release` = 17 e `exec-maven-plugin` apontando `mainClass` para `br.folclore.Main`. Nenhuma dependência externa.
- Commit: `chore: configuracao do maven com java 17`
- Tag: `v0.4.3`

---

- Tarefa: 1.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC Main.java`
- Ação: Criar a classe `Main` com `public static void main(String[] args)` que apenas imprime `RPG do Folclore Brasileiro - iniciando`. Validar com `mvn compile exec:java`.
- Commit: `feat: criacao do ponto de entrada main`
- Tag: `v0.4.4`

---

### 1.2 Estrutura de pastas

- Tarefa: 1.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/.gitkeep`, `SRC habilidades/.gitkeep`, `SRC batalha/.gitkeep`, `SRC ia/.gitkeep`, `SRC progressao/.gitkeep`
- Ação: Criar os cinco pacotes de domínio, cada um com um `.gitkeep`.
- Commit: `chore: estrutura de pacotes do dominio`
- Tag: `v0.4.5`

---

- Tarefa: 1.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ui/.gitkeep`, `SRC ui/componentes/.gitkeep`, `SRC ui/telas/.gitkeep`, `SRC ui/efeitos/.gitkeep`, `SRC save/.gitkeep`, `SRC audio/.gitkeep`
- Ação: Criar os pacotes de interface, efeitos, save e áudio, cada um com `.gitkeep`.
- Commit: `chore: estrutura de pacotes de interface save e audio`
- Tag: `v0.4.6`

---

- Tarefa: 1.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `RES sprites/.gitkeep`, `RES retratos/.gitkeep`, `RES fundos/.gitkeep`, `RES audio/bgm/.gitkeep`, `RES audio/sfx/.gitkeep`
- Ação: Criar as pastas de recursos visuais e sonoros, cada uma com `.gitkeep`.
- Commit: `chore: estrutura de pastas de recursos visuais e sonoros`
- Tag: `v0.4.7`

---

### 1.3 Documentação de processo

- Tarefa: 1.3.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `docs/git-workflow.md`
- Ação: Documentar: `main` = estável (recebe tags), `dev` = integração, branches `feature/<dev>-<id-tarefa>`; 1 tarefa = 1 commit; merge via Pull Request revisado pelo outro integrante.
- Commit: `docs: definicao da estrategia de branches e commits`
- Tag: `v0.4.8`

---

- Tarefa: 1.3.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `docs/decisoes.md`
- Ação: Registrar: linguagem Java, idioma único PT-BR e todas as regras adotadas listadas nas premissas deste planejamento (fórmula de dano, slots, XP, fuga, derrota, equipe, save).
- Commit: `docs: registro de decisoes de escopo e regras de jogo`
- Tag: `v0.4.9`

---

## Fase 2 — Núcleo da POO: Abstração, Encapsulamento e Polimorfismo (Ryan Amorim)

Pré-requisito: Fase 1.

### 2.1 Enum de tipos

- Tarefa: 2.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Tipo.java`
- Ação: Criar `enum Tipo` com as constantes `VENTO`, `FISICO`, `PSIQUICO`.
- Commit: `feat: criacao do enum tipo com tipos dos personagens jogaveis`
- Tag: `v0.5.1`

---

- Tarefa: 2.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Tipo.java`
- Ação: Adicionar as constantes elementais `FOGO`, `PLANTA`, `AGUA`.
- Commit: `feat: adicao dos tipos elementais fogo planta e agua`
- Tag: `v0.5.2`

---

- Tarefa: 2.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Tipo.java`
- Ação: Adicionar campo `private final String rotulo`, construtor privado e `getRotulo()`. Rótulos: Vento, Físico, Psíquico, Fogo, Planta, Água.
- Commit: `feat: rotulo em portugues para cada tipo`
- Tag: `v0.5.3`

---

### 2.2 Classe abstrata Personagem (Abstração + Encapsulamento)

- Tarefa: 2.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Criar `public abstract class Personagem` vazia, com Javadoc: «Abstração: base comum de todo combatente».
- Commit: `feat: criacao da classe abstrata personagem`
- Tag: `v0.5.4`

---

- Tarefa: 2.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private final String nome` e `private final Tipo tipo` e o construtor `protected Personagem(String nome, Tipo tipo)`, lançando `IllegalArgumentException` se nome vazio ou tipo nulo.
- Commit: `feat: atributos privados nome e tipo em personagem`
- Tag: `v0.5.5`

---

- Tarefa: 2.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public String getNome()` e `public Tipo getTipo()`.
- Commit: `feat: getters de nome e tipo em personagem`
- Tag: `v0.5.6`

---

- Tarefa: 2.2.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private int pvMaximo` e `private int pvAtual`; ampliar o construtor para `(nome, tipo, pvMaximo)`; validar `pvMaximo > 0`; inicializar `pvAtual = pvMaximo`.
- Commit: `feat: encapsulamento dos pontos de vida em personagem`
- Tag: `v0.5.7`

---

- Tarefa: 2.2.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `getPvMaximo()` e `getPvAtual()`.
- Commit: `feat: getters de pontos de vida em personagem`
- Tag: `v0.5.8`

---

- Tarefa: 2.2.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private int ataque`, `defesa`, `velocidade`; ampliar o construtor para `(nome, tipo, pvMaximo, ataque, defesa, velocidade)` validando valores >= 0.
- Commit: `feat: encapsulamento de ataque defesa e velocidade em personagem`
- Tag: `v0.5.9`

---

- Tarefa: 2.2.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `getAtaque()`, `getDefesa()` e `getVelocidade()`.
- Commit: `feat: getters de ataque defesa e velocidade em personagem`
- Tag: `v0.5.10`

---

- Tarefa: 2.2.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public boolean estaVivo()` retornando `pvAtual > 0`.
- Commit: `feat: metodo estaVivo na classe personagem`
- Tag: `v0.5.11`

---

- Tarefa: 2.2.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public int receberDano(int dano)`: lançar `IllegalArgumentException` se dano < 0; `pvAtual = Math.max(0, pvAtual - dano)`; retornar o dano efetivamente aplicado.
- Commit: `feat: metodo receberDano com validacao na classe personagem`
- Tag: `v0.5.12`

---

- Tarefa: 2.2.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public int curar(int quantidade)`: validar >= 0; `pvAtual = Math.min(pvMaximo, pvAtual + quantidade)`; retornar o valor efetivamente curado.
- Commit: `feat: metodo curar com limite de pv maximo na classe personagem`
- Tag: `v0.5.13`

---

- Tarefa: 2.2.11
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public int curarPercentualDaVidaMaxima(int percentual)`: `valor = Math.max(1, pvMaximo * percentual / 100)`; delegar a `curar(valor)`.
- Commit: `feat: metodo curarPercentualDaVidaMaxima na classe personagem`
- Tag: `v0.5.14`

---

- Tarefa: 2.2.12
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public int calcularDano(Personagem alvo, int poderBase)` retornando `Math.max(1, getAtaque() + poderBase - alvo.getDefesa())`. Não aplica o dano; só calcula.
- Commit: `feat: criacao do metodo calcularDano na classe personagem`
- Tag: `v0.5.15`

---

- Tarefa: 2.2.13
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Declarar `public abstract int atacar(Personagem alvo)` (retorna o dano causado), com Javadoc explicando que cada subclasse define o próprio ataque básico (Polimorfismo).
- Commit: `feat: metodo abstrato atacar na classe personagem`
- Tag: `v0.5.16`

---

- Tarefa: 2.2.14
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Declarar `public abstract String getNomeAtaqueBasico()`.
- Commit: `feat: metodo abstrato getNomeAtaqueBasico na classe personagem`
- Tag: `v0.5.17`

---

- Tarefa: 2.2.15
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public String resumoDeStatus()` retornando, por exemplo, `Saci (Vento) | PV 50/50 | ATQ 9 | DEF 5 | VEL 10`.
- Commit: `feat: metodo resumoDeStatus na classe personagem`
- Tag: `v0.5.18`

---

### 2.3 Habilidades (Polimorfismo)

- Tarefa: 2.3.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/ResultadoAcao.java`
- Ação: Criar `public final class ResultadoAcao` com `enum Efeito { DANO, CURA, FALHA }` e campos `efeito`, `valor` (int), `mensagem` (String) com construtor.
- Commit: `feat: criacao da classe resultadoAcao`
- Tag: `v0.5.19`

---

- Tarefa: 2.3.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/ResultadoAcao.java`
- Ação: Adicionar `getEfeito()`, `getValor()` e `getMensagem()`.
- Commit: `feat: getters da classe resultadoAcao`
- Tag: `v0.5.20`

---

- Tarefa: 2.3.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/Habilidade.java`
- Ação: Criar `public interface Habilidade` com `String getNome()` e `ResultadoAcao executar(Personagem usuario, Personagem alvo)`.
- Commit: `feat: criacao da interface habilidade`
- Tag: `v0.5.21`

---

- Tarefa: 2.3.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/Habilidade.java`
- Ação: Adicionar à interface `int getUsosMaximos()`, `int getUsosRestantes()`, `boolean temUsosRestantes()` e `void restaurarUsos()`.
- Commit: `feat: contrato de limite de usos na interface habilidade`
- Tag: `v0.5.22`

---

- Tarefa: 2.3.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeBase.java`
- Ação: Criar `abstract class HabilidadeBase implements Habilidade` com `private final String nome`, `private final int usosMaximos`, `private int usosRestantes`; construtor `(nome, usosMaximos)` (usosRestantes = usosMaximos); implementar `getNome`, `getUsosMaximos`, `getUsosRestantes`.
- Commit: `feat: classe abstrata habilidadeBase com controle de usos`
- Tag: `v0.5.23`

---

- Tarefa: 2.3.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeBase.java`
- Ação: Implementar `temUsosRestantes()` (`usosRestantes > 0`), `restaurarUsos()` (volta ao máximo) e `protected void consumirUso()` (decrementa; lança `IllegalStateException` se zero).
- Commit: `feat: consumo e restauracao de usos em habilidadeBase`
- Tag: `v0.5.24`

---

- Tarefa: 2.3.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeDano.java`
- Ação: Criar `class HabilidadeDano extends HabilidadeBase` com `private final int poderBase`, construtor `(nome, usosMaximos, poderBase)` e `executar` provisório lançando `UnsupportedOperationException`.
- Commit: `feat: criacao da classe habilidadeDano`
- Tag: `v0.5.25`

---

- Tarefa: 2.3.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeDano.java`
- Ação: Implementar `executar`: sem usos retorna `ResultadoAcao` FALHA (mensagem `Sem usos restantes de <nome>!`); senão `consumirUso()`, `dano = usuario.calcularDano(alvo, poderBase)`, `alvo.receberDano(dano)` e retorna DANO com mensagem `<usuario> usou <habilidade>! <alvo> sofreu <n> de dano.`
- Commit: `feat: implementacao do metodo executar em habilidadeDano`
- Tag: `v0.5.26`

---

- Tarefa: 2.3.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeCura.java`
- Ação: Criar `class HabilidadeCura extends HabilidadeBase` com `private final int percentual`, construtor `(nome, usosMaximos, percentual)` e `executar` provisório.
- Commit: `feat: criacao da classe habilidadeCura`
- Tag: `v0.5.27`

---

- Tarefa: 2.3.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC habilidades/HabilidadeCura.java`
- Ação: Implementar `executar`: sem usos retorna FALHA; senão `consumirUso()`, `curado = usuario.curarPercentualDaVidaMaxima(percentual)` e retorna CURA com mensagem `<usuario> usou <habilidade> e recuperou <n> de PV!`
- Commit: `feat: implementacao do metodo executar em habilidadeCura`
- Tag: `v0.5.28`

---

- Tarefa: 2.3.11
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private final List<Habilidade> habilidades = new ArrayList<>()` e `getHabilidades()` retornando `Collections.unmodifiableList`.
- Commit: `feat: lista encapsulada de habilidades equipadas em personagem`
- Tag: `v0.5.29`

---

- Tarefa: 2.3.12
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar constante `MAX_HABILIDADES_EQUIPADAS = 3` e `protected void adicionarHabilidade(Habilidade h)`: rejeita nulo e excesso do limite.
- Commit: `feat: metodo adicionarHabilidade com limite em personagem`
- Tag: `v0.5.30`

---

- Tarefa: 2.3.13
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public ResultadoAcao usarHabilidade(int indice, Personagem alvo)`: validar índice e delegar a `habilidades.get(indice).executar(this, alvo)`.
- Commit: `feat: metodo usarHabilidade na classe personagem`
- Tag: `v0.5.31`

---

## Fase 3 — Personagens jogáveis: Herança (Ryan Amorim)

Pré-requisito: Fase 2.

### 3.1 Saci (Vento)

- Tarefa: 3.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Saci.java`
- Ação: Criar `class Saci extends Personagem` com construtor `super(Saci, Tipo.VENTO, 50, 9, 5, 10)` e stubs temporários de `atacar` (`return 0`) e `getNomeAtaqueBasico` (`return vazio`) para compilar.
- Commit: `feat: criacao da classe saci herdando de personagem`
- Tag: `v0.5.32`

---

- Tarefa: 3.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Saci.java`
- Ação: Extrair os literais para constantes `private static final`: `PV_BASE=50`, `ATAQUE_BASE=9`, `DEFESA_BASE=5`, `VELOCIDADE_BASE=10`; usar no `super`.
- Commit: `refactor: constantes de status base do saci`
- Tag: `v0.5.33`

---

- Tarefa: 3.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Saci.java`
- Ação: Implementar `getNomeAtaqueBasico()` retornando Rasteira de Vento e `atacar(alvo)`: `alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO))` com `PODER_ATAQUE_BASICO = 5`.
- Commit: `feat: ataque basico rasteira de vento do saci`
- Tag: `v0.5.34`

---

- Tarefa: 3.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Saci.java`
- Ação: No construtor, `adicionarHabilidade(new HabilidadeCura(Ventos Revigorantes, 4, 5))`: cura 5% da vida máxima, 4 usos.
- Commit: `feat: implementacao da habilidade ventos revigorantes do saci`
- Tag: `v0.5.35`

---

### 3.2 Lobisomem (Físico)

- Tarefa: 3.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Lobisomem.java`
- Ação: Criar `class Lobisomem extends Personagem` com `super(Lobisomem, Tipo.FISICO, 60, 12, 6, 8)` e stubs temporários dos dois métodos abstratos.
- Commit: `feat: criacao da classe lobisomem herdando de personagem`
- Tag: `v0.5.36`

---

- Tarefa: 3.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Lobisomem.java`
- Ação: Extrair constantes `PV_BASE=60`, `ATAQUE_BASE=12`, `DEFESA_BASE=6`, `VELOCIDADE_BASE=8` e usá-las no `super`.
- Commit: `refactor: constantes de status base do lobisomem`
- Tag: `v0.5.37`

---

- Tarefa: 3.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Lobisomem.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Garra Selvagem) e `atacar(alvo)` com `PODER_ATAQUE_BASICO = 7`.
- Commit: `feat: ataque basico garra selvagem do lobisomem`
- Tag: `v0.5.38`

---

- Tarefa: 3.2.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Lobisomem.java`
- Ação: No construtor, `adicionarHabilidade(new HabilidadeDano(Uivo Brutal, 3, 12))`.
- Commit: `feat: habilidade uivo brutal do lobisomem`
- Tag: `v0.5.39`

---

### 3.3 Negrinho do Pastoreio (Psíquico)

- Tarefa: 3.3.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/NegrinhoDoPastoreio.java`
- Ação: Criar `class NegrinhoDoPastoreio extends Personagem` com `super(Negrinho do Pastoreio, Tipo.PSIQUICO, 45, 10, 4, 9)` e stubs temporários.
- Commit: `feat: criacao da classe negrinhoDoPastoreio herdando de personagem`
- Tag: `v0.5.40`

---

- Tarefa: 3.3.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/NegrinhoDoPastoreio.java`
- Ação: Extrair constantes `PV_BASE=45`, `ATAQUE_BASE=10`, `DEFESA_BASE=4`, `VELOCIDADE_BASE=9` e usá-las no `super`.
- Commit: `refactor: constantes de status base do negrinho do pastoreio`
- Tag: `v0.5.41`

---

- Tarefa: 3.3.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/NegrinhoDoPastoreio.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Toque Psíquico) e `atacar(alvo)` com `PODER_ATAQUE_BASICO = 6`.
- Commit: `feat: ataque basico toque psiquico do negrinho do pastoreio`
- Tag: `v0.5.42`

---

- Tarefa: 3.3.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/NegrinhoDoPastoreio.java`
- Ação: No construtor, `adicionarHabilidade(new HabilidadeDano(Objetos Perdidos, 3, 11))`.
- Commit: `feat: habilidade objetos perdidos do negrinho do pastoreio`
- Tag: `v0.5.43`

---

### 3.4 Catálogo dos heróis

- Tarefa: 3.4.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/CatalogoDeHerois.java`
- Ação: Criar classe utilitária com `public static List<Personagem> criarTodos()` retornando novas instâncias de `Saci`, `Lobisomem` e `NegrinhoDoPastoreio` (nessa ordem). Usada pela tela de seleção.
- Commit: `feat: catalogo de herois iniciais`
- Tag: `v0.5.44`

---

## Fase 4 — Janela desktop e telas iniciais (Guilherme Aparecido)

Pré-requisito: Fase 3 (usa `CatalogoDeHerois`).

### 4.1 Janela e loop de renderização

- Tarefa: 4.1.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/ConfiguracoesUi.java`
- Ação: Criar classe de constantes: `LARGURA = 960`, `ALTURA = 540`, `FPS = 60`, `TITULO = RPG do Folclore Brasileiro`.
- Commit: `feat: constantes de configuracao da interface`
- Tag: `v0.5.45`

---

- Tarefa: 4.1.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/JanelaPrincipal.java`
- Ação: Criar `JanelaPrincipal extends JFrame`: título via `ConfiguracoesUi.TITULO`, `EXIT_ON_CLOSE`, `setResizable(false)`.
- Commit: `feat: criacao da janela desktop principal`
- Tag: `v0.5.46`

---

- Tarefa: 4.1.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`, `SRC ui/JanelaPrincipal.java`
- Ação: Criar `PainelDoJogo extends JPanel` com `setPreferredSize(960x540)`, `setFocusable(true)` e double buffer; adicionar à janela, chamar `pack()` e `setLocationRelativeTo(null)`.
- Commit: `feat: painel de desenho do jogo dentro da janela`
- Tag: `v0.5.47`

---

- Tarefa: 4.1.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`
- Ação: Sobrescrever `paintComponent`: preencher fundo preto e configurar `Graphics2D` com antialiasing desligado e interpolação `NEAREST_NEIGHBOR` (pixel art nítida).
- Commit: `feat: configuracao de renderizacao pixel art no painel`
- Tag: `v0.5.48`

---

- Tarefa: 4.1.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`
- Ação: Criar `javax.swing.Timer` com intervalo `1000 / FPS` que chama `atualizar()` e `repaint()` a cada tick; iniciar no construtor.
- Commit: `feat: loop de renderizacao a 60 fps`
- Tag: `v0.5.49`

---

- Tarefa: 4.1.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC Main.java`
- Ação: Substituir o `println` por `SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true))`.
- Commit: `feat: main abre a janela principal`
- Tag: `v0.5.50`

---

### 4.2 Infraestrutura de telas e componentes

- Tarefa: 4.2.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/Tela.java`
- Ação: Criar `interface Tela` com `atualizar()`, `desenhar(Graphics2D g)`, `aoClicar(int x, int y)`, `aoMoverMouse(int x, int y)` e `aoPressionarTecla(int codigo)`.
- Commit: `feat: criacao da interface tela`
- Tag: `v0.5.51`

---

- Tarefa: 4.2.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/GerenciadorDeTelas.java`
- Ação: Criar classe com campo `Tela telaAtual` e `trocarPara(Tela nova)`.
- Commit: `feat: criacao do gerenciador de telas`
- Tag: `v0.5.52`

---

- Tarefa: 4.2.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`, `SRC ui/GerenciadorDeTelas.java`
- Ação: Expor `atualizar()` e `desenhar(g)` no gerenciador (delegando à tela atual, tratando nulo) e chamá-los no loop e no `paintComponent`.
- Commit: `feat: delegacao do loop e do desenho ao gerenciador de telas`
- Tag: `v0.5.53`

---

- Tarefa: 4.2.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`
- Ação: Registrar `MouseListener` (`mousePressed`) repassando as coordenadas para `aoClicar`.
- Commit: `feat: repasse de cliques do mouse para a tela atual`
- Tag: `v0.5.54`

---

- Tarefa: 4.2.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`
- Ação: Registrar `MouseMotionListener` (`mouseMoved`) repassando para `aoMoverMouse`.
- Commit: `feat: repasse de movimento do mouse para a tela atual`
- Tag: `v0.5.55`

---

- Tarefa: 4.2.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/PainelDoJogo.java`
- Ação: Registrar `KeyListener` (`keyPressed`) repassando `getKeyCode()` para `aoPressionarTecla`.
- Commit: `feat: repasse de teclado para a tela atual`
- Tag: `v0.5.56`

---

- Tarefa: 4.2.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/Botao.java`
- Ação: Criar `Botao` com `x, y, largura, altura, rotulo` e `Runnable aoAcionar`; construtor completo.
- Commit: `feat: criacao do componente botao`
- Tag: `v0.5.57`

---

- Tarefa: 4.2.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/Botao.java`
- Ação: Implementar `desenhar(Graphics2D g)`: retângulo preenchido, borda de 3 px e rótulo centralizado (fonte monoespaçada bold 20) usando `FontMetrics`.
- Commit: `feat: desenho do componente botao`
- Tag: `v0.5.58`

---

- Tarefa: 4.2.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/Botao.java`
- Ação: Adicionar `boolean habilitado`, `setHabilitado`, `contem(x, y)` e `clicar(x, y)` (executa `aoAcionar` só se habilitado e dentro da área). Desenhar cinza quando desabilitado.
- Commit: `feat: deteccao de clique e estado habilitado no botao`
- Tag: `v0.5.59`

---

- Tarefa: 4.2.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/Botao.java`
- Ação: Adicionar `boolean sobMouse`, `atualizarHover(x, y)` e cor de realce no `desenhar` quando `sobMouse`.
- Commit: `feat: efeito hover no botao`
- Tag: `v0.5.60`

---

- Tarefa: 4.2.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CarregadorDeImagens.java`
- Ação: Criar `static BufferedImage carregar(String caminho)` lendo de `getResourceAsStream` com `ImageIO.read`.
- Commit: `feat: carregador de imagens a partir dos recursos`
- Tag: `v0.5.61`

---

- Tarefa: 4.2.12
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CarregadorDeImagens.java`
- Ação: Adicionar cache `Map<String, BufferedImage>` e fallback: se o arquivo não existir, logar em `System.err` e retornar um quadrado magenta 32x32.
- Commit: `feat: cache e imagem substituta no carregador de imagens`
- Tag: `v0.5.62`

---

### 4.3 Tela Inicial

- Tarefa: 4.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Criar `TelaInicial implements Tela` com construtor recebendo `GerenciadorDeTelas` e métodos vazios.
- Commit: `feat: esqueleto da tela inicial`
- Tag: `v0.5.63`

---

- Tarefa: 4.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Em `desenhar`: fundo sólido verde-escuro e título RPG do Folclore Brasileiro centralizado (fonte 40 bold).
- Commit: `feat: fundo e titulo da tela inicial`
- Tag: `v0.5.64`

---

- Tarefa: 4.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Criar `Botao botaoNovoJogo` (380, 250, 200x50, «Novo Jogo») e desenhá-lo.
- Commit: `feat: layout do botao novo jogo`
- Tag: `v0.5.65`

---

- Tarefa: 4.3.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Ligar `aoClicar` e `aoMoverMouse` ao `botaoNovoJogo`; ação provisória: `System.out.println` (ligada na tarefa 4.4.13).
- Commit: `feat: eventos de clique e hover do botao novo jogo`
- Tag: `v0.5.66`

---

- Tarefa: 4.3.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Criar `botaoContinuar` (380, 315, 200x50, «Continuar») e desenhá-lo.
- Commit: `feat: layout do botao continuar`
- Tag: `v0.5.67`

---

- Tarefa: 4.3.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Ligar clique e hover do `botaoContinuar`; ação provisória `println` (ligada ao load na Fase 11).
- Commit: `feat: eventos de clique e hover do botao continuar`
- Tag: `v0.5.68`

---

- Tarefa: 4.3.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Criar `botaoSair` (380, 380, 200x50, «Sair») e desenhá-lo.
- Commit: `feat: layout do botao sair`
- Tag: `v0.5.69`

---

- Tarefa: 4.3.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Ligar clique e hover do `botaoSair`; ação: `System.exit(0)`.
- Commit: `feat: eventos de clique e hover do botao sair`
- Tag: `v0.5.70`

---

- Tarefa: 4.3.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Navegação por teclado: campo `indiceSelecionado`; setas cima/baixo alternam o botão realçado e ENTER aciona o selecionado.
- Commit: `feat: navegacao por teclado na tela inicial`
- Tag: `v0.5.71`

---

- Tarefa: 4.3.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/JanelaPrincipal.java`
- Ação: Instanciar o `GerenciadorDeTelas` e definir `TelaInicial` como tela de abertura.
- Commit: `feat: tela inicial como tela de abertura do jogo`
- Tag: `v0.5.72`

---

### 4.4 Tela de Seleção de Personagem

- Tarefa: 4.4.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Criar a tela com construtor `(GerenciadorDeTelas, Consumer<Personagem> aoConfirmar)` e campo `List<Personagem> opcoes = CatalogoDeHerois.criarTodos()`.
- Commit: `feat: esqueleto da tela de selecao de personagem`
- Tag: `v0.5.73`

---

- Tarefa: 4.4.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Desenhar fundo e título «Escolha seu personagem».
- Commit: `feat: fundo e titulo da tela de selecao`
- Tag: `v0.5.74`

---

- Tarefa: 4.4.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Desenhar 3 cartões 200x300 em x = 100, 380, 660 e y = 110 (retângulo com borda), um por opção.
- Commit: `feat: layout dos tres cartoes de personagem`
- Tag: `v0.5.75`

---

- Tarefa: 4.4.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Em cada cartão, escrever `getNome()` e `getTipo().getRotulo()`.
- Commit: `feat: nome e tipo nos cartoes de personagem`
- Tag: `v0.5.76`

---

- Tarefa: 4.4.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Em cada cartão, listar PV, ATQ, DEF e VEL (uma linha cada) lendo os getters.
- Commit: `feat: status base nos cartoes de personagem`
- Tag: `v0.5.77`

---

- Tarefa: 4.4.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Reservar em cada cartão uma área 160x160 com retângulo cinza (substituída pelo retrato na Fase 6).
- Commit: `feat: area reservada para retrato nos cartoes`
- Tag: `v0.5.78`

---

- Tarefa: 4.4.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Campo `indiceSelecionado = -1`; clique em um cartão o seleciona e desenha borda amarela de 4 px.
- Commit: `feat: selecao e destaque de cartao por clique`
- Tag: `v0.5.79`

---

- Tarefa: 4.4.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Setas esquerda/direita movem `indiceSelecionado` (circular).
- Commit: `feat: selecao de cartao por teclado`
- Tag: `v0.5.80`

---

- Tarefa: 4.4.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Criar `botaoConfirmar` (380, 450, 200x50, «Confirmar»), desabilitado enquanto `indiceSelecionado < 0`.
- Commit: `feat: layout do botao confirmar na selecao`
- Tag: `v0.5.81`

---

- Tarefa: 4.4.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Ação do `botaoConfirmar` (e ENTER): `aoConfirmar.accept(opcoes.get(indiceSelecionado))`.
- Commit: `feat: evento de confirmacao do personagem escolhido`
- Tag: `v0.5.82`

---

- Tarefa: 4.4.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Criar `botaoVoltar` (40, 460, 160x40, «Voltar») e desenhá-lo.
- Commit: `feat: layout do botao voltar na selecao`
- Tag: `v0.5.83`

---

- Tarefa: 4.4.12
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Ação do `botaoVoltar` (e ESC): `gerenciador.trocarPara(new TelaInicial(gerenciador))`.
- Commit: `feat: evento do botao voltar na selecao`
- Tag: `v0.5.84`

---

- Tarefa: 4.4.13
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Ação do Novo Jogo passa a abrir `TelaSelecaoPersonagem` com callback provisório que imprime o nome do escolhido (substituído na Fase 8).
- Commit: `feat: transicao do novo jogo para a selecao de personagem`
- Tag: `v0.5.85`

---

## Fase 5 — Motor de batalha (Ryan Amorim)

Pré-requisito: Fase 3.

### 5.1 Estrutura da batalha

- Tarefa: 5.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public boolean ehChefe()` retornando `false` (gancho sobrescrito pelos chefes).
- Commit: `feat: gancho ehChefe na classe personagem`
- Tag: `v0.5.1`

---

- Tarefa: 5.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/EstadoBatalha.java`
- Ação: Criar `enum EstadoBatalha { INICIO, ESCOLHA_DO_JOGADOR, TURNO_DO_INIMIGO, VITORIA, DERROTA, FUGA }`.
- Commit: `feat: enum de estados da batalha`
- Tag: `v0.5.2`

---

- Tarefa: 5.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Criar `Batalha` com campos `Personagem jogador`, `Personagem inimigo`, `EstadoBatalha estado` (INICIO) e `boolean fugaPermitida = !inimigo.ehChefe()`; construtor `(jogador, inimigo)`.
- Commit: `feat: criacao da classe batalha`
- Tag: `v0.5.3`

---

- Tarefa: 5.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Adicionar `getJogador()`, `getInimigo()`, `getEstado()` e `isFugaPermitida()`.
- Commit: `feat: getters da classe batalha`
- Tag: `v0.5.4`

---

- Tarefa: 5.1.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Adicionar `List<String> registro`, `private void registrar(String msg)` e `public List<String> getRegistro()` (cópia imutável).
- Commit: `feat: registro de mensagens da batalha`
- Tag: `v0.5.5`

---

- Tarefa: 5.1.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Adicionar `private int turno = 1` e `getTurno()`.
- Commit: `feat: contador de turnos na batalha`
- Tag: `v0.5.6`

---

- Tarefa: 5.1.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Javadoc da classe: ordem de turno FIXA (jogador, depois inimigo); o atributo velocidade não altera a ordem.
- Commit: `docs: documentacao da regra de ordem fixa de turnos`
- Tag: `v0.5.7`

---

### 5.2 Ciclo de turno e ataque básico

- Tarefa: 5.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public void iniciar()`: registrar `<inimigo> apareceu!` (ou `O chefe <inimigo> desafia você!` se `ehChefe()`) e mudar estado para `ESCOLHA_DO_JOGADOR`.
- Commit: `feat: metodo iniciar da batalha`
- Tag: `v0.5.8`

---

- Tarefa: 5.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Criar `private void exigirEstado(EstadoBatalha esperado)` lançando `IllegalStateException` se o estado atual for diferente.
- Commit: `feat: validacao de estado nas acoes da batalha`
- Tag: `v0.5.9`

---

- Tarefa: 5.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public void acaoAtacar()`: exigir `ESCOLHA_DO_JOGADOR`; `dano = jogador.atacar(inimigo)`; registrar `<jogador> usou <ataque básico> e causou <dano> de dano!`.
- Commit: `feat: implementacao da acao atacar na batalha`
- Tag: `v0.5.10`

---

- Tarefa: 5.2.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Criar `private void aposAcaoDoJogador()`: se o inimigo morreu, registrar `<inimigo> foi derrotado!` e estado `VITORIA`; senão estado `TURNO_DO_INIMIGO`.
- Commit: `feat: verificacao de vitoria apos acao do jogador`
- Tag: `v0.5.11`

---

- Tarefa: 5.2.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Chamar `aposAcaoDoJogador()` ao final de `acaoAtacar()`.
- Commit: `feat: encadeamento do turno apos o ataque do jogador`
- Tag: `v0.5.12`

---

- Tarefa: 5.2.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public void executarTurnoDoInimigo()`: exigir `TURNO_DO_INIMIGO`; por enquanto o inimigo sempre usa o ataque básico (`inimigo.atacar(jogador)`) e registra a mensagem. A IA entra na Fase 7.
- Commit: `feat: turno do inimigo com ataque basico provisorio`
- Tag: `v0.5.13`

---

- Tarefa: 5.2.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Ao final do turno do inimigo: se o jogador morreu, registrar `<jogador> foi derrotado...` e estado `DERROTA`; senão `turno++` e estado `ESCOLHA_DO_JOGADOR`.
- Commit: `feat: verificacao de derrota e avanco de turno`
- Tag: `v0.5.14`

---

### 5.3 Ação Habilidade

- Tarefa: 5.3.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public void acaoHabilidade(int indice)`: exigir `ESCOLHA_DO_JOGADOR`; validar `indice` dentro de `jogador.getHabilidades()`.
- Commit: `feat: esqueleto da acao habilidade na batalha`
- Tag: `v0.5.15`

---

- Tarefa: 5.3.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Chamar `jogador.usarHabilidade(indice, inimigo)` e registrar `resultado.getMensagem()` (serve para DANO e CURA).
- Commit: `feat: execucao de habilidade e registro do resultado`
- Tag: `v0.5.16`

---

- Tarefa: 5.3.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Se o resultado for `FALHA` (sem usos), registrar a mensagem, manter o estado e retornar sem consumir o turno.
- Commit: `feat: limite de usos nao consome turno em habilidade`
- Tag: `v0.5.17`

---

- Tarefa: 5.3.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Chamar `aposAcaoDoJogador()` ao final de `acaoHabilidade` quando houve sucesso.
- Commit: `feat: encadeamento do turno apos habilidade do jogador`
- Tag: `v0.5.18`

---

### 5.4 Ver status e Fugir

- Tarefa: 5.4.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public String acaoVerStatus()`: exigir `ESCOLHA_DO_JOGADOR`; retornar `inimigo.resumoDeStatus()` sem mudar estado nem consumir turno.
- Commit: `feat: implementacao da acao ver status`
- Tag: `v0.5.19`

---

- Tarefa: 5.4.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public boolean acaoFugir()`: se `!fugaPermitida`, registrar `Não é possível fugir de um chefe!` e retornar `false`.
- Commit: `feat: bloqueio de fuga contra chefes`
- Tag: `v0.5.20`

---

- Tarefa: 5.4.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Fuga permitida: registrar `Você fugiu da batalha!`, estado `FUGA`, retornar `true`.
- Commit: `feat: implementacao da acao fugir em encontros comuns`
- Tag: `v0.5.21`

---

### 5.5 Resultado da batalha

- Tarefa: 5.5.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/ResultadoBatalha.java`
- Ação: Criar `enum ResultadoBatalha { VITORIA, DERROTA, FUGA }`.
- Commit: `feat: enum de resultado da batalha`
- Tag: `v0.5.22`

---

- Tarefa: 5.5.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public ResultadoBatalha getResultado()` mapeando estados finais; retorna `null` se em andamento.
- Commit: `feat: metodo getResultado da batalha`
- Tag: `v0.5.23`

---

- Tarefa: 5.5.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: `public boolean estaEncerrada()` verdadeiro em `VITORIA`, `DERROTA` ou `FUGA`.
- Commit: `feat: metodo estaEncerrada da batalha`
- Tag: `v0.5.24`

---

## Fase 6 — Visuais de combate (Guilherme Aparecido)

Pré-requisito: Fases 4 e 5. Todos os sprites e retratos são PNG pixel art gerados por IA, colocados em `RES`.

### 6.1 Layout da Tela de Batalha

- Tarefa: 6.1.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Criar `TelaBatalha implements Tela` com construtor `(GerenciadorDeTelas, Batalha, Consumer<ResultadoBatalha> aoEncerrar)`; chamar `batalha.iniciar()` no construtor.
- Commit: `feat: esqueleto da tela de batalha`
- Tag: `v0.6.1`

---

- Tarefa: 6.1.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/PosicoesDeBatalha.java`
- Ação: Constantes: jogador em (120, 220), inimigo em (600, 70), tamanho de sprite 192x192.
- Commit: `feat: constantes de posicao dos combatentes`
- Tag: `v0.6.2`

---

- Tarefa: 6.1.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`, `RES fundos/batalha_generico.png`
- Ação: Adicionar a imagem 960x540 e carregá-la com `CarregadorDeImagens`; desenhá-la como fundo.
- Commit: `feat: carregamento do fundo generico de batalha`
- Tag: `v0.6.3`

---

- Tarefa: 6.1.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Desenhar uma plataforma elíptica (`fillOval`) sob cada posição de combatente.
- Commit: `feat: plataformas dos combatentes na tela de batalha`
- Tag: `v0.6.4`

---

- Tarefa: 6.1.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/BarraDeVida.java`
- Ação: Criar `desenhar(g, x, y, largura, pvAtual, pvMaximo)`: fundo cinza e preenchimento proporcional à vida.
- Commit: `feat: criacao do componente barra de vida`
- Tag: `v0.6.5`

---

- Tarefa: 6.1.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/BarraDeVida.java`
- Ação: Cor por faixa: verde acima de 50%, amarelo acima de 20%, vermelho abaixo.
- Commit: `feat: cores da barra de vida por faixa`
- Tag: `v0.6.6`

---

- Tarefa: 6.1.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: HUD do inimigo: caixa (40, 40, 300x70) com nome, rótulo do tipo e `BarraDeVida`.
- Commit: `feat: hud do inimigo na tela de batalha`
- Tag: `v0.6.7`

---

- Tarefa: 6.1.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: HUD do jogador: caixa (600, 330, 320x80) com nome, `BarraDeVida` e texto `PV atual/máximo`.
- Commit: `feat: hud do jogador na tela de batalha`
- Tag: `v0.6.8`

---

### 6.2 Sprites pixel art 2D de corpo inteiro

- Tarefa: 6.2.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/NormalizadorDeNomes.java`
- Ação: `static String paraId(String nome)`: minúsculas, sem acentos, espaços e hífens viram `_` (ex.: Mãe de Ouro vira `mae_de_ouro`).
- Commit: `feat: normalizador de nomes para ids de recursos`
- Tag: `v0.6.9`

---

- Tarefa: 6.2.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Criar `obter(String id)` carregando `/sprites/<id>.png` via `CarregadorDeImagens` e `precarregar(String id)`.
- Commit: `feat: repositorio de sprites`
- Tag: `v0.6.10`

---

- Tarefa: 6.2.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Criar `desenhar(g, Personagem p, x, y, boolean espelhado)` que escala para 192x192 e espelha horizontalmente quando pedido.
- Commit: `feat: desenho de sprite de personagem com espelhamento`
- Tag: `v0.6.11`

---

- Tarefa: 6.2.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/saci.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `saci` na pré-carga.
- Commit: `feat: carregamento do sprite do saci`
- Tag: `v0.6.12`

---

- Tarefa: 6.2.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/lobisomem.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `lobisomem` na pré-carga.
- Commit: `feat: carregamento do sprite do lobisomem`
- Tag: `v0.6.13`

---

- Tarefa: 6.2.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/negrinho_do_pastoreio.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `negrinho_do_pastoreio` na pré-carga.
- Commit: `feat: carregamento do sprite do negrinho do pastoreio`
- Tag: `v0.6.14`

---

- Tarefa: 6.2.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/mae_de_ouro.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `mae_de_ouro` na pré-carga.
- Commit: `feat: carregamento do sprite da mae de ouro`
- Tag: `v0.6.15`

---

- Tarefa: 6.2.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/batatao.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `batatao` na pré-carga.
- Commit: `feat: carregamento do sprite do batatao`
- Tag: `v0.6.16`

---

- Tarefa: 6.2.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/mula_sem_cabeca.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `mula_sem_cabeca` na pré-carga.
- Commit: `feat: carregamento do sprite da mula sem cabeca`
- Tag: `v0.6.17`

---

- Tarefa: 6.2.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/curupira.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `curupira` na pré-carga.
- Commit: `feat: carregamento do sprite do curupira`
- Tag: `v0.6.18`

---

- Tarefa: 6.2.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/caipora.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `caipora` na pré-carga.
- Commit: `feat: carregamento do sprite da caipora`
- Tag: `v0.6.19`

---

- Tarefa: 6.2.12
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/capelobo.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `capelobo` na pré-carga.
- Commit: `feat: carregamento do sprite do capelobo`
- Tag: `v0.6.20`

---

- Tarefa: 6.2.13
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/iara.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `iara` na pré-carga.
- Commit: `feat: carregamento do sprite da iara`
- Tag: `v0.6.21`

---

- Tarefa: 6.2.14
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/boto_cor_de_rosa.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `boto_cor_de_rosa` na pré-carga.
- Commit: `feat: carregamento do sprite do boto cor de rosa`
- Tag: `v0.6.22`

---

- Tarefa: 6.2.15
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES sprites/honorato.png`, `SRC ui/componentes/RepositorioDeSprites.java`
- Ação: Adicionar o PNG e registrar `honorato` na pré-carga.
- Commit: `feat: carregamento do sprite do honorato`
- Tag: `v0.6.23`

---

- Tarefa: 6.2.16
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Desenhar o sprite do jogador (espelhado, de costas) na posição `JOGADOR`.
- Commit: `feat: renderizacao do sprite do jogador na batalha`
- Tag: `v0.6.24`

---

- Tarefa: 6.2.17
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Desenhar o sprite do inimigo na posição `INIMIGO`.
- Commit: `feat: renderizacao do sprite do inimigo na batalha`
- Tag: `v0.6.25`

---

### 6.3 Caixas de texto e retratos (estilo Pokémon)

- Tarefa: 6.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CaixaDeTexto.java`
- Ação: Criar `CaixaDeTexto(x, y, largura, altura)` que desenha retângulo arredondado branco com borda preta dupla.
- Commit: `feat: criacao do componente caixa de texto`
- Tag: `v0.6.26`

---

- Tarefa: 6.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CaixaDeTexto.java`
- Ação: `definirTexto(String)` com quebra de linha automática por largura usando `FontMetrics`.
- Commit: `feat: quebra automatica de linha na caixa de texto`
- Tag: `v0.6.27`

---

- Tarefa: 6.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CaixaDeTexto.java`
- Ação: Efeito máquina de escrever: `atualizar()` revela 1 caractere a cada 2 frames; `estaCompleta()` e `completar()`.
- Commit: `feat: efeito de digitacao na caixa de texto`
- Tag: `v0.6.28`

---

- Tarefa: 6.3.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Caixa em (20, 400, 480x120) exibindo as mensagens de `batalha.getRegistro()` em fila (`Queue<String>`); ENTER ou clique completa a mensagem atual e, se já completa, avança.
- Commit: `feat: exibicao das mensagens da batalha na caixa de texto`
- Tag: `v0.6.29`

---

- Tarefa: 6.3.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/CaixaDeTexto.java`
- Ação: `definirRetrato(BufferedImage)` desenhando moldura 96x96 à esquerda e deslocando o texto.
- Commit: `feat: suporte a retrato na caixa de texto`
- Tag: `v0.6.30`

---

- Tarefa: 6.3.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Criar `obter(String id)` carregando `/retratos/<id>.png` via `CarregadorDeImagens`.
- Commit: `feat: repositorio de retratos`
- Tag: `v0.6.31`

---

- Tarefa: 6.3.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/saci.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `saci`.
- Commit: `feat: carregamento do retrato do saci`
- Tag: `v0.6.32`

---

- Tarefa: 6.3.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/lobisomem.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `lobisomem`.
- Commit: `feat: carregamento do retrato do lobisomem`
- Tag: `v0.6.33`

---

- Tarefa: 6.3.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/negrinho_do_pastoreio.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `negrinho_do_pastoreio`.
- Commit: `feat: carregamento do retrato do negrinho do pastoreio`
- Tag: `v0.6.34`

---

- Tarefa: 6.3.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/mula_sem_cabeca.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `mula_sem_cabeca`.
- Commit: `feat: carregamento do retrato da mula sem cabeca`
- Tag: `v0.6.35`

---

- Tarefa: 6.3.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/capelobo.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `capelobo`.
- Commit: `feat: carregamento do retrato do capelobo`
- Tag: `v0.6.36`

---

- Tarefa: 6.3.12
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES retratos/honorato.png`, `SRC ui/componentes/RepositorioDeRetratos.java`
- Ação: Adicionar o PNG 96x96 e registrar `honorato`.
- Commit: `feat: carregamento do retrato do honorato`
- Tag: `v0.6.37`

---

- Tarefa: 6.3.13
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSelecaoPersonagem.java`
- Ação: Trocar o retângulo cinza (tarefa 4.4.6) pelo retrato real de cada inicial via `RepositorioDeRetratos`.
- Commit: `feat: retratos reais nos cartoes de selecao`
- Tag: `v0.6.38`

---

### 6.4 Menu de ações da batalha

Grade 2x2 no canto inferior direito: colunas x = 520 e 740; linhas y = 410 e 465; cada botão 200x45.

- Tarefa: 6.4.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Criar e desenhar `botaoAtacar` (520, 410, «Atacar»).
- Commit: `feat: layout do botao atacar`
- Tag: `v0.6.39`

---

- Tarefa: 6.4.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Clique/hover do `botaoAtacar` chamando `batalha.acaoAtacar()`.
- Commit: `feat: evento do botao atacar`
- Tag: `v0.6.40`

---

- Tarefa: 6.4.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Criar e desenhar `botaoHabilidade` (740, 410, «Habilidade»).
- Commit: `feat: layout do botao habilidade`
- Tag: `v0.6.41`

---

- Tarefa: 6.4.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Clique no `botaoHabilidade` troca o menu para o submenu: até 3 botões com `getNome()` e `usos x/y` de cada habilidade em `jogador.getHabilidades()`.
- Commit: `feat: submenu de habilidades na tela de batalha`
- Tag: `v0.6.42`

---

- Tarefa: 6.4.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Clique em uma habilidade chama `batalha.acaoHabilidade(i)` e volta ao menu principal; botão desabilitado quando `!temUsosRestantes()`.
- Commit: `feat: evento de uso de habilidade e bloqueio sem usos`
- Tag: `v0.6.43`

---

- Tarefa: 6.4.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Botão «Voltar» no submenu que retorna ao menu principal sem consumir turno (também com ESC).
- Commit: `feat: botao voltar no submenu de habilidades`
- Tag: `v0.6.44`

---

- Tarefa: 6.4.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Criar e desenhar `botaoVerStatus` (520, 465, «Ver status»).
- Commit: `feat: layout do botao ver status`
- Tag: `v0.6.45`

---

- Tarefa: 6.4.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Clique abre painel sobreposto com o texto de `batalha.acaoVerStatus()` e um botão «Fechar».
- Commit: `feat: painel de status do inimigo`
- Tag: `v0.6.46`

---

- Tarefa: 6.4.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Criar e desenhar `botaoFugir` (740, 465, «Fugir»).
- Commit: `feat: layout do botao fugir`
- Tag: `v0.6.47`

---

- Tarefa: 6.4.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Clique chama `batalha.acaoFugir()`; botão desabilitado quando `!batalha.isFugaPermitida()`.
- Commit: `feat: evento do botao fugir e bloqueio em chefes`
- Tag: `v0.6.48`

---

- Tarefa: 6.4.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Habilitar os quatro botões somente com estado `ESCOLHA_DO_JOGADOR` e fila de mensagens vazia.
- Commit: `feat: habilitacao do menu conforme o estado da batalha`
- Tag: `v0.6.49`

---

### 6.5 Fluxo visual do turno

- Tarefa: 6.5.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Quando o estado for `TURNO_DO_INIMIGO` e a fila de mensagens esvaziar, chamar `batalha.executarTurnoDoInimigo()` uma única vez.
- Commit: `feat: disparo automatico do turno do inimigo`
- Tag: `v0.6.50`

---

- Tarefa: 6.5.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Quando `batalha.estaEncerrada()` e a fila esvaziar, chamar `aoEncerrar.accept(batalha.getResultado())`.
- Commit: `feat: notificacao de fim de batalha`
- Tag: `v0.6.51`

---

### 6.6 Efeitos visuais de ataque e habilidade

- Tarefa: 6.6.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/Animacao.java`
- Ação: Criar `interface Animacao` com `atualizar()`, `desenhar(Graphics2D)` e `terminou()`.
- Commit: `feat: interface base de animacoes`
- Tag: `v0.6.52`

---

- Tarefa: 6.6.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/GerenciadorDeEfeitos.java`
- Ação: Lista de animações ativas com `adicionar`, `atualizar` (remove as terminadas), `desenhar` e `temAtivas()`; integrar à `TelaBatalha`.
- Commit: `feat: gerenciador de efeitos visuais`
- Tag: `v0.6.53`

---

- Tarefa: 6.6.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/AnimacaoDeslocamento.java`
- Ação: Sprite avança 40 px em direção ao alvo e volta em 20 frames.
- Commit: `feat: animacao de avanco do atacante`
- Tag: `v0.6.54`

---

- Tarefa: 6.6.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/AnimacaoTremor.java`
- Ação: Deslocamento senoidal horizontal do sprite alvo por 15 frames.
- Commit: `feat: animacao de tremor ao receber dano`
- Tag: `v0.6.55`

---

- Tarefa: 6.6.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/AnimacaoNumeroFlutuante.java`
- Ação: Texto do valor (vermelho para dano, verde para cura) sobe 30 px e some gradualmente.
- Commit: `feat: animacao de numero flutuante`
- Tag: `v0.6.56`

---

- Tarefa: 6.6.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Ao usar Atacar (ou no ataque básico do inimigo), comparar PV antes/depois e disparar deslocamento + tremor + número flutuante.
- Commit: `feat: efeitos visuais para o ataque basico`
- Tag: `v0.6.57`

---

- Tarefa: 6.6.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/CoresDeTipo.java`
- Ação: `static Color paraTipo(Tipo)`: Vento ciano, Físico laranja, Psíquico roxo, Fogo vermelho, Planta verde, Água azul.
- Commit: `feat: mapeamento de cores por tipo`
- Tag: `v0.6.58`

---

- Tarefa: 6.6.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/AnimacaoParticulas.java`
- Ação: Rajada de círculos na cor do tipo viajando do usuário ao alvo em 25 frames.
- Commit: `feat: animacao de particulas para habilidades`
- Tag: `v0.6.59`

---

- Tarefa: 6.6.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Ao resolver uma habilidade de dano, disparar partículas, depois tremor e número flutuante no alvo.
- Commit: `feat: efeitos visuais acionados por habilidades de dano`
- Tag: `v0.6.60`

---

- Tarefa: 6.6.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/efeitos/AnimacaoCura.java`
- Ação: Partículas verdes subindo sobre o sprite do usuário por 30 frames.
- Commit: `feat: animacao de cura`
- Tag: `v0.6.61`

---

- Tarefa: 6.6.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Ao resolver uma habilidade com PV do usuário aumentando (ex.: Ventos Revigorantes), disparar `AnimacaoCura` e número verde.
- Commit: `feat: efeito visual da habilidade de cura`
- Tag: `v0.6.62`

---

- Tarefa: 6.6.12
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Manter `pvExibido` de cada combatente e interpolar até o PV real somente após a animação terminar, para dano e cura aparecerem no momento certo.
- Commit: `feat: sincronizacao da barra de vida com as animacoes`
- Tag: `v0.6.63`

---

- Tarefa: 6.6.13
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaBatalha.java`
- Ação: Bloquear o avanço de mensagens e o disparo do turno do inimigo enquanto `GerenciadorDeEfeitos.temAtivas()`.
- Commit: `feat: bloqueio do fluxo durante animacoes ativas`
- Tag: `v0.6.64`

---

## Fase 7 — IA inimiga e criação individual das criaturas (Ryan Amorim)

Pré-requisito: Fases 2, 3 e 5. Cada criatura é uma subclasse de `Personagem` (Herança) e sobrescreve `atacar` de forma própria (Polimorfismo). Todas ficam em `SRC modelo/`.

### 7.1 Inteligência Artificial

- Tarefa: 7.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/TipoDeDecisao.java`
- Ação: Criar `enum TipoDeDecisao { ATACAR, USAR_HABILIDADE }`.
- Commit: `feat: enum de tipos de decisao da ia`
- Tag: `v0.7.1`

---

- Tarefa: 7.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/DecisaoIA.java`
- Ação: Criar classe imutável com `TipoDeDecisao tipo` e `int indiceHabilidade`, com fábricas estáticas `atacar()` e `habilidade(int indice)`.
- Commit: `feat: criacao da classe decisaoIA`
- Tag: `v0.7.2`

---

- Tarefa: 7.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/InteligenciaArtificial.java`
- Ação: Criar `interface InteligenciaArtificial` com `DecisaoIA decidir(Personagem inimigo, Personagem jogador, int turno)`.
- Commit: `feat: interface de inteligencia artificial inimiga`
- Tag: `v0.7.3`

---

- Tarefa: 7.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/IaPadrao.java`
- Ação: Criar `IaPadrao implements InteligenciaArtificial` cujo `decidir` provisoriamente retorna `DecisaoIA.atacar()`.
- Commit: `feat: criacao da ia padrao com decisao provisoria`
- Tag: `v0.7.4`

---

- Tarefa: 7.1.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/IaPadrao.java`
- Ação: Regra 1: se `pvAtual <= 30%` do máximo e existir `HabilidadeCura` com usos (`instanceof`), retornar `DecisaoIA.habilidade(indice)`.
- Commit: `feat: regra de ia para curar quando a vida esta baixa`
- Tag: `v0.7.5`

---

- Tarefa: 7.1.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/IaPadrao.java`
- Ação: Regra 2: em turno ímpar, se existir `HabilidadeDano` com usos, retornar `DecisaoIA.habilidade(indice)`.
- Commit: `feat: regra de ia para usar habilidade de dano em turnos impares`
- Tag: `v0.7.6`

---

- Tarefa: 7.1.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC ia/IaPadrao.java`
- Ação: Regra 3 (padrão): atacar. Escrever Javadoc com as três regras em ordem de prioridade (explicável na apresentação).
- Commit: `docs: documentacao das regras de decisao da ia`
- Tag: `v0.7.7`

---

- Tarefa: 7.1.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Adicionar campo `InteligenciaArtificial ia` e segundo construtor que o recebe; o construtor atual usa `new IaPadrao()`.
- Commit: `feat: injecao da ia na classe batalha`
- Tag: `v0.7.8`

---

- Tarefa: 7.1.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Em `executarTurnoDoInimigo`, chamar `ia.decidir(...)`: `ATACAR` usa `inimigo.atacar(jogador)`; `USAR_HABILIDADE` usa `inimigo.usarHabilidade(indice, jogador)` e registra a mensagem.
- Commit: `feat: turno do inimigo guiado pela ia`
- Tag: `v0.7.9`

---

- Tarefa: 7.1.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Se o resultado da habilidade inimiga for `FALHA`, executar o ataque básico no lugar.
- Commit: `fix: fallback para ataque basico quando a habilidade inimiga falha`
- Tag: `v0.7.10`

---

### 7.2 Mãe de Ouro (Fogo)

- Tarefa: 7.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MaeDeOuro.java`
- Ação: Criar `class MaeDeOuro extends Personagem` com `super(Mãe de Ouro, Tipo.FOGO, 40, 8, 4, 7)` e stubs temporários dos métodos abstratos.
- Commit: `feat: criacao da classe maeDeOuro herdando de personagem`
- Tag: `v0.7.11`

---

- Tarefa: 7.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MaeDeOuro.java`
- Ação: Extrair constantes `PV_BASE=40`, `ATAQUE_BASE=8`, `DEFESA_BASE=4`, `VELOCIDADE_BASE=7`.
- Commit: `refactor: constantes de status base da mae de ouro`
- Tag: `v0.7.12`

---

- Tarefa: 7.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MaeDeOuro.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Bola de Fogo) e `atacar(alvo)` com `PODER_ATAQUE_BASICO = 5`.
- Commit: `feat: ataque basico bola de fogo da mae de ouro`
- Tag: `v0.7.13`

---

- Tarefa: 7.2.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MaeDeOuro.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Chama Dourada, 3, 11))`.
- Commit: `feat: habilidade chama dourada da mae de ouro`
- Tag: `v0.7.14`

---

### 7.3 Batatão (Fogo)

- Tarefa: 7.3.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Batatao.java`
- Ação: Criar `class Batatao extends Personagem` com `super(Batatão, Tipo.FOGO, 45, 9, 5, 5)` e stubs temporários.
- Commit: `feat: criacao da classe batatao herdando de personagem`
- Tag: `v0.7.15`

---

- Tarefa: 7.3.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Batatao.java`
- Ação: Extrair constantes `PV_BASE=45`, `ATAQUE_BASE=9`, `DEFESA_BASE=5`, `VELOCIDADE_BASE=5`.
- Commit: `refactor: constantes de status base do batatao`
- Tag: `v0.7.16`

---

- Tarefa: 7.3.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Batatao.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Cabeçada Flamejante) e `atacar(alvo)` com poder 5.
- Commit: `feat: ataque basico cabecada flamejante do batatao`
- Tag: `v0.7.17`

---

- Tarefa: 7.3.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Batatao.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Rolo de Fogo, 3, 12))`.
- Commit: `feat: habilidade rolo de fogo do batatao`
- Tag: `v0.7.18`

---

### 7.4 Mula sem Cabeça — Chefe do Ginásio de Fogo

- Tarefa: 7.4.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MulaSemCabeca.java`
- Ação: Criar `class MulaSemCabeca extends Personagem` com `super(Mula sem Cabeça, Tipo.FOGO, 70, 12, 7, 8)` e stubs temporários.
- Commit: `feat: criacao da classe mulaSemCabeca herdando de personagem`
- Tag: `v0.7.19`

---

- Tarefa: 7.4.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MulaSemCabeca.java`
- Ação: Extrair constantes `PV_BASE=70`, `ATAQUE_BASE=12`, `DEFESA_BASE=7`, `VELOCIDADE_BASE=8`.
- Commit: `refactor: constantes de status base da mula sem cabeca`
- Tag: `v0.7.20`

---

- Tarefa: 7.4.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MulaSemCabeca.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Coice Flamejante) e `atacar(alvo)` com poder 7.
- Commit: `feat: ataque basico coice flamejante da mula sem cabeca`
- Tag: `v0.7.21`

---

- Tarefa: 7.4.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MulaSemCabeca.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Galope Infernal, 3, 15))`.
- Commit: `feat: habilidade galope infernal da mula sem cabeca`
- Tag: `v0.7.22`

---

- Tarefa: 7.4.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/MulaSemCabeca.java`
- Ação: Sobrescrever `ehChefe()` retornando `true`.
- Commit: `feat: mula sem cabeca marcada como chefe`
- Tag: `v0.7.23`

---

### 7.5 Curupira (Planta)

- Tarefa: 7.5.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Curupira.java`
- Ação: Criar `class Curupira extends Personagem` com `super(Curupira, Tipo.PLANTA, 42, 8, 4, 9)` e stubs temporários.
- Commit: `feat: criacao da classe curupira herdando de personagem`
- Tag: `v0.7.24`

---

- Tarefa: 7.5.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Curupira.java`
- Ação: Extrair constantes `PV_BASE=42`, `ATAQUE_BASE=8`, `DEFESA_BASE=4`, `VELOCIDADE_BASE=9`.
- Commit: `refactor: constantes de status base do curupira`
- Tag: `v0.7.25`

---

- Tarefa: 7.5.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Curupira.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Pisada Veloz) e `atacar(alvo)` com poder 5.
- Commit: `feat: ataque basico pisada veloz do curupira`
- Tag: `v0.7.26`

---

- Tarefa: 7.5.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Curupira.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Pegadas Invertidas, 3, 10))`.
- Commit: `feat: habilidade pegadas invertidas do curupira`
- Tag: `v0.7.27`

---

### 7.6 Caipora (Planta)

- Tarefa: 7.6.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Caipora.java`
- Ação: Criar `class Caipora extends Personagem` com `super(Caipora, Tipo.PLANTA, 44, 9, 5, 7)` e stubs temporários.
- Commit: `feat: criacao da classe caipora herdando de personagem`
- Tag: `v0.7.28`

---

- Tarefa: 7.6.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Caipora.java`
- Ação: Extrair constantes `PV_BASE=44`, `ATAQUE_BASE=9`, `DEFESA_BASE=5`, `VELOCIDADE_BASE=7`.
- Commit: `refactor: constantes de status base da caipora`
- Tag: `v0.7.29`

---

- Tarefa: 7.6.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Caipora.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Chicote de Cipó) e `atacar(alvo)` com poder 5.
- Commit: `feat: ataque basico chicote de cipo da caipora`
- Tag: `v0.7.30`

---

- Tarefa: 7.6.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Caipora.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Cipó Enrolante, 3, 11))`.
- Commit: `feat: habilidade cipo enrolante da caipora`
- Tag: `v0.7.31`

---

### 7.7 Capelobo — Chefe do Ginásio de Planta

- Tarefa: 7.7.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Capelobo.java`
- Ação: Criar `class Capelobo extends Personagem` com `super(Capelobo, Tipo.PLANTA, 75, 13, 7, 7)` e stubs temporários.
- Commit: `feat: criacao da classe capelobo herdando de personagem`
- Tag: `v0.7.32`

---

- Tarefa: 7.7.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Capelobo.java`
- Ação: Extrair constantes `PV_BASE=75`, `ATAQUE_BASE=13`, `DEFESA_BASE=7`, `VELOCIDADE_BASE=7`.
- Commit: `refactor: constantes de status base do capelobo`
- Tag: `v0.7.33`

---

- Tarefa: 7.7.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Capelobo.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Mordida Selvagem) e `atacar(alvo)` com poder 7.
- Commit: `feat: ataque basico mordida selvagem do capelobo`
- Tag: `v0.7.34`

---

- Tarefa: 7.7.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Capelobo.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Uivo da Mata, 3, 16))`.
- Commit: `feat: habilidade uivo da mata do capelobo`
- Tag: `v0.7.35`

---

- Tarefa: 7.7.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Capelobo.java`
- Ação: Sobrescrever `ehChefe()` retornando `true`.
- Commit: `feat: capelobo marcado como chefe`
- Tag: `v0.7.36`

---

### 7.8 Iara (Água)

- Tarefa: 7.8.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Iara.java`
- Ação: Criar `class Iara extends Personagem` com `super(Iara, Tipo.AGUA, 43, 8, 4, 8)` e stubs temporários.
- Commit: `feat: criacao da classe iara herdando de personagem`
- Tag: `v0.7.37`

---

- Tarefa: 7.8.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Iara.java`
- Ação: Extrair constantes `PV_BASE=43`, `ATAQUE_BASE=8`, `DEFESA_BASE=4`, `VELOCIDADE_BASE=8`.
- Commit: `refactor: constantes de status base da iara`
- Tag: `v0.7.38`

---

- Tarefa: 7.8.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Iara.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Jato d’Água) e `atacar(alvo)` com poder 5.
- Commit: `feat: ataque basico jato dagua da iara`
- Tag: `v0.7.39`

---

- Tarefa: 7.8.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Iara.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeCura(Águas Curativas, 2, 10))` (cura 10% da vida máxima; exercita a regra 1 da IA).
- Commit: `feat: habilidade aguas curativas da iara`
- Tag: `v0.7.40`

---

### 7.9 Boto-cor-de-rosa (Água)

- Tarefa: 7.9.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/BotoCorDeRosa.java`
- Ação: Criar `class BotoCorDeRosa extends Personagem` com `super(Boto-cor-de-rosa, Tipo.AGUA, 46, 9, 5, 6)` e stubs temporários.
- Commit: `feat: criacao da classe botoCorDeRosa herdando de personagem`
- Tag: `v0.7.41`

---

- Tarefa: 7.9.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/BotoCorDeRosa.java`
- Ação: Extrair constantes `PV_BASE=46`, `ATAQUE_BASE=9`, `DEFESA_BASE=5`, `VELOCIDADE_BASE=6`.
- Commit: `refactor: constantes de status base do boto cor de rosa`
- Tag: `v0.7.42`

---

- Tarefa: 7.9.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/BotoCorDeRosa.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Respingo Rosa) e `atacar(alvo)` com poder 5.
- Commit: `feat: ataque basico respingo rosa do boto cor de rosa`
- Tag: `v0.7.43`

---

- Tarefa: 7.9.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/BotoCorDeRosa.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Redemoinho Rosado, 3, 11))`.
- Commit: `feat: habilidade redemoinho rosado do boto cor de rosa`
- Tag: `v0.7.44`

---

### 7.10 Honorato — Chefe do Ginásio de Água

- Tarefa: 7.10.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Honorato.java`
- Ação: Criar `class Honorato extends Personagem` com `super(Honorato, Tipo.AGUA, 80, 13, 8, 7)` e stubs temporários.
- Commit: `feat: criacao da classe honorato herdando de personagem`
- Tag: `v0.7.45`

---

- Tarefa: 7.10.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Honorato.java`
- Ação: Extrair constantes `PV_BASE=80`, `ATAQUE_BASE=13`, `DEFESA_BASE=8`, `VELOCIDADE_BASE=7`.
- Commit: `refactor: constantes de status base do honorato`
- Tag: `v0.7.46`

---

- Tarefa: 7.10.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Honorato.java`
- Ação: Implementar `getNomeAtaqueBasico()` (Mordida Serpentina) e `atacar(alvo)` com poder 7.
- Commit: `feat: ataque basico mordida serpentina do honorato`
- Tag: `v0.7.47`

---

- Tarefa: 7.10.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Honorato.java`
- Ação: No construtor: `adicionarHabilidade(new HabilidadeDano(Maré Fúria, 3, 17))`.
- Commit: `feat: habilidade mare furia do honorato`
- Tag: `v0.7.48`

---

- Tarefa: 7.10.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Honorato.java`
- Ação: Sobrescrever `ehChefe()` retornando `true`.
- Commit: `feat: honorato marcado como chefe`
- Tag: `v0.7.49`

---

## Fase 8 — Ginásios, spawn médico, portas e equipe (Ryan Amorim e Guilherme Aparecido)

Pré-requisito: Fases 6 e 7. O bloco 8.1 (Ryan) deve estar mesclado antes dos blocos 8.2 a 8.5 (Guilherme).

### 8.1 Modelo de progresso, recuperação e ginásios (Ryan Amorim)

- Tarefa: 8.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public void restaurarVidaTotal()` (`pvAtual = pvMaximo`).
- Commit: `feat: metodo restaurarVidaTotal na classe personagem`
- Tag: `v0.8.1`

---

- Tarefa: 8.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public void restaurarUsosDasHabilidades()` chamando `restaurarUsos()` em cada habilidade equipada.
- Commit: `feat: metodo restaurarUsosDasHabilidades na classe personagem`
- Tag: `v0.8.2`

---

- Tarefa: 8.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public void revigorar()` que chama `restaurarVidaTotal()` e `restaurarUsosDasHabilidades()`.
- Commit: `feat: metodo revigorar na classe personagem`
- Tag: `v0.8.3`

---

- Tarefa: 8.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Ginasio.java`
- Ação: Criar `Ginasio` com `String nome`, `Tipo elemento` e `List<Personagem> encontros` (2 comuns + chefe por último); construtor valida lista com 3 itens e que o último `ehChefe()`.
- Commit: `feat: criacao da classe ginasio`
- Tag: `v0.8.4`

---

- Tarefa: 8.1.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Ginasio.java`
- Ação: Adicionar `getNome()`, `getElemento()`, `getEncontros()` (imutável), `getChefe()` e `getTotalDeEncontros()`.
- Commit: `feat: getters da classe ginasio`
- Tag: `v0.8.5`

---

- Tarefa: 8.1.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDeGinasios.java`
- Ação: `static Ginasio criarGinasioDeFogo()` com encontros `MaeDeOuro`, `Batatao`, `MulaSemCabeca`.
- Commit: `feat: montagem do ginasio do fogo`
- Tag: `v0.8.6`

---

- Tarefa: 8.1.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDeGinasios.java`
- Ação: `static Ginasio criarGinasioDePlanta()` com `Curupira`, `Caipora`, `Capelobo`.
- Commit: `feat: montagem do ginasio da planta`
- Tag: `v0.8.7`

---

- Tarefa: 8.1.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDeGinasios.java`
- Ação: `static Ginasio criarGinasioDeAgua()` com `Iara`, `BotoCorDeRosa`, `Honorato`.
- Commit: `feat: montagem do ginasio da agua`
- Tag: `v0.8.8`

---

- Tarefa: 8.1.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Equipe.java`
- Ação: Criar `Equipe` com `List<Personagem> membros`, `int indiceTitular = 0`, constante `MAX_VAGAS = 4`, construtor `(Personagem inicial)`, `getTitular()` e `getMembros()` (imutável).
- Commit: `feat: criacao da classe equipe`
- Tag: `v0.8.9`

---

- Tarefa: 8.1.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Equipe.java`
- Ação: Adicionar `boolean recrutar(Personagem p)` (respeita `MAX_VAGAS`, sem duplicados) e `boolean trocarTitular(int indice)` (valida índice e `estaVivo()`).
- Commit: `feat: recrutamento e troca de titular na equipe`
- Tag: `v0.8.10`

---

- Tarefa: 8.1.11
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Equipe.java`
- Ação: Adicionar `void revigorarTodos()` chamando `revigorar()` em cada membro.
- Commit: `feat: metodo revigorarTodos na equipe`
- Tag: `v0.8.11`

---

- Tarefa: 8.1.12
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: Criar `SessaoDeJogo` com `Equipe equipe`, `int ginasiosVencidos = 0`, `int encontrosVencidos = 0`, `Ginasio ginasioAtual`; construtor `(Equipe)` e getters.
- Commit: `feat: criacao da classe sessaoDeJogo`
- Tag: `v0.8.12`

---

- Tarefa: 8.1.13
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `boolean portaLiberada(int indiceGinasio)` retornando `indiceGinasio <= ginasiosVencidos` (0 Fogo, 1 Planta, 2 Água).
- Commit: `feat: regra de liberacao das portas dos ginasios`
- Tag: `v0.8.13`

---

- Tarefa: 8.1.14
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `void entrarNoGinasio(int indice)`: lançar `IllegalStateException` se a porta estiver trancada; criar o ginásio via `CatalogoDeGinasios` (switch 0/1/2) e zerar `encontrosVencidos`.
- Commit: `feat: entrada nos ginasios com trava de progresso`
- Tag: `v0.8.14`

---

- Tarefa: 8.1.15
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `Personagem getProximoEncontro()` retornando `ginasioAtual.getEncontros().get(encontrosVencidos)` ou `null` se concluído.
- Commit: `feat: selecao do proximo encontro do ginasio`
- Tag: `v0.8.15`

---

- Tarefa: 8.1.16
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `boolean registrarVitoriaNoEncontro()`: `encontrosVencidos++`; se todos os encontros foram vencidos, `ginasiosVencidos++` e retornar `true` (ginásio concluído), senão `false`.
- Commit: `feat: registro de vitoria em encontro e conclusao de ginasio`
- Tag: `v0.8.16`

---

- Tarefa: 8.1.17
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: Ao concluir os ginásios 1 e 2 (Fogo e Planta), recrutar na equipe o primeiro inicial de `CatalogoDeHerois.criarTodos()` que ainda não estiver nela (comparar por `getNome()`).
- Commit: `feat: recrutamento de iniciais ao concluir ginasios`
- Tag: `v0.8.17`

---

- Tarefa: 8.1.18
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `void registrarDerrota()`: `equipe.revigorarTodos()`, `encontrosVencidos = 0` e recriar o ginásio atual (inimigos novos); ginásios vencidos permanecem.
- Commit: `feat: tratamento de derrota com reinicio do ginasio atual`
- Tag: `v0.8.18`

---

- Tarefa: 8.1.19
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `boolean jogoConcluido()` retornando `ginasiosVencidos == 3`.
- Commit: `feat: deteccao de fim de jogo na sessao`
- Tag: `v0.8.19`

---

### 8.2 Tela do Spawn Médico e portas (Guilherme Aparecido)

- Tarefa: 8.2.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar a tela com construtor `(GerenciadorDeTelas, SessaoDeJogo)` e métodos vazios.
- Commit: `feat: esqueleto da tela de spawn medico`
- Tag: `v0.8.20`

---

- Tarefa: 8.2.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`, `RES fundos/spawn_medico.png`
- Ação: Adicionar a imagem 960x540, carregá-la e desenhá-la como fundo.
- Commit: `feat: carregamento do fundo do spawn medico`
- Tag: `v0.8.21`

---

- Tarefa: 8.2.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Desenhar título «Spawn Médico» e o status do titular (nome, PV atual/máximo).
- Commit: `feat: titulo e status do titular no spawn medico`
- Tag: `v0.8.22`

---

- Tarefa: 8.2.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar e desenhar `botaoCurar` (380, 400, 200x50, «Revigorar equipe»).
- Commit: `feat: layout do botao revigorar equipe`
- Tag: `v0.8.23`

---

- Tarefa: 8.2.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Ação do botão: `sessao.getEquipe().revigorarTodos()` e exibir «Sua equipe foi revigorada!» por 2 segundos.
- Commit: `feat: evento de recuperacao de vida e habilidades no spawn medico`
- Tag: `v0.8.24`

---

- Tarefa: 8.2.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Desenhar a porta do Fogo (retângulo 140x220 em x=130, y=130) com rótulo «Fogo».
- Commit: `feat: layout da porta do ginasio do fogo`
- Tag: `v0.8.25`

---

- Tarefa: 8.2.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Clique na porta do Fogo: `sessao.entrarNoGinasio(0)` e `trocarPara(new TelaGinasio(gerenciador, sessao, 0))`.
- Commit: `feat: acesso ao ginasio do fogo pela porta`
- Tag: `v0.8.26`

---

- Tarefa: 8.2.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Desenhar a porta da Planta (x=410, y=130), com cadeado e cor cinza quando `!sessao.portaLiberada(1)`.
- Commit: `feat: layout da porta do ginasio da planta`
- Tag: `v0.8.27`

---

- Tarefa: 8.2.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Clique na porta da Planta: se trancada, mostrar «Vença o Ginásio do Fogo para abrir esta porta.»; se liberada, `entrarNoGinasio(1)` e abrir `TelaGinasio`.
- Commit: `feat: acesso e trava da porta do ginasio da planta`
- Tag: `v0.8.28`

---

- Tarefa: 8.2.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Desenhar a porta da Água (x=690, y=130), com cadeado e cor cinza quando `!sessao.portaLiberada(2)`.
- Commit: `feat: layout da porta do ginasio da agua`
- Tag: `v0.8.29`

---

- Tarefa: 8.2.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Clique na porta da Água: se trancada, mostrar «Vença o Ginásio da Planta para abrir esta porta.»; se liberada, `entrarNoGinasio(2)` e abrir `TelaGinasio`.
- Commit: `feat: acesso e trava da porta do ginasio da agua`
- Tag: `v0.8.30`

---

### 8.3 Tela dos Ginásios (Guilherme Aparecido)

- Tarefa: 8.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Criar a tela com construtor `(GerenciadorDeTelas, SessaoDeJogo, int indiceGinasio)` e métodos vazios.
- Commit: `feat: esqueleto da tela de ginasio`
- Tag: `v0.8.31`

---

- Tarefa: 8.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES fundos/ginasio_fogo.png`, `SRC ui/telas/TelaGinasio.java`
- Ação: Adicionar a imagem 960x540 e carregá-la quando `indiceGinasio == 0`.
- Commit: `feat: carregamento do cenario do ginasio do fogo`
- Tag: `v0.8.32`

---

- Tarefa: 8.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES fundos/ginasio_planta.png`, `SRC ui/telas/TelaGinasio.java`
- Ação: Adicionar a imagem 960x540 e carregá-la quando `indiceGinasio == 1`.
- Commit: `feat: carregamento do cenario do ginasio da planta`
- Tag: `v0.8.33`

---

- Tarefa: 8.3.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `RES fundos/ginasio_agua.png`, `SRC ui/telas/TelaGinasio.java`
- Ação: Adicionar a imagem 960x540 e carregá-la quando `indiceGinasio == 2`.
- Commit: `feat: carregamento do cenario do ginasio da agua`
- Tag: `v0.8.34`

---

- Tarefa: 8.3.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Desenhar o nome do ginásio e o progresso «Desafio X de 3» (X = `encontrosVencidos + 1`).
- Commit: `feat: titulo e progresso na tela de ginasio`
- Tag: `v0.8.35`

---

- Tarefa: 8.3.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Criar e desenhar `botaoDesafiar` (380, 400, 200x50, «Desafiar»); no último encontro o rótulo vira «Enfrentar o Chefe».
- Commit: `feat: layout do botao desafiar`
- Tag: `v0.8.36`

---

- Tarefa: 8.3.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Ação do botão: `new Batalha(sessao.getEquipe().getTitular(), sessao.getProximoEncontro())` e `trocarPara(new TelaBatalha(...))` com callback de fim de batalha.
- Commit: `feat: inicio de batalha a partir da tela de ginasio`
- Tag: `v0.8.37`

---

- Tarefa: 8.3.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Callback `VITORIA`: `sessao.registrarVitoriaNoEncontro()`; se retornar `true`, ir ao `TelaSpawnMedico`; senão, voltar à `TelaGinasio`.
- Commit: `feat: tratamento de vitoria no retorno da batalha`
- Tag: `v0.8.38`

---

- Tarefa: 8.3.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Callback `FUGA`: voltar à `TelaGinasio` sem alterar o progresso.
- Commit: `feat: tratamento de fuga no retorno da batalha`
- Tag: `v0.8.39`

---

- Tarefa: 8.3.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Callback `DERROTA`: `sessao.registrarDerrota()` e ir ao `TelaSpawnMedico` (a tela de derrota entra na Fase 10).
- Commit: `feat: tratamento de derrota no retorno da batalha`
- Tag: `v0.8.40`

---

- Tarefa: 8.3.11
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Botão «Spawn Médico» (40, 460, 200x40) que volta ao `TelaSpawnMedico` entre encontros, permitindo revigorar.
- Commit: `feat: botao de retorno ao spawn medico no ginasio`
- Tag: `v0.8.41`

---

### 8.4 Ligação com o início de jogo (Guilherme Aparecido)

- Tarefa: 8.4.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Substituir o callback provisório (tarefa 4.4.13): criar `new Equipe(escolhido)`, `new SessaoDeJogo(equipe)` e abrir `TelaSpawnMedico`.
- Commit: `feat: novo jogo cria sessao e leva ao spawn medico`
- Tag: `v0.8.42`

---

### 8.5 Tela de Equipe e troca de titular (Guilherme Aparecido)

- Tarefa: 8.5.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaEquipe.java`
- Ação: Criar a tela `(GerenciadorDeTelas, SessaoDeJogo)` listando cada membro em uma linha: nome, tipo, PV atual/máximo e marcador «Titular».
- Commit: `feat: tela de equipe com lista de membros`
- Tag: `v0.8.43`

---

- Tarefa: 8.5.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaEquipe.java`
- Ação: Botão «Tornar titular» por membro chamando `equipe.trocarTitular(i)`; desabilitado para o titular atual e para membros sem vida.
- Commit: `feat: botao de troca de titular na equipe`
- Tag: `v0.8.44`

---

- Tarefa: 8.5.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaEquipe.java`
- Ação: Desenhar as vagas restantes até 4: vagas livres como «Vaga disponível» e a 4ª sempre como «Vaga reservada» (cinza).
- Commit: `feat: exibicao das vagas livres e da vaga reservada`
- Tag: `v0.8.45`

---

- Tarefa: 8.5.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaEquipe.java`
- Ação: Botão «Voltar» retornando ao `TelaSpawnMedico`.
- Commit: `feat: botao voltar na tela de equipe`
- Tag: `v0.8.46`

---

- Tarefa: 8.5.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar e ligar `botaoEquipe` (40, 400, 200x50, «Equipe») abrindo `TelaEquipe`.
- Commit: `feat: botao equipe no spawn medico`
- Tag: `v0.8.47`

---

## Fase 9 — Progressão: XP, melhoria de atributos, poderes e slots (Ryan Amorim e Guilherme Aparecido)

Pré-requisito: Fase 8. Os blocos 9.1, 9.2 e 9.4 (Ryan) devem estar mesclados antes dos blocos 9.3 e 9.5 (Guilherme).

### 9.1 XP (Ryan Amorim)

- Tarefa: 9.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private int xp` e `getXp()`.
- Commit: `feat: atributo xp encapsulado em personagem`
- Tag: `v0.9.1`

---

- Tarefa: 9.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public void ganharExperiencia(int quantidade)`: lançar `IllegalArgumentException` se <= 0; somar ao `xp`.
- Commit: `feat: metodo ganharExperiencia na classe personagem`
- Tag: `v0.9.2`

---

- Tarefa: 9.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public boolean gastarExperiencia(int quantidade)`: retorna `false` sem alterar nada se o saldo for insuficiente; senão subtrai e retorna `true`.
- Commit: `feat: metodo gastarExperiencia na classe personagem`
- Tag: `v0.9.3`

---

- Tarefa: 9.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Constantes `XP_COMUM = 20` e `XP_CHEFE = 50`; `public int getXpRecompensa()` retorna `ehChefe() ? XP_CHEFE : XP_COMUM` (Polimorfismo via `ehChefe`).
- Commit: `feat: formula de xp de recompensa por inimigo`
- Tag: `v0.9.4`

---

- Tarefa: 9.1.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC batalha/Batalha.java`
- Ação: Adicionar `int getXpDaVitoria()` retornando `inimigo.getXpRecompensa()` se o resultado for `VITORIA`, senão `0` (exibido na tela de vitória).
- Commit: `feat: consulta de xp ganho ao fim da batalha`
- Tag: `v0.9.5`

---

- Tarefa: 9.1.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: Em `registrarVitoriaNoEncontro()`, antes de incrementar, obter o inimigo derrotado e chamar `equipe.getTitular().ganharExperiencia(inimigo.getXpRecompensa())`.
- Commit: `feat: distribuicao de xp ao titular apos vitoria`
- Tag: `v0.9.6`

---

### 9.2 Melhoria permanente de atributos (Ryan Amorim)

- Tarefa: 9.2.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Atributo.java`
- Ação: Criar `enum Atributo { PV, ATAQUE, DEFESA, VELOCIDADE }`.
- Commit: `feat: enum de atributos melhoraveis`
- Tag: `v0.9.7`

---

- Tarefa: 9.2.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Constante `CUSTO_MELHORIA_XP = 30` e `public boolean podeMelhorar()` (`xp >= CUSTO_MELHORIA_XP`).
- Commit: `feat: custo e verificacao de melhoria de atributo`
- Tag: `v0.9.8`

---

- Tarefa: 9.2.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Esqueleto de `public boolean melhorarAtributo(Atributo a)`: se `!gastarExperiencia(CUSTO_MELHORIA_XP)` retorna `false`; senão chama `switch` (casos vazios) e retorna `true`.
- Commit: `feat: esqueleto do metodo melhorarAtributo`
- Tag: `v0.9.9`

---

- Tarefa: 9.2.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Caso `PV`: `pvMaximo += 5` e `pvAtual += 5`.
- Commit: `feat: melhoria permanente de pv`
- Tag: `v0.9.10`

---

- Tarefa: 9.2.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Casos `ATAQUE`, `DEFESA` e `VELOCIDADE`: `+= 1` no campo correspondente.
- Commit: `feat: melhoria permanente de ataque defesa e velocidade`
- Tag: `v0.9.11`

---

### 9.3 Tela de melhoria de atributos (Guilherme Aparecido)

- Tarefa: 9.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Criar a tela com construtor `(GerenciadorDeTelas, SessaoDeJogo)` e métodos vazios.
- Commit: `feat: esqueleto da tela de melhoria de atributos`
- Tag: `v0.9.12`

---

- Tarefa: 9.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Desenhar fundo, título «Melhorar atributos», nome do titular e saldo «XP: N (custo 30 por ponto)».
- Commit: `feat: titulo e saldo de xp na tela de melhoria`
- Tag: `v0.9.13`

---

- Tarefa: 9.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Listar PV máximo, Ataque, Defesa e Velocidade atuais do titular (uma linha cada).
- Commit: `feat: lista de atributos atuais na tela de melhoria`
- Tag: `v0.9.14`

---

- Tarefa: 9.3.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Botão «+ PV» ao lado da linha de PV; clique chama `titular.melhorarAtributo(Atributo.PV)` e exibe «PV aumentou!».
- Commit: `feat: botao de melhoria de pv`
- Tag: `v0.9.15`

---

- Tarefa: 9.3.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Botão «+ Ataque» com clique chamando `melhorarAtributo(Atributo.ATAQUE)`.
- Commit: `feat: botao de melhoria de ataque`
- Tag: `v0.9.16`

---

- Tarefa: 9.3.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Botão «+ Defesa» com clique chamando `melhorarAtributo(Atributo.DEFESA)`.
- Commit: `feat: botao de melhoria de defesa`
- Tag: `v0.9.17`

---

- Tarefa: 9.3.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Botão «+ Velocidade» com clique chamando `melhorarAtributo(Atributo.VELOCIDADE)`.
- Commit: `feat: botao de melhoria de velocidade`
- Tag: `v0.9.18`

---

- Tarefa: 9.3.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Desabilitar os quatro botões de melhoria enquanto `!titular.podeMelhorar()`; reavaliar a cada `atualizar()`.
- Commit: `feat: bloqueio dos botoes de melhoria sem xp suficiente`
- Tag: `v0.9.19`

---

- Tarefa: 9.3.9
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaMelhoriaDeAtributos.java`
- Ação: Botão «Voltar» (e ESC) retornando ao `TelaSpawnMedico`.
- Commit: `feat: botao voltar na tela de melhoria`
- Tag: `v0.9.20`

---

- Tarefa: 9.3.10
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar e ligar `botaoMelhorias` (600, 400, 200x50, «Melhorar atributos») abrindo `TelaMelhoriaDeAtributos`.
- Commit: `feat: botao de melhoria de atributos no spawn medico`
- Tag: `v0.9.21`

---

### 9.4 Poderes liberados e slots de habilidade (Ryan Amorim)

- Tarefa: 9.4.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `private final List<Habilidade> poderesLiberados` e `getPoderesLiberados()` imutável.
- Commit: `feat: lista de poderes liberados em personagem`
- Tag: `v0.9.22`

---

- Tarefa: 9.4.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Em `adicionarHabilidade`, registrar também a habilidade em `poderesLiberados` (poder inicial do personagem conta como liberado).
- Commit: `feat: poder inicial registrado como liberado`
- Tag: `v0.9.23`

---

- Tarefa: 9.4.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Adicionar `public boolean liberarPoder(Habilidade h)`: ignora duplicados (comparar `getNome()`), chama `h.restaurarUsos()`, adiciona ao pool e retorna `true`.
- Commit: `feat: metodo liberarPoder na classe personagem`
- Tag: `v0.9.24`

---

- Tarefa: 9.4.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Constante `MAX_SLOTS = 4`, campo `slotsDeHabilidade = 2` (ataque básico + 1 habilidade) e `getSlotsDeHabilidade()`.
- Commit: `feat: slots de habilidade em personagem`
- Tag: `v0.9.25`

---

- Tarefa: 9.4.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: `public boolean desbloquearSlot()`: se `slotsDeHabilidade < MAX_SLOTS`, incrementa e retorna `true`; senão `false`.
- Commit: `feat: metodo desbloquearSlot na classe personagem`
- Tag: `v0.9.26`

---

- Tarefa: 9.4.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Trocar o limite fixo `MAX_HABILIDADES_EQUIPADAS = 3` por `slotsDeHabilidade - 1` em `adicionarHabilidade`; remover a constante.
- Commit: `refactor: limite de habilidades equipadas baseado nos slots`
- Tag: `v0.9.27`

---

- Tarefa: 9.4.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Em `liberarPoder`, se houver slot livre (`habilidades.size() < slotsDeHabilidade - 1`), equipar o novo poder automaticamente.
- Commit: `feat: equipar poder liberado quando ha slot livre`
- Tag: `v0.9.28`

---

- Tarefa: 9.4.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: `public boolean podeTrocarPoderes()` retornando `poderesLiberados.size() >= 4`.
- Commit: `feat: regra de troca de poderes apos quatro liberados`
- Tag: `v0.9.29`

---

- Tarefa: 9.4.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: `public boolean trocarPoderEquipado(int indiceEquipado, Habilidade nova)`: exige `podeTrocarPoderes()`, índice válido e `nova` presente em `poderesLiberados`; substitui o slot e retorna `true`.
- Commit: `feat: metodo trocarPoderEquipado na classe personagem`
- Tag: `v0.9.30`

---

- Tarefa: 9.4.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: Em `registrarVitoriaNoEncontro()`, chamar `titular.liberarPoder(inimigo.getHabilidades().get(0))` para cada inimigo derrotado, inclusive chefes.
- Commit: `feat: vitoria libera o poder do inimigo derrotado`
- Tag: `v0.9.31`

---

- Tarefa: 9.4.11
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/Equipe.java`
- Ação: Adicionar `void desbloquearSlotParaTodos()` chamando `desbloquearSlot()` em cada membro.
- Commit: `feat: desbloqueio de slot para toda a equipe`
- Tag: `v0.9.32`

---

- Tarefa: 9.4.12
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: Quando `registrarVitoriaNoEncontro()` concluir um ginásio, chamar `equipe.desbloquearSlotParaTodos()`.
- Commit: `feat: desbloqueio de slot de habilidade pos-ginasio`
- Tag: `v0.9.33`

---

### 9.5 Tela de poderes (Guilherme Aparecido)

- Tarefa: 9.5.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaPoderes.java`
- Ação: Criar a tela `(GerenciadorDeTelas, SessaoDeJogo)` desenhando 4 slots: slot 1 = ataque básico (fixo), slots equipados com o nome da habilidade e slots não desbloqueados como «Bloqueado (venha um ginásio)».
- Commit: `feat: tela de poderes com os quatro slots`
- Tag: `v0.9.34`

---

- Tarefa: 9.5.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaPoderes.java`
- Ação: Listar `getPoderesLiberados()` em uma coluna selecionável por clique, mostrando nome e usos máximos.
- Commit: `feat: lista de poderes liberados na tela de poderes`
- Tag: `v0.9.35`

---

- Tarefa: 9.5.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaPoderes.java`
- Ação: Botão «Equipar»: com um slot equipado e um poder selecionados, chama `trocarPoderEquipado(slot, poder)`.
- Commit: `feat: botao equipar poder na tela de poderes`
- Tag: `v0.9.36`

---

- Tarefa: 9.5.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaPoderes.java`
- Ação: Desabilitar «Equipar» e mostrar «Libere 4 poderes para trocar» enquanto `!titular.podeTrocarPoderes()`.
- Commit: `feat: aviso de bloqueio de troca de poderes`
- Tag: `v0.9.37`

---

- Tarefa: 9.5.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaPoderes.java`
- Ação: Botão «Voltar» (e ESC) retornando ao `TelaSpawnMedico`.
- Commit: `feat: botao voltar na tela de poderes`
- Tag: `v0.9.38`

---

- Tarefa: 9.5.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar e ligar `botaoPoderes` (820, 400, 100x50, «Poderes») abrindo `TelaPoderes`.
- Commit: `feat: botao de poderes no spawn medico`
- Tag: `v0.9.39`

---

## Fase 10 — Diálogos, história, vitória, derrota e créditos (Guilherme Aparecido)

Pré-requisito: Fases 8 e 9.

### 10.1 Sistema de diálogo

- Tarefa: 10.1.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/componentes/LinhaDeDialogo.java`
- Ação: Criar classe imutável com `String idRetrato` (nulo = narrador), `String falante` e `String texto`.
- Commit: `feat: criacao da classe linhaDeDialogo`
- Tag: `v0.10.1`

---

- Tarefa: 10.1.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Criar a tela com construtor `(GerenciadorDeTelas, Tela telaDeFundo, List<LinhaDeDialogo> linhas, Runnable aoTerminar)` e índice da linha atual.
- Commit: `feat: esqueleto da tela de dialogo`
- Tag: `v0.10.2`

---

- Tarefa: 10.1.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Em `desenhar`: desenhar `telaDeFundo` (ou fundo escuro se nulo) e uma camada preta com 50% de opacidade.
- Commit: `feat: fundo da tela de dialogo`
- Tag: `v0.10.3`

---

- Tarefa: 10.1.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Instanciar `CaixaDeTexto` em (40, 380, 880x140) exibindo o texto da linha atual com efeito de digitação.
- Commit: `feat: caixa de texto na tela de dialogo`
- Tag: `v0.10.4`

---

- Tarefa: 10.1.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Se `idRetrato != null`, chamar `caixa.definirRetrato(RepositorioDeRetratos.obter(id))`; narrador não exibe retrato.
- Commit: `feat: retrato do falante no dialogo`
- Tag: `v0.10.5`

---

- Tarefa: 10.1.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Desenhar o nome do falante em uma etiqueta acima da caixa de texto.
- Commit: `feat: etiqueta com nome do falante no dialogo`
- Tag: `v0.10.6`

---

- Tarefa: 10.1.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: ENTER/clique: se a digitação não terminou, `completar()`; senão avançar a linha; após a última, chamar `aoTerminar.run()`.
- Commit: `feat: avanco de linhas e fim do dialogo`
- Tag: `v0.10.7`

---

- Tarefa: 10.1.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDialogo.java`
- Ação: Desenhar um indicador «▼» piscando (a cada 30 frames) no canto da caixa quando o texto estiver completo.
- Commit: `feat: indicador de continuar no dialogo`
- Tag: `v0.10.8`

---

### 10.2 Roteiro da história principal

Todos os textos em PT-BR, 3 a 6 linhas por cena, tom de lenda popular. Classe estática `Roteiro` retornando `List<LinhaDeDialogo>`.

- Tarefa: 10.2.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Criar `introducao()` (narrador): um Brasil onde as lendas ganharam vida e três ginásios elementais guardam o equilíbrio da floresta.
- Commit: `feat: roteiro da introducao da historia`
- Tag: `v0.10.9`

---

- Tarefa: 10.2.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Criar `preGinasio(int indice)` caso 0: desafio da Mula sem Cabeça (retrato `mula_sem_cabeca`).
- Commit: `feat: dialogo pre-chefe do ginasio do fogo`
- Tag: `v0.10.10`

---

- Tarefa: 10.2.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Caso 1 de `preGinasio`: desafio do Capelobo (retrato `capelobo`).
- Commit: `feat: dialogo pre-chefe do ginasio da planta`
- Tag: `v0.10.11`

---

- Tarefa: 10.2.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Caso 2 de `preGinasio`: desafio do Honorato (retrato `honorato`).
- Commit: `feat: dialogo pre-chefe do ginasio da agua`
- Tag: `v0.10.12`

---

- Tarefa: 10.2.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Criar `aposGinasio(int indice)` caso 0: derrota da Mula, um novo espaço de habilidade foi liberado e um aliado inicial se junta à equipe.
- Commit: `feat: dialogo pos-vitoria do ginasio do fogo`
- Tag: `v0.10.13`

---

- Tarefa: 10.2.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Caso 1 de `aposGinasio`: derrota do Capelobo, novo espaço liberado e o último inicial se junta à equipe.
- Commit: `feat: dialogo pos-vitoria do ginasio da planta`
- Tag: `v0.10.14`

---

- Tarefa: 10.2.7
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Caso 2 de `aposGinasio`: derrota do Honorato e a água da floresta volta a correr.
- Commit: `feat: dialogo pos-vitoria do ginasio da agua`
- Tag: `v0.10.15`

---

- Tarefa: 10.2.8
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/Roteiro.java`
- Ação: Criar `desfecho()`: narrador encerra a história e agradece às lendas (precede os créditos).
- Commit: `feat: roteiro do desfecho da historia`
- Tag: `v0.10.16`

---

### 10.3 Ligação dos diálogos ao fluxo

- Tarefa: 10.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Novo Jogo passa a abrir `TelaDialogo` com `Roteiro.introducao()` e, ao terminar, a `TelaSelecaoPersonagem`.
- Commit: `feat: introducao da historia antes da selecao de personagem`
- Tag: `v0.10.17`

---

- Tarefa: 10.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Ao clicar em Desafiar e `getProximoEncontro().ehChefe()`, abrir `TelaDialogo` com `Roteiro.preGinasio(indice)` antes da batalha.
- Commit: `feat: dialogo antes da batalha contra o chefe`
- Tag: `v0.10.18`

---

- Tarefa: 10.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Quando o ginásio for concluído, abrir `TelaDialogo` com `Roteiro.aposGinasio(indice)`; ao fim ir ao `TelaSpawnMedico` (ou ao desfecho + créditos se `jogoConcluido()`).
- Commit: `feat: dialogo apos a conclusao do ginasio`
- Tag: `v0.10.19`

---

### 10.4 Tela de Vitória

- Tarefa: 10.4.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaVitoria.java`
- Ação: Criar `(GerenciadorDeTelas, Batalha, Runnable aoContinuar)` desenhando fundo e título «Vitória!».
- Commit: `feat: esqueleto da tela de vitoria`
- Tag: `v0.10.20`

---

- Tarefa: 10.4.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaVitoria.java`
- Ação: Exibir «+N XP» com `batalha.getXpDaVitoria()`.
- Commit: `feat: exibicao do xp ganho na tela de vitoria`
- Tag: `v0.10.21`

---

- Tarefa: 10.4.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaVitoria.java`
- Ação: Exibir «Novo poder liberado: \<nome>» com `batalha.getInimigo().getHabilidades().get(0).getNome()`.
- Commit: `feat: exibicao do poder liberado na tela de vitoria`
- Tag: `v0.10.22`

---

- Tarefa: 10.4.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaVitoria.java`
- Ação: Botão «Continuar» (380, 440, 200x50): layout e clique chamando `aoContinuar.run()`.
- Commit: `feat: botao continuar na tela de vitoria`
- Tag: `v0.10.23`

---

- Tarefa: 10.4.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: No callback `VITORIA`, abrir `TelaVitoria` e só então seguir o fluxo (próximo desafio, spawn médico ou diálogo de conclusão).
- Commit: `feat: integracao da tela de vitoria ao fluxo do ginasio`
- Tag: `v0.10.24`

---

### 10.5 Tela de Derrota

- Tarefa: 10.5.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDerrota.java`
- Ação: Criar `(GerenciadorDeTelas, Runnable aoRecomecar)` com fundo escuro e título «Derrota».
- Commit: `feat: esqueleto da tela de derrota`
- Tag: `v0.10.25`

---

- Tarefa: 10.5.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDerrota.java`
- Ação: Texto «Sua equipe foi levada de volta ao Spawn Médico.»
- Commit: `feat: mensagem da tela de derrota`
- Tag: `v0.10.26`

---

- Tarefa: 10.5.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDerrota.java`
- Ação: Criar e desenhar `botaoRecomecar` (360, 400, 240x50, «Recomeçar do Spawn»).
- Commit: `feat: layout do botao recomecar`
- Tag: `v0.10.27`

---

- Tarefa: 10.5.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaDerrota.java`
- Ação: Clique/ENTER no botão chama `aoRecomecar.run()`.
- Commit: `feat: evento do botao recomecar`
- Tag: `v0.10.28`

---

- Tarefa: 10.5.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: No callback `DERROTA`, chamar `sessao.registrarDerrota()` e abrir `TelaDerrota` (substitui o retorno direto da tarefa 8.3.10) cujo `aoRecomecar` abre `TelaSpawnMedico`.
- Commit: `feat: integracao da tela de derrota ao fluxo do ginasio`
- Tag: `v0.10.29`

---

### 10.6 Tela de Créditos

- Tarefa: 10.6.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaCreditos.java`
- Ação: Criar a tela com fundo preto e `GerenciadorDeTelas` no construtor.
- Commit: `feat: esqueleto da tela de creditos`
- Tag: `v0.10.30`

---

- Tarefa: 10.6.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaCreditos.java`
- Ação: Rolagem vertical (1 px por frame) das linhas: título do jogo; Ryan Amorim (Back-end, POO, Lógica de Batalha, IA); Guilherme Aparecido (Front-end, UI, Save, Áudio); «Inspirado no folclore brasileiro».
- Commit: `feat: rolagem de creditos`
- Tag: `v0.10.31`

---

- Tarefa: 10.6.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaCreditos.java`
- Ação: Ao fim da rolagem, ou ao pressionar ESC/ENTER, voltar à `TelaInicial`.
- Commit: `feat: saida da tela de creditos`
- Tag: `v0.10.32`

---

- Tarefa: 10.6.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaGinasio.java`
- Ação: Após `Roteiro.desfecho()` (jogo concluído), abrir `TelaCreditos`.
- Commit: `feat: transicao do desfecho para os creditos`
- Tag: `v0.10.33`

---

## Fase 11 — Salvar e carregar progresso (Ryan Amorim e Guilherme Aparecido)

Pré-requisito: Fase 10. O bloco 11.1 (Ryan) deve estar mesclado antes dos blocos 11.2 e 11.3 (Guilherme). O jogo só salva no Spawn Médico (ponto sem batalha em andamento), no arquivo `saves/save1.properties`.

### 11.1 Estado exportável do domínio (Ryan Amorim)

- Tarefa: 11.1.1
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDePoderes.java`
- Ação: Criar `static Habilidade criarPorNome(String nome)` com `switch` cujo `default` lança `IllegalArgumentException`.
- Commit: `feat: esqueleto do catalogo de poderes`
- Tag: `v0.11.1`

---

- Tarefa: 11.1.2
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDePoderes.java`
- Ação: Casos dos heróis: Ventos Revigorantes (cura 5%, 4 usos), Uivo Brutal (dano 12, 3 usos), Objetos Perdidos (dano 11, 3 usos).
- Commit: `feat: poderes dos herois no catalogo de poderes`
- Tag: `v0.11.2`

---

- Tarefa: 11.1.3
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDePoderes.java`
- Ação: Casos de Fogo: Chama Dourada (11), Rolo de Fogo (12), Galope Infernal (15), todos com 3 usos.
- Commit: `feat: poderes do fogo no catalogo de poderes`
- Tag: `v0.11.3`

---

- Tarefa: 11.1.4
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDePoderes.java`
- Ação: Casos de Planta: Pegadas Invertidas (10), Cipó Enrolante (11), Uivo da Mata (16), todos com 3 usos.
- Commit: `feat: poderes da planta no catalogo de poderes`
- Tag: `v0.11.4`

---

- Tarefa: 11.1.5
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/CatalogoDePoderes.java`
- Ação: Casos de Água: Águas Curativas (cura 10%, 2 usos), Redemoinho Rosado (dano 11, 3 usos), Maré Fúria (dano 17, 3 usos).
- Commit: `feat: poderes da agua no catalogo de poderes`
- Tag: `v0.11.5`

---

- Tarefa: 11.1.6
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: `public Map<String,String> exportarEstado()` com chaves `nome`, `pv_atual`, `pv_maximo`, `ataque`, `defesa`, `velocidade`, `xp`, `slots`, `equipadas` (nomes separados por `;`) e `liberados` (idem).
- Commit: `feat: exportacao do estado do personagem`
- Tag: `v0.11.6`

---

- Tarefa: 11.1.7
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: `public void restaurarEstado(Map<String,String> dados)`: aplicar PV, ataque, defesa, velocidade, XP e slots com validação (`pv_atual <= pv_maximo`).
- Commit: `feat: restauracao dos atributos numericos do personagem`
- Tag: `v0.11.7`

---

- Tarefa: 11.1.8
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/Personagem.java`
- Ação: Em `restaurarEstado`, limpar `habilidades` e `poderesLiberados` e reconstruí-las via `CatalogoDePoderes.criarPorNome` a partir de `equipadas` e `liberados`.
- Commit: `feat: restauracao de poderes e habilidades equipadas`
- Tag: `v0.11.8`

---

- Tarefa: 11.1.9
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC modelo/CatalogoDeHerois.java`
- Ação: Adicionar `static Personagem criarPorNome(String nome)` retornando nova instância do inicial correspondente (exceção se desconhecido).
- Commit: `feat: criacao de heroi por nome para o carregamento`
- Tag: `v0.11.9`

---

- Tarefa: 11.1.10
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `public Map<String,String> exportarEstado()` com `ginasios_vencidos`, `indice_titular`, `total_membros` e, para cada membro i, as chaves de `exportarEstado()` prefixadas por `membro.<i>.`.
- Commit: `feat: exportacao do estado da sessao de jogo`
- Tag: `v0.11.10`

---

- Tarefa: 11.1.11
- Responsável: Ryan Amorim
- Arquivo(s) Alvo: `SRC progressao/SessaoDeJogo.java`
- Ação: `public static SessaoDeJogo restaurar(Map<String,String> dados)`: recriar membros por nome, aplicar `restaurarEstado`, definir titular e `ginasiosVencidos`; `encontrosVencidos = 0`.
- Commit: `feat: restauracao da sessao de jogo a partir do estado salvo`
- Tag: `v0.11.11`

---

### 11.2 Motor de arquivos de save (Guilherme Aparecido)

- Tarefa: 11.2.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: Criar classe com constantes `PASTA = saves`, `ARQUIVO = saves/save1.properties` e `VERSAO = 1`.
- Commit: `feat: esqueleto do gerenciador de save`
- Tag: `v0.11.12`

---

- Tarefa: 11.2.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: `static boolean salvar(Map<String,String> dados)`: criar a pasta se não existir, copiar para `Properties` e gravar com `store` usando `OutputStreamWriter` UTF-8.
- Commit: `feat: gravacao do arquivo de save`
- Tag: `v0.11.13`

---

- Tarefa: 11.2.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: `static Optional<Map<String,String>> carregar()`: ler com `Properties.load` (UTF-8) e converter para `Map`; vazio se o arquivo não existir.
- Commit: `feat: leitura do arquivo de save`
- Tag: `v0.11.14`

---

- Tarefa: 11.2.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: `static boolean existeSave()` verificando `Files.exists`.
- Commit: `feat: verificacao da existencia de save`
- Tag: `v0.11.15`

---

- Tarefa: 11.2.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: Capturar `IOException` em `salvar` (log em `System.err`, retorna `false`) e em `carregar` (retorna vazio).
- Commit: `feat: tratamento de erros de leitura e gravacao do save`
- Tag: `v0.11.16`

---

- Tarefa: 11.2.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC save/GerenciadorDeSave.java`
- Ação: `salvar` grava `versao_save = VERSAO`; `carregar` retorna vazio se a versão do arquivo for diferente.
- Commit: `feat: versionamento do arquivo de save`
- Tag: `v0.11.17`

---

### 11.3 Integração de save e load na interface (Guilherme Aparecido)

- Tarefa: 11.3.1
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Criar e desenhar `botaoSalvar` (40, 470, 200x45, «Salvar jogo»).
- Commit: `feat: layout do botao salvar jogo`
- Tag: `v0.11.18`

---

- Tarefa: 11.3.2
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Clique: `GerenciadorDeSave.salvar(sessao.exportarEstado())` e exibir «Jogo salvo!» ou «Erro ao salvar.» por 2 segundos.
- Commit: `feat: evento do botao salvar jogo`
- Tag: `v0.11.19`

---

- Tarefa: 11.3.3
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaSpawnMedico.java`
- Ação: Salvar automaticamente uma vez ao abrir a tela (no construtor), sem mensagem.
- Commit: `feat: salvamento automatico ao entrar no spawn medico`
- Tag: `v0.11.20`

---

- Tarefa: 11.3.4
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Ação do `botaoContinuar`: `carregar()`; se presente, `SessaoDeJogo.restaurar(dados)` e abrir `TelaSpawnMedico`.
- Commit: `feat: logica de load vinculada ao botao continuar`
- Tag: `v0.11.21`

---

- Tarefa: 11.3.5
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Desabilitar o `botaoContinuar` quando `!GerenciadorDeSave.existeSave()`.
- Commit: `feat: bloqueio do botao continuar sem save existente`
- Tag: `v0.11.22`

---

- Tarefa: 11.3.6
- Responsável: Guilherme Aparecido
- Arquivo(s) Alvo: `SRC ui/telas/TelaInicial.java`
- Ação: Capturar exceções da restauração e exibir «Não foi possível carregar o jogo salvo.» sem fechar o jogo.
- Commit: `fix: mensagem de erro ao carregar save invalido`
- Tag: `v0.11.23`

---
