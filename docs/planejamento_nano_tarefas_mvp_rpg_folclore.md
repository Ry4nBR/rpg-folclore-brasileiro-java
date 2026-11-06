# Planejamento de Nano-Tarefas do MVP — RPG de Batalhas do Folclore Brasileiro

**Equipe:** Ryan Amorim (Back-end, POO, Lógica de Batalha) e Guilherme Aparecido (Front-end, UI, Telas)
**Documento de referência:** *Planejamento de Nano-Tarefas — RPG de Batalhas do Folclore Brasileiro* (projeto completo). Este documento é derivado dele e **não o substitui nem o altera**.
**Idioma do jogo:** exclusivamente Português do Brasil (PT-BR). Os textos ficam escritos nas próprias classes de interface.

---

## 1. Apresentação e objetivo do MVP

Este MVP é um protótipo executável e mínimo, criado para validar três coisas antes de investir no jogo completo:

1. A **arquitetura em POO** (Abstração, Encapsulamento, Herança e Polimorfismo) sustenta o combate sem improvisos.
2. O **sistema de batalha por turnos** funciona de ponta a ponta, com vitória e derrota.
3. A **interface Swing** consegue conduzir o jogador do menu até o resultado do confronto.

O protótipo tem um único fluxo: **Menu → Seleção de personagem → Batalha contra o chefe → Resultado → Menu**.

## 2. Diferenças entre o projeto completo e o MVP

| Aspecto | Projeto completo | MVP |
|---|---|---|
| Ginásios | 3 (Fogo, Planta, Água) | 1 (Fogo) |
| Personagens jogáveis | 3 iniciais + recrutamento | 2 (Saci e Lobisomem) |
| Inimigos | 9 criaturas (6 comuns + 3 chefes) | 1 chefe (Mula sem Cabeça) |
| Atributos | PV, Ataque, Defesa, Velocidade | PV, Ataque, Defesa |
| Habilidades | Slots, poderes liberados, troca | 1 habilidade fixa por combatente |
| Progressão | XP, melhoria, slots por ginásio | Apenas concluir o ginásio |
| Persistência | Save e Continuar | Nenhuma |
| Ações de batalha | Atacar, Habilidade, Ver status, Fugir | Atacar e Habilidade |
| IA | Pacote de IA com regras | Regra simples no próprio chefe |
| Visual e áudio | Sprites, retratos, animações, BGM, SFX | Formas desenhadas em Java2D; sem áudio |

## 3. Escopo funcional

- Tela inicial com **Jogar** e **Sair**.
- Seleção entre **Saci** (Vento) e **Lobisomem** (Físico), exibindo PV, Ataque, Defesa e habilidade.
- Batalha por turnos contra a **Mula sem Cabeça** (chefe do Ginásio do Fogo).
- Ações do jogador: **Atacar** e **Habilidade** (com limite de usos).
- Chefe age sozinho por regra simples e determinística.
- Mensagens de combate exibidas em caixa de texto.
- Detecção de **vitória** e **derrota**.
- Tela de resultado com retorno ao menu e, na derrota, opção de tentar novamente.
- Marcação de **Ginásio do Fogo concluído** ao vencer o chefe.

## 4. Funcionalidades excluídas

Múltiplos ginásios, múltiplos chefes, equipe, recrutamento, troca de personagem, XP e melhorias, níveis, desbloqueio de habilidades, poderes equipáveis e slots, save e load, fuga, ação Ver status, atributo Velocidade, vantagens de tipo, múltiplos encontros, exploração, spawn médico, diálogos e roteiro, créditos, sprites, retratos, animações, áudio e qualquer idioma além de PT-BR.

## 5. Tecnologias e arquitetura

- **Java 17**, **Swing/Java2D**, **Maven**, sem bibliotecas externas.
- Arquitetura em camadas simples, proporcional ao MVP:
  - `modelo`: entidades (`Personagem` e subclasses).
  - `habilidades`: contrato e tipos de habilidade.
  - `batalha`: regras e estados do combate.
  - `progressao`: sessão mínima do ginásio.
  - `ui`, `ui/componentes`, `ui/telas`: interface.
  - `demo`: validações de console, sem interface.
- A interface **só lê** o domínio por getters e chama métodos públicos de `Batalha`. Nenhuma regra de combate fica dentro de classes de tela.

## 6. Estrutura inicial de diretórios

Atalho usado em todo o documento: `SRC` = `src/main/java/br/folclore/`.

```
rpg-folclore/
├── pom.xml
├── README.md
├── .gitignore
├── docs/
│   ├── mvp-escopo.md
│   ├── diagrama-classes-mvp.md
│   ├── pilares-poo-mvp.md
│   └── checklist-validacao-mvp.md
└── src/main/java/br/folclore/
    ├── Main.java
    ├── modelo/          (Tipo, Personagem, Saci, Lobisomem, MulaSemCabeca, CatalogoDeHerois)
    ├── habilidades/     (ResultadoAcao, Habilidade, HabilidadeBase, HabilidadeDano, HabilidadeCura)
    ├── batalha/         (EstadoBatalha, Batalha, ResultadoBatalha)
    ├── progressao/      (SessaoMvp)
    ├── demo/            (DemoPilares, DemoBatalhaVitoria, DemoBatalhaDerrota)
    └── ui/
        ├── ConfiguracoesUi, JanelaPrincipal, PainelDoJogo, GerenciadorDeTelas
        ├── componentes/ (Botao, BarraDeVida, CaixaDeMensagens)
        └── telas/       (Tela, TelaInicial, TelaSelecaoPersonagem, TelaBatalha, TelaResultado)
```

## 7. Decisões Técnicas e Simplificações do MVP

**D1 — Seleção de personagem.** O jogador escolhe 1 entre 2 personagens e o usa até o fim. Não existe equipe, troca ou recrutamento. *Motivo:* basta para provar herança e polimorfismo com duas subclasses reais.

**D2 — Atributos iniciais.** Somente PV, Ataque e Defesa (a Velocidade foi removida porque a ordem de turnos é fixa e ela não teria efeito).

| Combatente | Tipo | PV | Ataque | Defesa |
|---|---|---|---|---|
| Saci | Vento | 60 | 10 | 6 |
| Lobisomem | Físico | 70 | 12 | 7 |
| Mula sem Cabeça (chefe) | Fogo | 55 | 9 | 5 |

O enum `Tipo` existe apenas como rótulo exibido. **Não há vantagem ou desvantagem de tipo.**

**D3 — Habilidades.** Cada combatente tem ataque básico (usos ilimitados) e **1 habilidade** com usos limitados.

| Combatente | Ataque básico (poder) | Habilidade | Efeito | Usos |
|---|---|---|---|---|
| Saci | Rasteira de Vento (6) | Ventos Revigorantes | Cura 20% do PV máximo | 3 |
| Lobisomem | Garra Selvagem (7) | Uivo Brutal | Dano com poder 12 | 2 |
| Mula sem Cabeça | Coice Flamejante (5) | Galope Infernal | Dano com poder 10 | 2 |

*Simplificação registrada:* a cura do Saci passa de 5% (projeto completo) para 20% para que o efeito seja visível e testável na barra de vida (12 PV em vez de 3).

**D4 — Cálculo de dano.** `dano = max(1, ataque do atacante + poder − defesa do alvo)`. Dano mínimo de 1. A cura é `max(1, PV máximo × percentual / 100)` (divisão inteira), limitada ao PV máximo.

**D5 — Ordem dos turnos.** Fixa: jogador age, depois o chefe. Se o chefe cair a 0 PV, ele **não** age naquele turno.

**D6 — Habilidade sem usos.** A ação é recusada com mensagem e **não consome o turno**.

**D7 — Comportamento do chefe.** Em turno **par**, se a Galope Infernal tiver usos, ele a usa; caso contrário usa o ataque básico. A regra vive no próprio `MulaSemCabeca` (polimorfismo), sem pacote de IA.

**D8 — Condição de vitória.** PV do chefe chega a 0.

**D9 — Condição de derrota.** PV do jogador chega a 0 (verificada após o turno do chefe).

**D10 — Encerramento do ginásio.** Vencer o chefe marca `ginasioConcluido = true` na `SessaoMvp`, mostra «Ginásio do Fogo concluído!» e permite voltar ao menu. Na derrota, o jogador pode **tentar novamente** (nova batalha, PV cheio, chefe novo) ou voltar ao menu.

**D11 — Visual.** Combatentes são retângulos coloridos com o nome (Java2D). Nenhum arquivo de imagem ou áudio é necessário.

**D12 — Balanceamento esperado (conferido à mão).** Saci vence a Mula em 5 turnos terminando com 18 PV; Lobisomem vence em 4 turnos. Derrota ocorre se o jogador desperdiçar turnos.

## 8. Distribuição de responsabilidades

**Ryan Amorim:** entidades, regras de negócio, cálculo de dano, lógica de batalha, comportamento do chefe, sessão do ginásio, demos de console, documentação técnica, build e release.

**Guilherme Aparecido:** janela, telas, componentes visuais, navegação, integração da interface com `Batalha`, validação funcional e README.

**Regra de ouro:** a interface (Guilherme) só consome métodos públicos entregues e integrados pelo Ryan. **Nenhuma tarefa de um modifica arquivos do outro**, exceto onde a tarefa indicar explicitamente.

## 9. Fluxo de Git e versionamento

### 9.1 Branch do MVP

O MVP vive em uma branch própria, criada a partir do estado atual de `dev`, que **nunca recebe merge do MVP**.

```bash
git checkout dev
git pull origin dev
git checkout -b mvp/prototipo
git push -u origin mvp/prototipo
```

**Branch recomendada para iniciar o MVP: `mvp/prototipo`.**

Regras:
- `dev` e `main` não recebem nenhum commit, merge ou tag do MVP.
- Todo Pull Request do MVP tem como **base `mvp/prototipo`**. Conferir a base antes de abrir o PR.

### 9.2 Ciclo de cada nano-tarefa (1 tarefa = 1 commit = 1 tag)

```bash
# 1. atualizar a base
git checkout mvp/prototipo && git pull origin mvp/prototipo

# 2. branch curta da tarefa (ex.: tarefa 1.1.2 do Ryan)
git checkout -b mvp/ryan-1.1.2

# 3. implementar só o que a tarefa pede, validar (mvn -q compile e a validação da tarefa)

# 4. commit único
git add <arquivos-alvo>
git commit -m "<mensagem do campo Commit>"
git push -u origin mvp/ryan-1.1.2

# 5. abrir PR com base mvp/prototipo; o outro integrante revisa;
#    mesclar com "Rebase and merge" ou "Squash and merge" (resultado: 1 commit)

# 6. tag anotada no commit resultante, em PT-BR
git checkout mvp/prototipo && git pull origin mvp/prototipo
git tag -a v0.1.2 -m "Concluída a tarefa 1.1.2: campos e encapsulamento de Personagem"
git push origin v0.1.2
```

### 9.3 Padrão de commits e tags

- **Commits:** Conventional Commits (`feat`, `fix`, `refactor`, `docs`, `chore`, `build`, `test`), mensagem em PT-BR sem acentos, no imperativo curto.
- **Tags:** `v0.<fase>.<número>`, anotadas, com mensagem em PT-BR iniciando por «Concluída a tarefa X.Y.Z: …». Contador reinicia em 1 a cada fase. A versão final é `v1.0.0`.

### 9.4 Colisão de tags com o projeto completo

Tags Git são **globais ao repositório**. Se o projeto completo já usa tags como `v0.1.1` no mesmo repositório, elas colidirão com as do MVP. Soluções, em ordem de preferência:

1. Usar um repositório separado (clone) para o MVP, mantendo as tags exatamente como neste documento.
2. Se for o mesmo repositório, acrescentar o prefixo `mvp-` a todas as tags (ex.: `mvp-v0.1.2`, final `mvp-v1.0.0`). A troca é mecânica e não altera nenhuma outra regra.

Antes da tarefa 0.1.1, executar `git tag -l "v0.*"` e decidir. A decisão deve ser registrada em `docs/mvp-escopo.md`.

### 9.5 Correção de tarefas já concluídas

- Nunca mover, apagar ou reescrever tag já publicada.
- A correção é uma **nova tarefa curta**: branch `mvp/fix-<id>`, commit `fix: ...`, PR para `mvp/prototipo` e tag `<tag-original>-fix1` (próxima correção: `-fix2`). Exemplo: `v0.1.2-fix1`.
- Se a correção mudar assinatura pública, avisar o outro desenvolvedor no PR e atualizar as tarefas dependentes.

### 9.6 Validação de cada entrega

1. `mvn -q compile` sem erros.
2. Executar a validação descrita na tarefa.
3. Revisão cruzada no PR (Ryan revisa Guilherme e vice-versa).
4. Só então criar a tag.

---
## 10. Planejamento de fases, blocos e nano-tarefas

Convenções: `SRC` = `src/main/java/br/folclore/`. Todas as tarefas assumem a branch `mvp/prototipo` como base (seção 9). Tags escritas sem prefixo; aplicar `mvp-` se a seção 9.4 exigir.

---

## Fase 0 — Preparação do ambiente e estrutura inicial

### Bloco 0.1 — Branch, build e esqueleto

### Tarefa 0.1.1
- **ID:** `0.1.1`
- **Título:** Criar a branch do MVP e registrar o escopo
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** Nenhum.
- **Arquivos-alvo:** `docs/mvp-escopo.md`
- **Objetivo:** Isolar o desenvolvimento do MVP e deixar o escopo e a política de tags registrados no repositório.
- **Procedimento de execução:**
  1. Rodar `git tag -l "v0.*"` e decidir o prefixo de tags conforme a seção 9.4.
  2. Executar os comandos da seção 9.1 para criar e publicar `mvp/prototipo` a partir de `dev`.
  3. Criar `docs/mvp-escopo.md` com: objetivo do MVP, tabela de diferenças (seção 2), lista de exclusões (seção 4), decisões D1 a D12 (seção 7) e a decisão sobre prefixo de tags.
  4. Criar a branch `mvp/ryan-0.1.1`, commitar, abrir PR com base `mvp/prototipo` e mesclar.
- **Regras técnicas:** Nenhum PR com base `dev` ou `main`. O documento não altera a documentação original.
- **Critérios de aceite:** `mvp/prototipo` existe no remoto; `docs/mvp-escopo.md` existe nela e não existe em `dev`.
- **Validação:** `git branch -r` lista `origin/mvp/prototipo`; `git diff dev..mvp/prototipo --stat` mostra só o novo documento.
- **Commit:** `docs: registro do escopo e da branch do mvp`
- **Tag:** `v0.0.1` — «Concluída a tarefa 0.1.1: branch e escopo do MVP registrados»

### Tarefa 0.1.2
- **ID:** `0.1.2`
- **Título:** Configurar Maven (Java 17) e `.gitignore`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 0.1.1 integrada.
- **Arquivos-alvo:** `pom.xml`, `.gitignore`
- **Objetivo:** Ter um build Maven mínimo e sem dependências externas.
- **Procedimento de execução:**
  1. Criar (ou ajustar, se já veio de `dev`) o `pom.xml` com `groupId` `br.folclore`, `artifactId` `rpg-folclore`, `version` `1.0.0-MVP`, `maven.compiler.release` = 17 e `project.build.sourceEncoding` = UTF-8.
  2. Adicionar `exec-maven-plugin` com `mainClass` `br.folclore.Main`.
  3. Garantir no `.gitignore`: `target/`, `out/`, `.idea/`, `*.iml`, `.vscode/`, `*.class`.
- **Regras técnicas:** Nenhuma tag `<dependency>`. Não introduzir plugins além do exec.
- **Critérios de aceite:** `mvn -q validate` termina sem erro; nenhum diretório gerado aparece em `git status`.
- **Validação:** Rodar `mvn -q validate` e depois `git status`.
- **Commit:** `chore: configuracao do maven java 17 e gitignore do mvp`
- **Tag:** `v0.0.2` — «Concluída a tarefa 0.1.2: build Maven e gitignore»

### Tarefa 0.1.3
- **ID:** `0.1.3`
- **Título:** Criar o ponto de entrada e a estrutura de pacotes
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 0.1.2 integrada.
- **Arquivos-alvo:** `SRC Main.java`; `.gitkeep` em `SRC modelo/`, `SRC habilidades/`, `SRC batalha/`, `SRC progressao/`, `SRC demo/`, `SRC ui/componentes/`, `SRC ui/telas/`
- **Objetivo:** Deixar o projeto compilando e executando, com os pacotes do MVP já criados.
- **Procedimento de execução:**
  1. Criar `Main` com `public static void main(String[] args)` que imprime `RPG do Folclore Brasileiro (MVP) - iniciando`.
  2. Criar os pacotes listados, cada um com um `.gitkeep`.
  3. Se a branch trouxer pacotes do projeto completo que o MVP não usa (`ia`, `save`, `audio`, `ui/efeitos`), removê-los com `git rm -r`.
- **Regras técnicas:** `Main` não deve conter lógica de jogo.
- **Critérios de aceite:** Projeto compila e imprime a mensagem; só existem pacotes do MVP.
- **Validação:** `mvn -q compile exec:java` imprime a mensagem.
- **Commit:** `chore: ponto de entrada e estrutura de pacotes do mvp`
- **Tag:** `v0.0.3` — «Concluída a tarefa 0.1.3: Main e pacotes do MVP»

### Bloco 0.2 — Ambiente do segundo desenvolvedor

### Tarefa 0.2.1
- **ID:** `0.2.1`
- **Título:** Validar o ambiente e documentar requisitos no README
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 0.1.3 integrada.
- **Arquivos-alvo:** `README.md`
- **Objetivo:** Confirmar que o segundo desenvolvedor consegue clonar, compilar e executar o MVP.
- **Procedimento de execução:**
  1. Clonar o repositório, executar `git checkout mvp/prototipo` e rodar `java -version` e `mvn -version`.
  2. Executar `mvn -q compile exec:java` e conferir a mensagem do `Main`.
  3. No `README.md`, criar a seção «MVP»: descrição de uma linha, aviso «Jogo em Português do Brasil», requisitos (JDK 17, Maven 3.8+) e comando de execução.
- **Regras técnicas:** Não editar arquivos de código.
- **Critérios de aceite:** README contém requisitos e comando; build executou na máquina do Guilherme.
- **Validação:** Outra pessoa segue só o README e executa o projeto com sucesso.
- **Commit:** `docs: requisitos e execucao do mvp no readme`
- **Tag:** `v0.0.4` — «Concluída a tarefa 0.2.1: ambiente validado e README do MVP»

---

## Fase 1 — Modelagem das entidades e conceitos fundamentais de POO

### Bloco 1.1 — Personagem (Abstração e Encapsulamento)

### Tarefa 1.1.1
- **ID:** `1.1.1`
- **Título:** Criar o enum `Tipo`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 0.1.3 integrada.
- **Arquivos-alvo:** `SRC modelo/Tipo.java`
- **Objetivo:** Ter os rótulos de tipo em PT-BR para exibição.
- **Procedimento de execução:**
  1. Criar `public enum Tipo` com `VENTO`, `FISICO` e `FOGO`.
  2. Adicionar `private final String rotulo`, construtor privado e `getRotulo()`.
  3. Rótulos: `Vento`, `Físico`, `Fogo`.
- **Regras técnicas:** Sem lógica de vantagem de tipo (decisão D2).
- **Critérios de aceite:** Os três valores existem e retornam o rótulo correto.
- **Validação:** `mvn -q compile`; em `jshell` com o classpath `target/classes`, `Tipo.FISICO.getRotulo()` retorna `Físico`.
- **Commit:** `feat: criacao do enum tipo com rotulos em portugues`
- **Tag:** `v0.1.1` — «Concluída a tarefa 1.1.1: enum Tipo»

### Tarefa 1.1.2
- **ID:** `1.1.2`
- **Título:** Criar `Personagem` com atributos encapsulados
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.1 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Estabelecer a classe base abstrata com PV, Ataque e Defesa protegidos por encapsulamento.
- **Procedimento de execução:**
  1. Criar `public abstract class Personagem` com Javadoc «Abstração: base comum de todo combatente».
  2. Campos privados: `final String nome`, `final Tipo tipo`, `int pvMaximo`, `int pvAtual`, `final int ataque`, `final int defesa`.
  3. Construtor `protected Personagem(String nome, Tipo tipo, int pvMaximo, int ataque, int defesa)`: lançar `IllegalArgumentException` se nome vazio, tipo nulo, `pvMaximo <= 0` ou ataque/defesa negativos; iniciar `pvAtual = pvMaximo`.
  4. Getters públicos para todos os campos. Nenhum setter.
- **Regras técnicas:** Campos `private`; nenhuma lógica de combate nesta tarefa. A classe ainda não precisa de método abstrato.
- **Critérios de aceite:** Compila; valores inválidos lançam exceção; não há setters.
- **Validação:** Em um `main` temporário (não commitado), criar uma subclasse anônima válida e outra com `pvMaximo = 0` e confirmar a exceção.
- **Commit:** `feat: classe abstrata personagem com atributos encapsulados`
- **Tag:** `v0.1.2` — «Concluída a tarefa 1.1.2: Personagem com PV, Ataque e Defesa»

### Tarefa 1.1.3
- **ID:** `1.1.3`
- **Título:** Implementar `estaVivo` e `receberDano`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.2 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Permitir que o personagem perca vida de forma controlada.
- **Procedimento de execução:**
  1. Adicionar `public boolean estaVivo()` retornando `pvAtual > 0`.
  2. Adicionar `public int receberDano(int dano)`: lançar `IllegalArgumentException` se `dano < 0`; `pvAtual = Math.max(0, pvAtual - dano)`; retornar o dano efetivamente aplicado.
- **Regras técnicas:** O PV nunca fica negativo; o campo continua privado.
- **Critérios de aceite:** Dano de 999 em personagem de 60 PV deixa 0 PV, retorna 60 e `estaVivo()` é `false`.
- **Validação:** `main` temporário com os três casos: dano 10, dano 999, dano -1.
- **Commit:** `feat: metodos estaVivo e receberDano em personagem`
- **Tag:** `v0.1.3` — «Concluída a tarefa 1.1.3: estaVivo e receberDano»

### Tarefa 1.1.4
- **ID:** `1.1.4`
- **Título:** Implementar `curar` e `curarPercentualDaVidaMaxima`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.3 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Permitir recuperação de vida respeitando o limite máximo.
- **Procedimento de execução:**
  1. Adicionar `public int curar(int quantidade)`: lançar `IllegalArgumentException` se negativa; `pvAtual = Math.min(pvMaximo, pvAtual + quantidade)`; retornar o valor efetivamente curado.
  2. Adicionar `public int curarPercentualDaVidaMaxima(int percentual)`: `valor = Math.max(1, pvMaximo * percentual / 100)`; delegar a `curar(valor)`.
- **Regras técnicas:** Divisão inteira (decisão D4); nunca ultrapassar `pvMaximo`.
- **Critérios de aceite:** Com 60 PV máx. e 40 atuais, `curarPercentualDaVidaMaxima(20)` cura 12 e deixa 52; com 58 atuais cura apenas 2.
- **Validação:** `main` temporário com esses dois casos.
- **Commit:** `feat: metodos curar e curarPercentualDaVidaMaxima em personagem`
- **Tag:** `v0.1.4` — «Concluída a tarefa 1.1.4: cura com limite de PV»

### Tarefa 1.1.5
- **ID:** `1.1.5`
- **Título:** Implementar `calcularDano`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.2 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Centralizar a fórmula de dano em um único método.
- **Procedimento de execução:**
  1. Adicionar `public int calcularDano(Personagem alvo, int poderBase)` retornando `Math.max(1, getAtaque() + poderBase - alvo.getDefesa())`.
  2. Adicionar Javadoc com a fórmula da decisão D4.
- **Regras técnicas:** O método **só calcula**; não aplica dano ao alvo. Deve ser `public`, pois habilidades de outro pacote o utilizam.
- **Critérios de aceite:** Atacante com ataque 10 e poder 6 contra defesa 5 retorna 11; contra defesa 50 retorna 1.
- **Validação:** `main` temporário com os dois casos.
- **Commit:** `feat: criacao do metodo calcularDano na classe personagem`
- **Tag:** `v0.1.5` — «Concluída a tarefa 1.1.5: cálculo de dano»

### Tarefa 1.1.6
- **ID:** `1.1.6`
- **Título:** Declarar `atacar` e `getNomeAtaqueBasico` abstratos e criar `resumoDeStatus`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.5 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Definir o contrato que cada subclasse cumpre de forma própria (base do polimorfismo).
- **Procedimento de execução:**
  1. Declarar `public abstract int atacar(Personagem alvo)` (retorna o dano causado), com Javadoc.
  2. Declarar `public abstract String getNomeAtaqueBasico()`.
  3. Adicionar `public String resumoDeStatus()` retornando, por exemplo, `Saci (Vento) | PV 60/60 | ATQ 10 | DEF 6`.
- **Regras técnicas:** Métodos abstratos sem corpo; o resumo usa apenas getters.
- **Critérios de aceite:** Compila; uma subclasse anônima é obrigada a implementar os dois métodos.
- **Validação:** `main` temporário: criar subclasse anônima e imprimir `resumoDeStatus()`.
- **Commit:** `feat: metodos abstratos de ataque e resumo de status em personagem`
- **Tag:** `v0.1.6` — «Concluída a tarefa 1.1.6: contrato abstrato de ataque»

### Bloco 1.2 — Habilidades (Polimorfismo)

### Tarefa 1.2.1
- **ID:** `1.2.1`
- **Título:** Criar `ResultadoAcao` e a interface `Habilidade`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.1.6 integrada.
- **Arquivos-alvo:** `SRC habilidades/ResultadoAcao.java`, `SRC habilidades/Habilidade.java`
- **Objetivo:** Definir o contrato comum de habilidades e o retorno padronizado de uma ação.
- **Procedimento de execução:**
  1. Criar `public final class ResultadoAcao` com `enum Efeito { DANO, CURA, FALHA }`, campos finais `efeito`, `valor` e `mensagem`, construtor e getters.
  2. Criar `public interface Habilidade` com `String getNome()`, `int getUsosMaximos()`, `int getUsosRestantes()`, `boolean temUsosRestantes()` e `ResultadoAcao executar(Personagem usuario, Personagem alvo)`.
- **Regras técnicas:** `ResultadoAcao` é imutável.
- **Critérios de aceite:** Compila; `ResultadoAcao` não possui setters.
- **Validação:** `mvn -q compile` e criação de um `ResultadoAcao` em `main` temporário.
- **Commit:** `feat: criacao de resultadoAcao e da interface habilidade`
- **Tag:** `v0.1.7` — «Concluída a tarefa 1.2.1: contrato de habilidades»

### Tarefa 1.2.2
- **ID:** `1.2.2`
- **Título:** Criar `HabilidadeBase` com controle de usos
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.1 integrada.
- **Arquivos-alvo:** `SRC habilidades/HabilidadeBase.java`
- **Objetivo:** Reaproveitar nome e contagem de usos em todas as habilidades (Herança).
- **Procedimento de execução:**
  1. Criar `public abstract class HabilidadeBase implements Habilidade` com `private final String nome`, `private final int usosMaximos`, `private int usosRestantes`.
  2. Construtor `(String nome, int usosMaximos)`; validar `usosMaximos > 0`.
  3. Implementar `getNome`, `getUsosMaximos`, `getUsosRestantes` e `temUsosRestantes()`.
  4. Adicionar `protected void consumirUso()`: lança `IllegalStateException` se zero; senão decrementa.
- **Regras técnicas:** `executar` continua abstrato aqui.
- **Critérios de aceite:** `consumirUso` abaixo de zero lança exceção.
- **Validação:** Subclasse anônima em `main` temporário consumindo todos os usos e uma tentativa extra.
- **Commit:** `feat: classe abstrata habilidadeBase com controle de usos`
- **Tag:** `v0.1.8` — «Concluída a tarefa 1.2.2: base de habilidades»

### Tarefa 1.2.3
- **ID:** `1.2.3`
- **Título:** Implementar `HabilidadeDano`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.2 integrada.
- **Arquivos-alvo:** `SRC habilidades/HabilidadeDano.java`
- **Objetivo:** Ter uma habilidade que causa dano e consome usos.
- **Procedimento de execução:**
  1. Criar `public class HabilidadeDano extends HabilidadeBase` com `private final int poderBase` e construtor `(nome, usosMaximos, poderBase)`.
  2. Implementar `executar`: se `!temUsosRestantes()`, retornar `FALHA` com `Sem usos restantes de <nome>!`.
  3. Caso contrário: `consumirUso()`; `dano = usuario.calcularDano(alvo, poderBase)`; `efetivo = alvo.receberDano(dano)`; retornar `DANO` com valor `efetivo` e mensagem `<usuario> usou <habilidade>! <alvo> sofreu <n> de dano.`
- **Regras técnicas:** Não consumir uso quando retornar `FALHA`.
- **Critérios de aceite:** Habilidade com 2 usos funciona 2 vezes e na 3ª retorna `FALHA` sem alterar o alvo.
- **Validação:** `main` temporário com três execuções seguidas.
- **Commit:** `feat: implementacao da habilidade de dano`
- **Tag:** `v0.1.9` — «Concluída a tarefa 1.2.3: HabilidadeDano»

### Tarefa 1.2.4
- **ID:** `1.2.4`
- **Título:** Implementar `HabilidadeCura`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.2 integrada e 1.1.4 integrada.
- **Arquivos-alvo:** `SRC habilidades/HabilidadeCura.java`
- **Objetivo:** Ter uma habilidade que cura o próprio usuário, com comportamento distinto da de dano.
- **Procedimento de execução:**
  1. Criar `public class HabilidadeCura extends HabilidadeBase` com `private final int percentual` e construtor `(nome, usosMaximos, percentual)`.
  2. Implementar `executar`: sem usos retorna `FALHA`; senão `consumirUso()`, `curado = usuario.curarPercentualDaVidaMaxima(percentual)` e retorna `CURA` com mensagem `<usuario> usou <habilidade> e recuperou <n> de PV!`
- **Regras técnicas:** O alvo recebido é ignorado (cura o usuário).
- **Critérios de aceite:** Usuário com 60 PV máx. e 40 atuais, cura de 20%, fica com 52.
- **Validação:** `main` temporário com os casos de 40 e 58 PV atuais.
- **Commit:** `feat: implementacao da habilidade de cura`
- **Tag:** `v0.1.10` — «Concluída a tarefa 1.2.4: HabilidadeCura»

### Tarefa 1.2.5
- **ID:** `1.2.5`
- **Título:** Vincular uma habilidade ao `Personagem`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.3 e 1.2.4 integradas.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Permitir que cada combatente carregue sua habilidade e a use por um método único.
- **Procedimento de execução:**
  1. Adicionar `private Habilidade habilidade`, `public Habilidade getHabilidade()` e `protected void definirHabilidade(Habilidade h)` (rejeita nulo).
  2. Adicionar `public ResultadoAcao usarHabilidade(Personagem alvo)`: lança `IllegalStateException` se não houver habilidade; senão `return habilidade.executar(this, alvo)`.
- **Regras técnicas:** Somente subclasses definem a habilidade (`protected`).
- **Critérios de aceite:** Personagem de teste com `HabilidadeDano` ataca o alvo via `usarHabilidade`.
- **Validação:** `main` temporário com subclasse anônima chamando `usarHabilidade`.
- **Commit:** `feat: habilidade vinculada ao personagem com metodo usarHabilidade`
- **Tag:** `v0.1.11` — «Concluída a tarefa 1.2.5: habilidade no Personagem»

---

## Fase 2 — Mecânicas de batalha

### Bloco 2.1 — Estrutura da batalha

### Tarefa 2.1.1
- **ID:** `2.1.1`
- **Título:** Criar o enum `EstadoBatalha`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.5 integrada.
- **Arquivos-alvo:** `SRC batalha/EstadoBatalha.java`
- **Objetivo:** Representar explicitamente em que ponto o combate está.
- **Procedimento de execução:**
  1. Criar `public enum EstadoBatalha { ESCOLHA_DO_JOGADOR, TURNO_DO_CHEFE, VITORIA, DERROTA }`.
- **Regras técnicas:** Sem estados extras (não há fuga nem outras ações no MVP).
- **Critérios de aceite:** Compila com os quatro valores.
- **Validação:** `mvn -q compile`.
- **Commit:** `feat: enum de estados da batalha`
- **Tag:** `v0.2.1` — «Concluída a tarefa 2.1.1: estados da batalha»

### Tarefa 2.1.2
- **ID:** `2.1.2`
- **Título:** Criar `Batalha` com estado, turno e registro de mensagens
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.1.1 integrada.
- **Arquivos-alvo:** `SRC batalha/Batalha.java`
- **Objetivo:** Ter o objeto que guarda os combatentes, o estado e as mensagens do combate.
- **Procedimento de execução:**
  1. Criar `Batalha` com `Personagem jogador`, `Personagem chefe`, `EstadoBatalha estado = ESCOLHA_DO_JOGADOR`, `int turno = 1` e `List<String> mensagens`.
  2. Construtor `(Personagem jogador, Personagem chefe)`: rejeitar nulos e registrar `O chefe <nome> desafia você!`.
  3. Getters: `getJogador()`, `getChefe()`, `getEstado()`, `getTurno()`.
  4. `private void registrar(String msg)` e `public List<String> consumirMensagens()` (devolve cópia das pendentes e limpa a lista).
  5. Javadoc da classe: ordem de turno fixa, jogador depois chefe (decisão D5).
- **Regras técnicas:** A interface nunca altera listas internas; só usa `consumirMensagens()`.
- **Critérios de aceite:** Após criar a batalha, `consumirMensagens()` retorna 1 mensagem e uma segunda chamada retorna lista vazia.
- **Validação:** `main` temporário imprimindo duas chamadas consecutivas.
- **Commit:** `feat: criacao da classe batalha com estado turno e mensagens`
- **Tag:** `v0.2.2` — «Concluída a tarefa 2.1.2: estrutura da batalha»

### Bloco 2.2 — Turno do jogador

### Tarefa 2.2.1
- **ID:** `2.2.1`
- **Título:** Implementar a ação Atacar e a checagem de vitória
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.1.2 integrada.
- **Arquivos-alvo:** `SRC batalha/Batalha.java`
- **Objetivo:** Executar o ataque básico do jogador e decidir o que vem depois.
- **Procedimento de execução:**
  1. Criar `private void exigirEstado(EstadoBatalha esperado)` lançando `IllegalStateException` se diferente.
  2. Criar `public void acaoAtacar()`: exigir `ESCOLHA_DO_JOGADOR`; `dano = jogador.atacar(chefe)`; registrar `<jogador> usou <ataque básico> e causou <dano> de dano!`.
  3. Criar `private void aposAcaoDoJogador()`: se `!chefe.estaVivo()`, registrar `<chefe> caiu em combate!` e estado `VITORIA`; senão estado `TURNO_DO_CHEFE`.
  4. Chamar `aposAcaoDoJogador()` no fim de `acaoAtacar()`.
- **Regras técnicas:** Chefe derrotado não age (D5). O dano vem de `Personagem.atacar`, sem duplicar fórmula.
- **Critérios de aceite:** Um ataque reduz o PV do chefe e muda o estado para `TURNO_DO_CHEFE`; ataque fora do estado correto lança exceção.
- **Validação:** `main` temporário com um personagem anônimo (ataque fixo) contra chefe anônimo; imprimir estado e mensagens.
- **Commit:** `feat: acao atacar na batalha com verificacao de vitoria`
- **Tag:** `v0.2.3` — «Concluída a tarefa 2.2.1: ação Atacar»

### Tarefa 2.2.2
- **ID:** `2.2.2`
- **Título:** Implementar a ação Habilidade
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.2.1 integrada.
- **Arquivos-alvo:** `SRC batalha/Batalha.java`
- **Objetivo:** Executar a habilidade do jogador respeitando o limite de usos.
- **Procedimento de execução:**
  1. Criar `public boolean acaoHabilidade()`: exigir `ESCOLHA_DO_JOGADOR`.
  2. Se `jogador.getHabilidade()` não tem usos, registrar `Sem usos restantes de <nome>!` e retornar `false` **sem alterar estado**.
  3. Caso contrário, `resultado = jogador.usarHabilidade(chefe)`, registrar `resultado.getMensagem()`, chamar `aposAcaoDoJogador()` e retornar `true`.
- **Regras técnicas:** Decisão D6: recusa não consome turno. Serve para habilidades de dano e de cura sem `if` por tipo.
- **Critérios de aceite:** Com 1 uso restante, a 1ª chamada retorna `true`; na 2ª retorna `false` e o estado permanece `ESCOLHA_DO_JOGADOR`.
- **Validação:** `main` temporário usando `HabilidadeDano` de 1 uso, alternando com o turno do chefe simulado via ataque.
- **Commit:** `feat: acao habilidade na batalha com limite de usos`
- **Tag:** `v0.2.4` — «Concluída a tarefa 2.2.2: ação Habilidade»

### Bloco 2.3 — Turno do chefe e encerramento

### Tarefa 2.3.1
- **ID:** `2.3.1`
- **Título:** Criar o gancho `deveUsarHabilidade` em `Personagem`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.5 integrada.
- **Arquivos-alvo:** `SRC modelo/Personagem.java`
- **Objetivo:** Permitir que cada combatente decida, por polimorfismo, se usa a habilidade em um turno.
- **Procedimento de execução:**
  1. Adicionar `public boolean deveUsarHabilidade(int turno)` retornando `false`.
  2. Javadoc: sobrescrito pelo chefe (decisão D7).
- **Regras técnicas:** O método não executa nada; apenas decide.
- **Critérios de aceite:** Qualquer personagem comum retorna `false`.
- **Validação:** `mvn -q compile` e chamada em `main` temporário.
- **Commit:** `feat: gancho deveUsarHabilidade na classe personagem`
- **Tag:** `v0.2.5` — «Concluída a tarefa 2.3.1: gancho de decisão do turno»

### Tarefa 2.3.2
- **ID:** `2.3.2`
- **Título:** Implementar o turno do chefe e a checagem de derrota
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.2.2 e 2.3.1 integradas.
- **Arquivos-alvo:** `SRC batalha/Batalha.java`
- **Objetivo:** Fazer o chefe agir sozinho e fechar o ciclo do turno.
- **Procedimento de execução:**
  1. Criar `public void executarTurnoDoChefe()`: exigir `TURNO_DO_CHEFE`.
  2. Se `chefe.deveUsarHabilidade(turno)` e a habilidade tem usos, `resultado = chefe.usarHabilidade(jogador)` e registrar a mensagem; senão `dano = chefe.atacar(jogador)` e registrar `<chefe> usou <ataque básico> e causou <dano> de dano!`.
  3. Se `!jogador.estaVivo()`, registrar `<jogador> caiu em combate!` e estado `DERROTA`.
  4. Senão `turno++` e estado `ESCOLHA_DO_JOGADOR`.
- **Regras técnicas:** Sem `instanceof` e sem referência a classes concretas de chefe.
- **Critérios de aceite:** Após o turno do chefe com jogador vivo, o turno aumenta em 1 e o estado volta ao jogador; com jogador a 0 PV, estado `DERROTA`.
- **Validação:** `main` temporário com jogador de PV baixo e chefe anônimo.
- **Commit:** `feat: turno do chefe e verificacao de derrota na batalha`
- **Tag:** `v0.2.6` — «Concluída a tarefa 2.3.2: turno do chefe e derrota»

### Tarefa 2.3.3
- **ID:** `2.3.3`
- **Título:** Criar `ResultadoBatalha` e consulta de fim de combate
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.3.2 integrada.
- **Arquivos-alvo:** `SRC batalha/ResultadoBatalha.java`, `SRC batalha/Batalha.java`
- **Objetivo:** Dar à interface uma forma simples de saber se e como o combate terminou.
- **Procedimento de execução:**
  1. Criar `public enum ResultadoBatalha { VITORIA, DERROTA }`.
  2. Em `Batalha`, adicionar `public boolean estaEncerrada()` (estado `VITORIA` ou `DERROTA`) e `public ResultadoBatalha getResultado()` (mapeia o estado; `null` se em andamento).
- **Regras técnicas:** Sem lógica de interface nesses métodos.
- **Critérios de aceite:** Em andamento: `estaEncerrada()` falso e `getResultado()` nulo; ao fim, o valor correto.
- **Validação:** `main` temporário cobrindo os três momentos.
- **Commit:** `feat: resultado de batalha e consulta de encerramento`
- **Tag:** `v0.2.7` — «Concluída a tarefa 2.3.3: resultado da batalha»

---
## Fase 3 — Implementação dos dois personagens jogáveis (Herança)

### Bloco 3.1 — Saci

### Tarefa 3.1.1
- **ID:** `3.1.1`
- **Título:** Criar a classe `Saci` com status base e ataque básico
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.5 integrada.
- **Arquivos-alvo:** `SRC modelo/Saci.java`
- **Objetivo:** Ter o primeiro personagem jogável herdando de `Personagem`.
- **Procedimento de execução:**
  1. Criar `public class Saci extends Personagem`.
  2. Constantes `private static final`: `PV_BASE = 60`, `ATAQUE_BASE = 10`, `DEFESA_BASE = 6`, `PODER_ATAQUE_BASICO = 6`.
  3. Construtor público sem parâmetros: `super("Saci", Tipo.VENTO, PV_BASE, ATAQUE_BASE, DEFESA_BASE)`.
  4. `getNomeAtaqueBasico()` retorna `Rasteira de Vento`.
  5. `atacar(alvo)`: `return alvo.receberDano(calcularDano(alvo, PODER_ATAQUE_BASICO))`.
- **Regras técnicas:** Sem números soltos no corpo dos métodos; status só em constantes (decisões D2 e D3).
- **Critérios de aceite:** `new Saci().resumoDeStatus()` mostra `Saci (Vento) | PV 60/60 | ATQ 10 | DEF 6`.
- **Validação:** `main` temporário: Saci ataca outro Saci de defesa 6 e causa 10 de dano.
- **Commit:** `feat: implementacao do personagem saci com ataque basico`
- **Tag:** `v0.3.1` — «Concluída a tarefa 3.1.1: Saci»

### Tarefa 3.1.2
- **ID:** `3.1.2`
- **Título:** Adicionar a habilidade Ventos Revigorantes ao Saci
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 3.1.1 integrada.
- **Arquivos-alvo:** `SRC modelo/Saci.java`
- **Objetivo:** Dar ao Saci uma habilidade de cura.
- **Procedimento de execução:**
  1. No construtor, após o `super`, chamar `definirHabilidade(new HabilidadeCura("Ventos Revigorantes", 3, 20))`.
- **Regras técnicas:** Cura de 20% e 3 usos (decisão D3).
- **Critérios de aceite:** Saci com 40 PV usa a habilidade e fica com 52; após 3 usos, `temUsosRestantes()` é falso.
- **Validação:** `main` temporário: `saci.receberDano(20)`, `saci.usarHabilidade(saci)` e impressão do PV.
- **Commit:** `feat: habilidade ventos revigorantes do saci`
- **Tag:** `v0.3.2` — «Concluída a tarefa 3.1.2: Ventos Revigorantes»

### Bloco 3.2 — Lobisomem

### Tarefa 3.2.1
- **ID:** `3.2.1`
- **Título:** Criar a classe `Lobisomem` com status base e ataque básico
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 1.2.5 integrada.
- **Arquivos-alvo:** `SRC modelo/Lobisomem.java`
- **Objetivo:** Ter o segundo personagem jogável.
- **Procedimento de execução:**
  1. Criar `public class Lobisomem extends Personagem` com constantes `PV_BASE = 70`, `ATAQUE_BASE = 12`, `DEFESA_BASE = 7`, `PODER_ATAQUE_BASICO = 7`.
  2. Construtor: `super("Lobisomem", Tipo.FISICO, PV_BASE, ATAQUE_BASE, DEFESA_BASE)`.
  3. `getNomeAtaqueBasico()` retorna `Garra Selvagem`; `atacar(alvo)` igual ao padrão do Saci, com seu poder.
- **Regras técnicas:** Não copiar código do Saci por herança entre os dois; ambos herdam apenas de `Personagem`.
- **Critérios de aceite:** Resumo `Lobisomem (Físico) | PV 70/70 | ATQ 12 | DEF 7`; ataque contra defesa 5 causa 14.
- **Validação:** `main` temporário contra um `Saci`.
- **Commit:** `feat: implementacao do personagem lobisomem com ataque basico`
- **Tag:** `v0.3.3` — «Concluída a tarefa 3.2.1: Lobisomem»

### Tarefa 3.2.2
- **ID:** `3.2.2`
- **Título:** Adicionar a habilidade Uivo Brutal ao Lobisomem
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 3.2.1 integrada.
- **Arquivos-alvo:** `SRC modelo/Lobisomem.java`
- **Objetivo:** Dar ao Lobisomem uma habilidade de dano.
- **Procedimento de execução:**
  1. No construtor, `definirHabilidade(new HabilidadeDano("Uivo Brutal", 2, 12))`.
- **Regras técnicas:** Poder 12 e 2 usos (decisão D3).
- **Critérios de aceite:** Uivo Brutal contra defesa 5 causa 19; a 3ª tentativa retorna `FALHA`.
- **Validação:** `main` temporário com 3 usos seguidos.
- **Commit:** `feat: habilidade uivo brutal do lobisomem`
- **Tag:** `v0.3.4` — «Concluída a tarefa 3.2.2: Uivo Brutal»

### Bloco 3.3 — Catálogo

### Tarefa 3.3.1
- **ID:** `3.3.1`
- **Título:** Criar `CatalogoDeHerois`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 3.1.2 e 3.2.2 integradas.
- **Arquivos-alvo:** `SRC modelo/CatalogoDeHerois.java`
- **Objetivo:** Fornecer à interface a lista dos dois personagens sem que ela conheça as classes concretas.
- **Procedimento de execução:**
  1. Criar classe utilitária com `public static List<Personagem> criarTodos()` retornando novas instâncias de `Saci` e `Lobisomem`, nessa ordem.
  2. Cada chamada cria objetos **novos** (PV cheio e usos restaurados).
- **Regras técnicas:** Retorno do tipo `Personagem`, nunca de subclasse.
- **Critérios de aceite:** A lista tem 2 itens; duas chamadas retornam objetos distintos.
- **Validação:** `main` temporário comparando referências e imprimindo `resumoDeStatus()`.
- **Commit:** `feat: catalogo dos herois jogaveis`
- **Tag:** `v0.3.5` — «Concluída a tarefa 3.3.1: catálogo de heróis (entrega para a Fase 5)»

---

## Fase 4 — Implementação do chefe e confronto principal

### Bloco 4.1 — Mula sem Cabeça

### Tarefa 4.1.1
- **ID:** `4.1.1`
- **Título:** Criar `MulaSemCabeca` com status base e ataque básico
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 2.3.1 e 1.2.5 integradas.
- **Arquivos-alvo:** `SRC modelo/MulaSemCabeca.java`
- **Objetivo:** Ter o chefe do Ginásio do Fogo como mais uma subclasse de `Personagem`.
- **Procedimento de execução:**
  1. Criar `public class MulaSemCabeca extends Personagem` com `PV_BASE = 55`, `ATAQUE_BASE = 9`, `DEFESA_BASE = 5`, `PODER_ATAQUE_BASICO = 5`.
  2. Construtor: `super("Mula sem Cabeça", Tipo.FOGO, PV_BASE, ATAQUE_BASE, DEFESA_BASE)`.
  3. `getNomeAtaqueBasico()` retorna `Coice Flamejante`; `atacar(alvo)` no padrão dos heróis.
- **Regras técnicas:** Classe do chefe fica em `modelo`, sem dependência de `batalha` ou `ui`.
- **Critérios de aceite:** Contra defesa 6, o ataque básico causa 8.
- **Validação:** `main` temporário contra um `Saci`.
- **Commit:** `feat: implementacao do chefe mula sem cabeca com ataque basico`
- **Tag:** `v0.4.1` — «Concluída a tarefa 4.1.1: Mula sem Cabeça»

### Tarefa 4.1.2
- **ID:** `4.1.2`
- **Título:** Adicionar Galope Infernal e o comportamento do chefe
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 4.1.1 integrada.
- **Arquivos-alvo:** `SRC modelo/MulaSemCabeca.java`
- **Objetivo:** Implementar a habilidade do chefe e a regra de decisão por polimorfismo.
- **Procedimento de execução:**
  1. No construtor, `definirHabilidade(new HabilidadeDano("Galope Infernal", 2, 10))`.
  2. Sobrescrever `deveUsarHabilidade(int turno)`: `return turno % 2 == 0 && getHabilidade().temUsosRestantes()`.
- **Regras técnicas:** Regra determinística (decisão D7); sem aleatoriedade.
- **Critérios de aceite:** Turno 1 retorna `false`; turno 2 retorna `true`; após 2 usos, retorna `false` em qualquer turno.
- **Validação:** `main` temporário chamando `deveUsarHabilidade(1..6)` com usos sendo consumidos.
- **Commit:** `feat: habilidade galope infernal e decisao de turno do chefe`
- **Tag:** `v0.4.2` — «Concluída a tarefa 4.1.2: comportamento do chefe»

### Bloco 4.2 — Validações por console (sem interface)

### Tarefa 4.2.1
- **ID:** `4.2.1`
- **Título:** Criar `DemoPilares` para demonstrar o polimorfismo
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 4.1.2 e 3.3.1 integradas.
- **Arquivos-alvo:** `SRC demo/DemoPilares.java`
- **Objetivo:** Mostrar, no console, as três subclasses reagindo de forma diferente ao mesmo método.
- **Procedimento de execução:**
  1. `main` cria `List<Personagem>` com `Saci`, `Lobisomem` e `MulaSemCabeca`.
  2. Para cada um, imprimir `resumoDeStatus()`, o nome do ataque básico e o dano de `atacar` contra um `Lobisomem` novo.
  3. Para cada um, imprimir a mensagem de `usarHabilidade` (a do Saci será cura; as demais, dano).
- **Regras técnicas:** Nenhum `instanceof` ou `if` por tipo concreto.
- **Critérios de aceite:** Saída mostra ataques, números e mensagens diferentes por personagem.
- **Validação:** `mvn -q compile exec:java -Dexec.mainClass=br.folclore.demo.DemoPilares`.
- **Commit:** `test: demo de console dos pilares de poo`
- **Tag:** `v0.4.3` — «Concluída a tarefa 4.2.1: demo de polimorfismo»

### Tarefa 4.2.2
- **ID:** `4.2.2`
- **Título:** Criar `DemoBatalhaVitoria`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 4.2.1 integrada.
- **Arquivos-alvo:** `SRC demo/DemoBatalhaVitoria.java`
- **Objetivo:** Provar que a batalha completa termina em vitória com cada herói.
- **Procedimento de execução:**
  1. Para cada herói de `CatalogoDeHerois.criarTodos()`, criar `new Batalha(heroi, new MulaSemCabeca())`.
  2. Laço até `estaEncerrada()`: se o estado é `ESCOLHA_DO_JOGADOR`, chamar `acaoAtacar()`; se `TURNO_DO_CHEFE`, chamar `executarTurnoDoChefe()`; imprimir `consumirMensagens()` a cada passo.
  3. No fim, imprimir resultado, turnos e PV restante do herói; lançar `AssertionError` se o resultado não for `VITORIA`.
- **Regras técnicas:** O demo só usa a API pública de `Batalha`.
- **Critérios de aceite:** Saci vence em 5 turnos com 18 PV; Lobisomem vence em 4 turnos (valores da decisão D12).
- **Validação:** Executar a classe e conferir as duas vitórias e os números.
- **Commit:** `test: demo de batalha com vitoria dos dois herois`
- **Tag:** `v0.4.4` — «Concluída a tarefa 4.2.2: batalha validada (vitória)»

### Tarefa 4.2.3
- **ID:** `4.2.3`
- **Título:** Criar `DemoBatalhaDerrota`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 4.2.2 integrada.
- **Arquivos-alvo:** `SRC demo/DemoBatalhaDerrota.java`
- **Objetivo:** Provar a derrota e a recusa de habilidade sem usos.
- **Procedimento de execução:**
  1. Criar um `Saci`, chamar `receberDano(59)` (resta 1 PV) e iniciar a batalha contra `MulaSemCabeca`.
  2. Chamar `acaoAtacar()` e depois `executarTurnoDoChefe()`; confirmar resultado `DERROTA`.
  3. Em outra batalha, com um `Lobisomem`: `acaoHabilidade()` (esperado `true`), `executarTurnoDoChefe()`, `acaoHabilidade()` (esperado `true`), `executarTurnoDoChefe()` e uma terceira `acaoHabilidade()` (esperado `false`, estado ainda `ESCOLHA_DO_JOGADOR`).
- **Regras técnicas:** Reduzir PV apenas por `receberDano` (público), sem reflexão.
- **Critérios de aceite:** Demo imprime `DERROTA` e a mensagem `Sem usos restantes de Uivo Brutal!`.
- **Validação:** Executar a classe e conferir a saída.
- **Commit:** `test: demo de batalha com derrota e habilidade sem usos`
- **Tag:** `v0.4.5` — «Concluída a tarefa 4.2.3: derrota e limite de usos validados»

---

## Fase 5 — Construção das interfaces gráficas

Pode começar em paralelo à Fase 1 (tarefas 5.1.x e 5.2.1 só dependem da Fase 0). As tarefas 5.3.x exigem 3.3.1; 5.4.3 exige 2.3.3.

### Bloco 5.1 — Janela, telas e botões

### Tarefa 5.1.1
- **ID:** `5.1.1`
- **Título:** Criar a janela principal, o painel de desenho e o loop
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 0.2.1 integrada.
- **Arquivos-alvo:** `SRC ui/ConfiguracoesUi.java`, `SRC ui/JanelaPrincipal.java`, `SRC ui/PainelDoJogo.java`, `SRC Main.java` (única alteração autorizada em arquivo do Ryan)
- **Objetivo:** Abrir uma janela 960x540 com renderização contínua.
- **Procedimento de execução:**
  1. `ConfiguracoesUi` com constantes `LARGURA = 960`, `ALTURA = 540`, `FPS = 60`, `TITULO = "RPG do Folclore Brasileiro"`.
  2. `JanelaPrincipal extends JFrame`: título, `EXIT_ON_CLOSE`, `setResizable(false)`, adicionar o painel, `pack()` e `setLocationRelativeTo(null)`.
  3. `PainelDoJogo extends JPanel`: `setPreferredSize`, `setFocusable(true)`, double buffer; `paintComponent` pinta fundo preto e configura `Graphics2D` com `NEAREST_NEIGHBOR`.
  4. Loop com `javax.swing.Timer` de `1000 / FPS` ms chamando `atualizar()` e `repaint()`.
  5. Em `Main`, substituir o `println` por `SwingUtilities.invokeLater(() -> new JanelaPrincipal().setVisible(true))`.
- **Regras técnicas:** Toda criação de Swing dentro da thread de eventos; sem lógica de jogo.
- **Critérios de aceite:** Janela abre com 960x540, fundo preto, fecha encerrando o processo.
- **Validação:** `mvn -q compile exec:java` e fechar a janela.
- **Commit:** `feat: janela principal com painel e loop de renderizacao`
- **Tag:** `v0.5.1` — «Concluída a tarefa 5.1.1: janela e loop»

### Tarefa 5.1.2
- **ID:** `5.1.2`
- **Título:** Criar `Tela`, `GerenciadorDeTelas` e o repasse de eventos
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.1 integrada.
- **Arquivos-alvo:** `SRC ui/telas/Tela.java`, `SRC ui/GerenciadorDeTelas.java`, `SRC ui/PainelDoJogo.java`
- **Objetivo:** Permitir trocar de tela e repassar mouse e teclado à tela atual.
- **Procedimento de execução:**
  1. `interface Tela` com `atualizar()`, `desenhar(Graphics2D g)`, `aoClicar(int x, int y)`, `aoMoverMouse(int x, int y)`, `aoPressionarTecla(int codigo)`.
  2. `GerenciadorDeTelas` com `Tela telaAtual` e `trocarPara(Tela nova)`; métodos que delegam à tela atual, tratando nulo.
  3. No `PainelDoJogo`, chamar o gerenciador no loop e no `paintComponent`; registrar `MouseListener` (`mousePressed`), `MouseMotionListener` (`mouseMoved`) e `KeyListener` (`keyPressed`) repassando ao gerenciador.
- **Regras técnicas:** Telas não conhecem o `PainelDoJogo`; só o gerenciador.
- **Critérios de aceite:** Uma tela de teste imprime coordenadas ao clicar e o código da tecla pressionada.
- **Validação:** Tela anônima temporária (não commitada) injetada pelo `JanelaPrincipal`.
- **Commit:** `feat: interface tela e gerenciador de telas com repasse de eventos`
- **Tag:** `v0.5.2` — «Concluída a tarefa 5.1.2: gerenciador de telas»

### Tarefa 5.1.3
- **ID:** `5.1.3`
- **Título:** Criar o componente `Botao`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.2 integrada.
- **Arquivos-alvo:** `SRC ui/componentes/Botao.java`
- **Objetivo:** Ter um botão desenhado em Java2D com clique, hover e estado desabilitado.
- **Procedimento de execução:**
  1. Campos: `x, y, largura, altura`, `String rotulo`, `Runnable aoAcionar`, `boolean habilitado = true`, `boolean sobMouse`.
  2. `desenhar(Graphics2D g)`: retângulo preenchido, borda de 3 px e rótulo centralizado com `FontMetrics` (fonte monoespaçada bold 20); cor de realce no hover e cinza se desabilitado.
  3. `contem(x, y)`, `atualizarHover(x, y)`, `clicar(x, y)` (só executa `aoAcionar` se habilitado e dentro da área), `setHabilitado(boolean)` e `setRotulo(String)`.
- **Regras técnicas:** O botão não conhece telas nem regras de jogo.
- **Critérios de aceite:** Botão desabilitado ignora clique; hover muda a cor.
- **Validação:** Tela de teste temporária com dois botões, um desabilitado.
- **Commit:** `feat: criacao do componente botao com hover e estado habilitado`
- **Tag:** `v0.5.3` — «Concluída a tarefa 5.1.3: componente Botao»

### Bloco 5.2 — Tela inicial

### Tarefa 5.2.1
- **ID:** `5.2.1`
- **Título:** Criar a `TelaInicial` com Jogar e Sair
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.3 integrada.
- **Arquivos-alvo:** `SRC ui/telas/TelaInicial.java`, `SRC ui/JanelaPrincipal.java`
- **Objetivo:** Abrir o jogo em um menu funcional.
- **Procedimento de execução:**
  1. `TelaInicial implements Tela` com construtor `(Runnable aoJogar)`.
  2. Desenhar fundo verde-escuro e o título «RPG do Folclore Brasileiro» centralizado (fonte 40 bold) e o subtítulo «Ginásio do Fogo — MVP».
  3. Botões `Jogar` (380, 250, 200x50) e `Sair` (380, 320, 200x50). `Jogar` chama `aoJogar.run()`; `Sair` chama `System.exit(0)`. ENTER aciona `Jogar`.
  4. Na `JanelaPrincipal`, abrir `TelaInicial` como tela de abertura com `aoJogar` provisório que imprime `Jogar`.
- **Regras técnicas:** A tela recebe o que fazer por parâmetro; não instancia outras telas.
- **Critérios de aceite:** Menu aparece; Jogar imprime no console; Sair fecha sem erro.
- **Validação:** Executar e testar mouse, hover e ENTER.
- **Commit:** `feat: tela inicial com botoes jogar e sair`
- **Tag:** `v0.5.4` — «Concluída a tarefa 5.2.1: tela inicial»

### Bloco 5.3 — Seleção de personagem

### Tarefa 5.3.1
- **ID:** `5.3.1`
- **Título:** Desenhar o layout da `TelaSelecaoPersonagem`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.2.1 e 3.3.1 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaSelecaoPersonagem.java`
- **Objetivo:** Exibir os dois personagens com seus dados.
- **Procedimento de execução:**
  1. Construtor `(Consumer<Personagem> aoConfirmar, Runnable aoVoltar)`; campo `List<Personagem> opcoes = CatalogoDeHerois.criarTodos()`.
  2. Desenhar fundo e título «Escolha seu personagem».
  3. Para cada opção, um cartão 260x320 (x = 190 e 510, y = 100) com: retângulo colorido 120x120 como figura (ciano para Vento, laranja para Físico), nome, `getTipo().getRotulo()`, linhas `PV`, `ATQ`, `DEF` e `Habilidade: <nome> (<usos> usos)`.
- **Regras técnicas:** A tela usa só `Personagem` e `Habilidade` pelos getters; nunca `Saci` ou `Lobisomem`.
- **Critérios de aceite:** Os dois cartões exibem os valores da decisão D2 e as habilidades da D3.
- **Validação:** Abrir a tela por uma chamada temporária no `JanelaPrincipal` e conferir os valores.
- **Commit:** `feat: layout da tela de selecao de personagem`
- **Tag:** `v0.5.5` — «Concluída a tarefa 5.3.1: layout da seleção»

### Tarefa 5.3.2
- **ID:** `5.3.2`
- **Título:** Programar seleção, Confirmar e Voltar
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.3.1 integrada.
- **Arquivos-alvo:** `SRC ui/telas/TelaSelecaoPersonagem.java`
- **Objetivo:** Permitir escolher um cartão e confirmar.
- **Procedimento de execução:**
  1. Campo `indiceSelecionado = -1`; clique em um cartão o seleciona (borda amarela de 4 px); setas esquerda e direita também alternam.
  2. Botão `Confirmar` (380, 450, 200x50), desabilitado enquanto `indiceSelecionado < 0`; ao acionar (clique ou ENTER), `aoConfirmar.accept(opcoes.get(indiceSelecionado))`.
  3. Botão `Voltar` (40, 470, 160x40) e tecla ESC chamam `aoVoltar.run()`.
- **Regras técnicas:** Hover em todos os botões; seleção não muda o domínio.
- **Critérios de aceite:** Sem seleção, Confirmar fica inativo; com seleção, entrega o objeto correto.
- **Validação:** Teste manual com mouse e teclado; imprimir o nome recebido.
- **Commit:** `feat: selecao confirmacao e retorno na tela de personagem`
- **Tag:** `v0.5.6` — «Concluída a tarefa 5.3.2: interação da seleção»

### Tarefa 5.3.3
- **ID:** `5.3.3`
- **Título:** Ligar o menu à seleção de personagem
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.3.2 integrada.
- **Arquivos-alvo:** `SRC ui/JanelaPrincipal.java`
- **Objetivo:** Conectar Jogar → Seleção → Voltar ao menu.
- **Procedimento de execução:**
  1. Extrair um método `abrirMenu()` e outro `abrirSelecao()` que usam `gerenciador.trocarPara(...)`.
  2. `aoJogar` chama `abrirSelecao()`; `aoVoltar` chama `abrirMenu()`; `aoConfirmar` provisório imprime o nome do personagem (será substituído na Fase 6).
- **Regras técnicas:** A navegação fica concentrada na `JanelaPrincipal`.
- **Critérios de aceite:** Menu → Seleção → Voltar → Menu funciona repetidamente.
- **Validação:** Teste manual de 3 ciclos.
- **Commit:** `feat: navegacao entre menu e selecao de personagem`
- **Tag:** `v0.5.7` — «Concluída a tarefa 5.3.3: navegação menu e seleção»

### Bloco 5.4 — Layout da tela de batalha (sem lógica)

### Tarefa 5.4.1
- **ID:** `5.4.1`
- **Título:** Criar o componente `BarraDeVida`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.3 integrada.
- **Arquivos-alvo:** `SRC ui/componentes/BarraDeVida.java`
- **Objetivo:** Desenhar PV de forma proporcional e colorida.
- **Procedimento de execução:**
  1. Método estático `desenhar(Graphics2D g, int x, int y, int largura, int altura, int pvAtual, int pvMaximo)`.
  2. Fundo cinza e preenchimento proporcional; cor verde acima de 50%, amarela acima de 20%, vermelha abaixo.
  3. Texto `PV atual/máximo` sobre a barra.
- **Regras técnicas:** Tratar `pvMaximo <= 0` sem divisão por zero.
- **Critérios de aceite:** 60/60, 25/60 e 5/60 aparecem verde, amarela e vermelha.
- **Validação:** Tela temporária com as três barras.
- **Commit:** `feat: componente barra de vida com cores por faixa`
- **Tag:** `v0.5.8` — «Concluída a tarefa 5.4.1: barra de vida»

### Tarefa 5.4.2
- **ID:** `5.4.2`
- **Título:** Criar o componente `CaixaDeMensagens`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.2 integrada.
- **Arquivos-alvo:** `SRC ui/componentes/CaixaDeMensagens.java`
- **Objetivo:** Exibir texto de combate em uma caixa com quebra automática de linha.
- **Procedimento de execução:**
  1. Construtor `(x, y, largura, altura)`; campo `String texto`.
  2. `definirTexto(String)` e `desenhar(Graphics2D g)`: retângulo arredondado branco com borda preta dupla; quebrar linhas por largura usando `FontMetrics`.
- **Regras técnicas:** Sem efeito de digitação (fora do MVP).
- **Critérios de aceite:** Um texto longo quebra em várias linhas sem sair da caixa.
- **Validação:** Tela temporária com mensagem de 200 caracteres.
- **Commit:** `feat: componente caixa de mensagens com quebra de linha`
- **Tag:** `v0.5.9` — «Concluída a tarefa 5.4.2: caixa de mensagens»

### Tarefa 5.4.3
- **ID:** `5.4.3`
- **Título:** Desenhar o layout da `TelaBatalha`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.4.1, 5.4.2 e 2.3.3 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`
- **Objetivo:** Posicionar combatentes, HUDs e caixa de mensagens, ainda sem ações.
- **Procedimento de execução:**
  1. Construtor `(Batalha batalha, Consumer<ResultadoBatalha> aoEncerrar)`; guardar os campos.
  2. Fundo em degradê e uma plataforma elíptica sob cada combatente.
  3. Combatentes como retângulos coloridos com o nome: jogador (140, 230, 150x180) e chefe (640, 80, 170x200); cor por tipo (Vento ciano, Físico laranja, Fogo vermelho).
  4. HUD do chefe (40, 40, 320x70) e do jogador (600, 320, 320x80): nome, tipo e `BarraDeVida` lendo `getPvAtual()` e `getPvMaximo()`.
  5. `CaixaDeMensagens` em (20, 410, 480x110), vazia.
- **Regras técnicas:** A tela só lê getters de `Batalha` e `Personagem`; não chama nenhuma ação.
- **Critérios de aceite:** Com `new Batalha(new Saci(), new MulaSemCabeca())`, a tela mostra os dois HUDs com PV cheio.
- **Validação:** Abertura provisória a partir do `JanelaPrincipal` (não commitada).
- **Commit:** `feat: layout base da tela de batalha`
- **Tag:** `v0.5.10` — «Concluída a tarefa 5.4.3: layout da batalha»

### Bloco 5.5 — Tela de resultado

### Tarefa 5.5.1
- **ID:** `5.5.1`
- **Título:** Criar a `TelaResultado`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.1.3 integrada.
- **Arquivos-alvo:** `SRC ui/telas/TelaResultado.java`
- **Objetivo:** Mostrar vitória ou derrota e oferecer os caminhos de saída.
- **Procedimento de execução:**
  1. Construtor `(boolean vitoria, Runnable aoMenu, Runnable aoTentarNovamente)`.
  2. Título «Vitória!» (verde) ou «Derrota» (vermelho) e uma linha de texto descritiva.
  3. Botão `Voltar ao menu` (360, 380, 240x50) sempre visível; botão `Tentar novamente` (360, 320, 240x50) visível **somente** na derrota.
- **Regras técnicas:** Callbacks por parâmetro; nenhuma referência a `Batalha`.
- **Critérios de aceite:** Os dois modos aparecem corretamente e os botões chamam os `Runnable`.
- **Validação:** Abertura temporária nos dois modos, imprimindo ao clicar.
- **Commit:** `feat: tela de resultado com vitoria e derrota`
- **Tag:** `v0.5.11` — «Concluída a tarefa 5.5.1: tela de resultado»

---
## Fase 6 — Integração entre interface e lógica de batalha

Pré-requisito geral: Fases 4 e 5 integradas. Todas as tarefas desta fase são do Guilherme e usam **apenas** a API pública de `Batalha` já entregue pelo Ryan.

### Bloco 6.1 — Abertura da batalha

### Tarefa 6.1.1
- **ID:** `6.1.1`
- **Título:** Abrir a `TelaBatalha` ao confirmar o personagem
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 5.3.3, 5.4.3 e 4.1.2 integradas.
- **Arquivos-alvo:** `SRC ui/JanelaPrincipal.java`
- **Objetivo:** Conectar a seleção ao combate contra a Mula sem Cabeça.
- **Procedimento de execução:**
  1. Criar `abrirBatalha(Personagem escolhido)` que monta `new Batalha(escolhido, new MulaSemCabeca())` e chama `gerenciador.trocarPara(new TelaBatalha(batalha, this::aoFimDaBatalha))`.
  2. Trocar o `aoConfirmar` provisório por `abrirBatalha`.
  3. Criar `aoFimDaBatalha(ResultadoBatalha r)` provisório que imprime o resultado e chama `abrirMenu()`.
- **Regras técnicas:** A criação do chefe fica centralizada neste único método (será movida para `SessaoMvp` na tarefa 7.1.2).
- **Critérios de aceite:** Escolher Saci ou Lobisomem abre a batalha com o personagem correto e PV cheio.
- **Validação:** Teste manual com os dois personagens, conferindo nomes e PV nos HUDs.
- **Commit:** `feat: abertura da batalha a partir da selecao de personagem`
- **Tag:** `v0.6.1` — «Concluída a tarefa 6.1.1: seleção abre a batalha»

### Bloco 6.2 — Ações do jogador

### Tarefa 6.2.1
- **ID:** `6.2.1`
- **Título:** Exibir as mensagens da batalha em fila
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 6.1.1 integrada.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`
- **Objetivo:** Mostrar uma mensagem por vez e só avançar quando o jogador ler.
- **Procedimento de execução:**
  1. Campo `Queue<String> fila = new ArrayDeque<>()`; método privado `absorverMensagens()` que faz `fila.addAll(batalha.consumirMensagens())`.
  2. Chamar `absorverMensagens()` no construtor e após cada ação da batalha.
  3. A `CaixaDeMensagens` mostra `fila.peek()`; ENTER, ESPAÇO ou clique na tela remove o primeiro item.
  4. Método `podeAgir()`: `fila.isEmpty()`.
- **Regras técnicas:** A tela nunca lê as mensagens internas da `Batalha` fora de `consumirMensagens()`.
- **Critérios de aceite:** A mensagem de abertura «O chefe Mula sem Cabeça desafia você!» aparece e some ao avançar.
- **Validação:** Teste manual.
- **Commit:** `feat: fila de mensagens de combate na tela de batalha`
- **Tag:** `v0.6.2` — «Concluída a tarefa 6.2.1: fila de mensagens»

### Tarefa 6.2.2
- **ID:** `6.2.2`
- **Título:** Criar o botão Atacar e ligá-lo à batalha
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 6.2.1 e 2.2.1 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`
- **Objetivo:** Permitir que o jogador ataque pela interface.
- **Procedimento de execução:**
  1. Criar `Botao botaoAtacar` (520, 410, 200x45, «Atacar») com hover.
  2. Ação: `batalha.acaoAtacar()` seguido de `absorverMensagens()`.
  3. Habilitar o botão somente quando `batalha.getEstado() == ESCOLHA_DO_JOGADOR && podeAgir()`, reavaliando a cada `atualizar()`.
- **Regras técnicas:** Sem cálculo de dano na tela.
- **Critérios de aceite:** Clicar reduz o PV do chefe e mostra a mensagem do ataque; o botão fica inativo enquanto houver mensagem pendente.
- **Validação:** Teste manual de um ataque e conferência do HUD.
- **Commit:** `feat: botao atacar na tela de batalha`
- **Tag:** `v0.6.3` — «Concluída a tarefa 6.2.2: botão Atacar»

### Tarefa 6.2.3
- **ID:** `6.2.3`
- **Título:** Criar o botão Habilidade e ligá-lo à batalha
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 6.2.2 e 2.2.2 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`
- **Objetivo:** Permitir o uso da habilidade pela interface, com os usos visíveis.
- **Procedimento de execução:**
  1. Criar `Botao botaoHabilidade` (740, 410, 200x45).
  2. A cada `atualizar()`, definir o rótulo `<nome da habilidade> (<usos restantes>/<usos máximos>)` lendo `getHabilidade()`.
  3. Ação: `batalha.acaoHabilidade()` seguido de `absorverMensagens()`.
  4. Habilitar com a mesma regra do Atacar **e** `getHabilidade().temUsosRestantes()`.
- **Regras técnicas:** A tela não distingue cura de dano; apenas chama a ação.
- **Critérios de aceite:** Saci vê a cura refletida na barra e o contador cair de 3 para 2; ao zerar, o botão fica desabilitado.
- **Validação:** Teste manual com Saci (3 usos) e Lobisomem (2 usos).
- **Commit:** `feat: botao habilidade com contador de usos na tela de batalha`
- **Tag:** `v0.6.4` — «Concluída a tarefa 6.2.3: botão Habilidade»

### Bloco 6.3 — Turno do chefe e encerramento

### Tarefa 6.3.1
- **ID:** `6.3.1`
- **Título:** Disparar o turno do chefe automaticamente
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 6.2.3 e 2.3.2 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`
- **Objetivo:** Fazer o chefe agir assim que o jogador terminar de ler as mensagens.
- **Procedimento de execução:**
  1. No `atualizar()`, se `batalha.getEstado() == TURNO_DO_CHEFE && podeAgir()`, chamar `batalha.executarTurnoDoChefe()` e `absorverMensagens()`.
- **Regras técnicas:** Chamar uma única vez por turno (a mudança de estado garante isso).
- **Critérios de aceite:** Após o jogador agir e ler a mensagem, o chefe ataca; em turnos pares usa Galope Infernal enquanto houver usos.
- **Validação:** Jogar 4 turnos e conferir o padrão básico, habilidade, básico, habilidade.
- **Commit:** `feat: turno automatico do chefe na tela de batalha`
- **Tag:** `v0.6.5` — «Concluída a tarefa 6.3.1: turno do chefe na interface»

### Tarefa 6.3.2
- **ID:** `6.3.2`
- **Título:** Encerrar a batalha e abrir a `TelaResultado`
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 6.3.1, 5.5.1 e 2.3.3 integradas.
- **Arquivos-alvo:** `SRC ui/telas/TelaBatalha.java`, `SRC ui/JanelaPrincipal.java`
- **Objetivo:** Passar do combate ao resultado depois de ler todas as mensagens.
- **Procedimento de execução:**
  1. Em `atualizar()`, se `batalha.estaEncerrada() && podeAgir()` e o aviso ainda não foi dado, chamar `aoEncerrar.accept(batalha.getResultado())` uma única vez (flag `encerramentoNotificado`).
  2. Em `aoFimDaBatalha`, abrir `TelaResultado(r == VITORIA, this::abrirMenu, this::abrirSelecao)`; o `abrirSelecao` é provisório para «Tentar novamente» (refeito em 7.2.1).
- **Regras técnicas:** Nenhuma ação fica habilitada após o fim da batalha.
- **Critérios de aceite:** Vitória e derrota levam à tela correta; as mensagens finais são lidas antes da troca de tela.
- **Validação:** Vencer com os dois personagens e perder uma vez (não atacar com Saci e usar só a cura até acabar).
- **Commit:** `feat: encerramento da batalha com tela de resultado`
- **Tag:** `v0.6.6` — «Concluída a tarefa 6.3.2: fim de batalha e resultado»

---

## Fase 7 — Fluxo completo do ginásio e condições de vitória e derrota

### Bloco 7.1 — Sessão e conclusão do ginásio

### Tarefa 7.1.1
- **ID:** `7.1.1`
- **Título:** Criar `SessaoMvp`
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 4.1.2 e 2.3.3 integradas.
- **Arquivos-alvo:** `SRC progressao/SessaoMvp.java`
- **Objetivo:** Guardar o único dado de progressão do MVP e centralizar a criação do confronto.
- **Procedimento de execução:**
  1. Criar `SessaoMvp` com `private boolean ginasioConcluido` e `isGinasioConcluido()`.
  2. `public Batalha criarBatalha(Personagem heroi)` retorna `new Batalha(heroi, new MulaSemCabeca())`.
  3. `public void registrarResultado(ResultadoBatalha r)`: se `VITORIA`, `ginasioConcluido = true`.
  4. `public void reiniciar()`: `ginasioConcluido = false`.
- **Regras técnicas:** Sem dependência de `ui`; sem persistência.
- **Critérios de aceite:** Estado começa falso; vitória o torna verdadeiro; derrota não altera; `reiniciar()` volta a falso.
- **Validação:** `main` temporário cobrindo os quatro casos.
- **Commit:** `feat: sessao do mvp com marcacao de ginasio concluido`
- **Tag:** `v0.7.1` — «Concluída a tarefa 7.1.1: sessão do MVP»

### Tarefa 7.1.2
- **ID:** `7.1.2`
- **Título:** Usar a sessão e exibir «Ginásio do Fogo concluído!»
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 7.1.1 e 6.3.2 integradas.
- **Arquivos-alvo:** `SRC ui/JanelaPrincipal.java`, `SRC ui/telas/TelaResultado.java`
- **Objetivo:** Fechar o fluxo de vitória e marcar a conclusão do ginásio.
- **Procedimento de execução:**
  1. Na `JanelaPrincipal`, criar o campo `SessaoMvp sessao`; `abrirBatalha` passa a usar `sessao.criarBatalha(escolhido)`.
  2. Em `aoFimDaBatalha`, chamar `sessao.registrarResultado(r)` antes de abrir a tela de resultado.
  3. Alterar o construtor de `TelaResultado` para `(boolean vitoria, boolean ginasioConcluido, Runnable aoMenu, Runnable aoTentarNovamente)`; na vitória com `ginasioConcluido`, exibir «Ginásio do Fogo concluído!».
  4. `abrirMenu()` chama `sessao.reiniciar()`.
- **Regras técnicas:** A tela só recebe booleanos; não conhece `SessaoMvp`.
- **Critérios de aceite:** Vencer a Mula mostra a mensagem do ginásio; voltar ao menu e jogar de novo começa limpo.
- **Validação:** Teste manual: vencer, voltar ao menu, jogar de novo e perder, conferindo que a mensagem de conclusão não aparece na derrota.
- **Commit:** `feat: conclusao do ginasio do fogo ao vencer o chefe`
- **Tag:** `v0.7.2` — «Concluída a tarefa 7.1.2: ginásio concluído»

### Bloco 7.2 — Derrota e reinício

### Tarefa 7.2.1
- **ID:** `7.2.1`
- **Título:** Implementar «Tentar novamente» com personagem e chefe novos
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 7.1.2 integrada.
- **Arquivos-alvo:** `SRC ui/JanelaPrincipal.java`
- **Objetivo:** Permitir nova tentativa sem objetos “sujos” da batalha anterior.
- **Procedimento de execução:**
  1. Ao confirmar um personagem, guardar `escolhidoNome = personagem.getNome()`.
  2. Criar `tentarNovamente()`: localizar em `CatalogoDeHerois.criarTodos()` o personagem de mesmo nome (instância nova) e chamar `abrirBatalha` com ele.
  3. Usar `this::tentarNovamente` como `aoTentarNovamente` da `TelaResultado`.
- **Regras técnicas:** Nunca reutilizar o `Personagem` da batalha anterior.
- **Critérios de aceite:** Após perder, «Tentar novamente» inicia batalha com PV e usos cheios e chefe novo.
- **Validação:** Perder com o Saci, tentar de novo e confirmar PV 60/60, 3 usos e chefe 55/55.
- **Commit:** `feat: tentar novamente apos derrota com instancias novas`
- **Tag:** `v0.7.3` — «Concluída a tarefa 7.2.1: tentar novamente»

---

## Fase 8 — Testes, correções, validação e finalização do MVP

### Bloco 8.1 — Documentação técnica

### Tarefa 8.1.1
- **ID:** `8.1.1`
- **Título:** Documentar os diagramas do MVP em Mermaid
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 7.2.1 integrada.
- **Arquivos-alvo:** `docs/diagrama-classes-mvp.md`
- **Objetivo:** Registrar a arquitetura como ela foi realmente construída.
- **Procedimento de execução:**
  1. `classDiagram` com `Personagem` (abstrata) e `Saci`, `Lobisomem`, `MulaSemCabeca`.
  2. `classDiagram` com `Habilidade`, `HabilidadeBase`, `HabilidadeDano`, `HabilidadeCura` e a relação com `Personagem`.
  3. `stateDiagram-v2` de `EstadoBatalha` com as transições (atacar, habilidade, turno do chefe, vitória, derrota).
  4. `flowchart` das telas: Inicial → Seleção → Batalha → Resultado → Inicial (e Tentar novamente → Batalha).
- **Regras técnicas:** Conferir os nomes com o código; não documentar o que não existe.
- **Critérios de aceite:** Os quatro diagramas renderizam no GitHub e coincidem com o código.
- **Validação:** Visualizar o arquivo no repositório.
- **Commit:** `docs: diagramas de classes estados e telas do mvp`
- **Tag:** `v0.8.1` — «Concluída a tarefa 8.1.1: diagramas do MVP»

### Tarefa 8.1.2
- **ID:** `8.1.2`
- **Título:** Documentar os quatro pilares da POO no MVP
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 8.1.1 integrada.
- **Arquivos-alvo:** `docs/pilares-poo-mvp.md`
- **Objetivo:** Ter um exemplo real do código para cada pilar, pronto para apresentação.
- **Procedimento de execução:**
  1. **Abstração:** `Personagem` e `Habilidade`.
  2. **Encapsulamento:** campos privados e validações em `receberDano`, `curar` e no construtor.
  3. **Herança:** `Saci`, `Lobisomem` e `MulaSemCabeca` herdando de `Personagem`; `HabilidadeDano` e `HabilidadeCura` herdando de `HabilidadeBase`.
  4. **Polimorfismo:** `atacar`, `Habilidade.executar` (dano vs. cura) e `deveUsarHabilidade` do chefe; citar a saída do `DemoPilares`.
  5. Para cada pilar, indicar arquivo, método e em que momento da batalha aparece.
- **Regras técnicas:** Linguagem simples; trechos curtos.
- **Critérios de aceite:** Os quatro pilares têm exemplo e referência a arquivo existente.
- **Validação:** Revisão cruzada do Guilherme conferindo cada referência.
- **Commit:** `docs: explicacao dos pilares de poo aplicados ao mvp`
- **Tag:** `v0.8.2` — «Concluída a tarefa 8.1.2: pilares de POO»

### Bloco 8.2 — Validação funcional

### Tarefa 8.2.1
- **ID:** `8.2.1`
- **Título:** Criar e executar o checklist de validação
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 7.2.1 integrada.
- **Arquivos-alvo:** `docs/checklist-validacao-mvp.md`
- **Objetivo:** Verificar o critério de conclusão da seção 13 com resultado registrado.
- **Procedimento de execução:**
  1. Copiar a checklist da seção 13 para o arquivo, com colunas «Resultado» (OK/Falhou) e «Observação».
  2. Executar cada item na aplicação (ambos personagens, vitória, derrota, tentar novamente, sair).
  3. Registrar os resultados. Para cada falha, abrir uma correção conforme a seção 9.5 (`fix:` e tag `-fix1`).
  4. Repetir os itens que falharam após a correção.
- **Regras técnicas:** Não corrigir dentro desta tarefa; correções têm commit próprio.
- **Critérios de aceite:** Todos os itens com resultado OK (ou correção integrada e item repetido).
- **Validação:** Revisão cruzada do Ryan repetindo 3 itens sorteados.
- **Commit:** `test: checklist de validacao funcional do mvp executada`
- **Tag:** `v0.8.3` — «Concluída a tarefa 8.2.1: validação funcional»

### Bloco 8.3 — Entrega

### Tarefa 8.3.1
- **ID:** `8.3.1`
- **Título:** Gerar o JAR executável
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 8.2.1 integrada.
- **Arquivos-alvo:** `pom.xml`
- **Objetivo:** Permitir executar o MVP com um único comando.
- **Procedimento de execução:**
  1. Configurar `maven-jar-plugin` com `Main-Class` `br.folclore.Main` e `finalName` `rpg-folclore-mvp`.
  2. Rodar `mvn -q package` e `java -jar target/rpg-folclore-mvp.jar`.
- **Regras técnicas:** Sem novas dependências; o jar não vai para o Git (`target/` ignorado).
- **Critérios de aceite:** O jar abre o menu e o fluxo completo funciona.
- **Validação:** Executar o jar em uma máquina limpa (ou outro usuário) apenas com JDK 17.
- **Commit:** `build: empacotamento do mvp em jar executavel`
- **Tag:** `v0.8.4` — «Concluída a tarefa 8.3.1: jar executável»

### Tarefa 8.3.2
- **ID:** `8.3.2`
- **Título:** Finalizar o README do MVP
- **Responsável:** Guilherme Aparecido
- **Pré-requisitos:** 8.3.1 integrada.
- **Arquivos-alvo:** `README.md`
- **Objetivo:** Documentar execução, controles e regras para quem abrir o projeto.
- **Procedimento de execução:**
  1. Atualizar a seção «MVP» com: como executar (`mvn compile exec:java` e `java -jar`), controles (mouse, setas, ENTER, ESPAÇO, ESC), resumo das regras (fórmula de dano, turnos, habilidades) e a branch `mvp/prototipo`.
  2. Incluir os links para os documentos em `docs/`.
- **Regras técnicas:** Texto em PT-BR; sem prometer funcionalidades excluídas.
- **Critérios de aceite:** Um terceiro consegue executar e jogar só com o README.
- **Validação:** Teste cego com alguém que não participou.
- **Commit:** `docs: readme final do mvp com execucao controles e regras`
- **Tag:** `v0.8.5` — «Concluída a tarefa 8.3.2: README final»

### Tarefa 8.3.3
- **ID:** `8.3.3`
- **Título:** Fechar a versão `v1.0.0` do MVP
- **Responsável:** Ryan Amorim
- **Pré-requisitos:** 8.3.2 integrada e checklist da seção 13 integralmente marcada.
- **Arquivos-alvo:** `docs/mvp-escopo.md`
- **Objetivo:** Registrar o encerramento e criar a tag final, sem tocar no projeto completo.
- **Procedimento de execução:**
  1. Acrescentar em `docs/mvp-escopo.md` a seção «Status: concluído na versão 1.0.0» com a data.
  2. Fluxo normal de PR para `mvp/prototipo`.
  3. Criar a tag `v1.0.0` e publicá-la.
  4. Confirmar que `dev` e `main` permaneceram intactas.
- **Regras técnicas:** **Não** fazer merge de `mvp/prototipo` em `dev` ou `main`.
- **Critérios de aceite:** Tag `v1.0.0` aponta para o último commit de `mvp/prototipo`; `git log dev..mvp/prototipo` mostra só commits do MVP.
- **Validação:** `git tag -l "v1.0.0"` e `git diff dev...mvp/prototipo --stat`.
- **Commit:** `chore: conclusao da versao 1.0.0 do mvp`
- **Tag:** `v1.0.0` — «MVP concluído: ginásio do Fogo jogável de ponta a ponta»

---

## 11. Dependências entre tarefas

| Quem espera | Tarefa | Depende de | O que precisa estar pronto |
|---|---|---|---|
| Guilherme | 5.1.1 | 0.2.1 | Projeto compilando |
| Guilherme | 5.3.1 | 3.3.1 (Ryan) | `CatalogoDeHerois.criarTodos()` retornando `List<Personagem>` |
| Guilherme | 5.4.3 | 2.3.3 (Ryan) | `Batalha` com getters, `estaEncerrada()` e `getResultado()` |
| Guilherme | 6.1.1 | 4.1.2 (Ryan) | `MulaSemCabeca` completa |
| Guilherme | 6.2.2 | 2.2.1 (Ryan) | `acaoAtacar()` |
| Guilherme | 6.2.3 | 2.2.2 (Ryan) | `acaoHabilidade()` |
| Guilherme | 6.3.1 | 2.3.2 (Ryan) | `executarTurnoDoChefe()` |
| Guilherme | 7.1.2 | 7.1.1 (Ryan) | `SessaoMvp` |
| Ryan | 8.1.1 | 7.2.1 (Guilherme) | Fluxo de telas final |
| Ryan | 8.3.1 | 8.2.1 (Guilherme) | Checklist aprovada |

**Execução em paralelo recomendada:**

| Momento | Ryan Amorim | Guilherme Aparecido |
|---|---|---|
| 1 | Fase 0 (0.1.1 a 0.1.3) | aguarda 0.1.3 |
| 2 | Fase 1, depois Fase 2 | 0.2.1, 5.1.1, 5.1.2, 5.1.3, 5.2.1, 5.4.1, 5.4.2, 5.5.1 |
| 3 | Fase 3 | aguarda 3.3.1: 5.3.1 a 5.3.3 |
| 4 | Fase 4 | 5.4.3 e Fase 6 (conforme as entregas) |
| 5 | 7.1.1 | 7.1.2 e 7.2.1 |
| 6 | Fase 8 (8.1.x, 8.3.1, 8.3.3) | 8.2.1 e 8.3.2 |

**Arquivos compartilhados:** `JanelaPrincipal.java` e `README.md` são só do Guilherme; `Main.java` é só do Ryan, com a única exceção da tarefa 5.1.1. `Personagem.java` e `Batalha.java` são só do Ryan.

## 12. Critérios gerais de qualidade

1. Cada tarefa compila isoladamente (`mvn -q compile`) e não quebra tarefas anteriores.
2. Um commit por tarefa, com mensagem em Conventional Commits e escopo exato da tarefa.
3. Nenhuma regra de combate em classes de `ui`; nenhuma dependência de `ui` em `modelo`, `habilidades`, `batalha` ou `progressao`.
4. Atributos de domínio sempre `private`; alterações apenas por métodos validados.
5. Sem `instanceof` para decidir comportamento de personagem ou habilidade.
6. Sem números soltos: status e poderes em constantes nomeadas.
7. Sem código comentado, sem `System.out` fora de `demo` e das validações temporárias não commitadas.
8. Todo PR revisado pelo outro desenvolvedor, com a base `mvp/prototipo`.
9. Todos os textos visíveis ao jogador em Português do Brasil.
10. Nenhuma classe criada sem uso real no MVP.

## 13. Checklist de conclusão

### 13.1 Validação funcional

- [ ] 1. A aplicação inicia sem erro.
- [ ] 2. A tela inicial aparece com **Jogar** e **Sair**.
- [ ] 3. É possível escolher Saci ou Lobisomem e voltar ao menu sem perder estabilidade.
- [ ] 4. Confirmar o personagem abre a batalha contra a Mula sem Cabeça.
- [ ] 5. Os HUDs mostram nome, tipo e PV de ambos os combatentes.
- [ ] 6. **Atacar** reduz o PV do chefe conforme a fórmula `max(1, ataque + poder − defesa)`.
- [ ] 7. **Habilidade** funciona (cura do Saci, dano do Lobisomem) e exibe usos restantes.
- [ ] 8. Habilidade sem usos fica desabilitada e não consome turno.
- [ ] 9. O chefe age sozinho: básico nos turnos ímpares, Galope Infernal nos pares enquanto houver usos.
- [ ] 10. Todas as mensagens aparecem em ordem e são lidas antes da próxima ação.
- [ ] 11. Vitória (PV do chefe = 0) leva à tela «Vitória!» com «Ginásio do Fogo concluído!».
- [ ] 12. Derrota (PV do jogador = 0) leva à tela «Derrota» com «Tentar novamente».
- [ ] 13. «Tentar novamente» reinicia com PV cheio, usos cheios e chefe novo.
- [ ] 14. «Voltar ao menu» reinicia o estado; um novo jogo começa limpo.
- [ ] 15. **Sair** encerra a aplicação sem erro.

### 13.2 Validação técnica

- [ ] 16. `mvn -q compile` sem erros ou avisos relevantes.
- [ ] 17. `DemoPilares`, `DemoBatalhaVitoria` e `DemoBatalhaDerrota` executam com a saída esperada.
- [ ] 18. `java -jar target/rpg-folclore-mvp.jar` executa o fluxo completo.
- [ ] 19. Nenhum arquivo da pasta `ui` contém fórmula de dano.
- [ ] 20. Os quatro pilares de POO estão documentados com referência real a arquivos.
- [ ] 21. Todas as tarefas têm commit e tag publicados na branch `mvp/prototipo`.
- [ ] 22. `dev` e `main` não receberam commits ou tags do MVP.
- [ ] 23. A tag `v1.0.0` existe e aponta para o último commit do MVP.

## 14. Resultado esperado

Ao final, o repositório terá a branch `mvp/prototipo` com 58 commits e 58 tags, um jogo executável por `mvn compile exec:java` ou `java -jar`, e uma documentação que comprova a arquitetura em POO. O fluxo **Menu → Seleção → Batalha → Resultado → Menu** funcionará sem erros, com dois personagens, um chefe, combate por turnos, vitória, derrota e conclusão do Ginásio do Fogo. Com isso, a equipe poderá decidir com segurança se a arquitetura sustenta a expansão para o projeto completo.

### Resumo de fases, blocos e nano-tarefas

| Fase | Tema | Blocos | Tarefas | Tags |
|---|---|---|---|---|
| 0 | Preparação | 2 | 4 | v0.0.1 a v0.0.4 |
| 1 | Modelagem de POO | 2 | 11 | v0.1.1 a v0.1.11 |
| 2 | Mecânicas de batalha | 3 | 7 | v0.2.1 a v0.2.7 |
| 3 | Personagens jogáveis | 3 | 5 | v0.3.1 a v0.3.5 |
| 4 | Chefe e validações | 2 | 5 | v0.4.1 a v0.4.5 |
| 5 | Interfaces gráficas | 5 | 11 | v0.5.1 a v0.5.11 |
| 6 | Integração interface e lógica | 3 | 6 | v0.6.1 a v0.6.6 |
| 7 | Fluxo do ginásio | 2 | 3 | v0.7.1 a v0.7.3 |
| 8 | Testes e finalização | 3 | 6 | v0.8.1 a v0.8.5 e v1.0.0 |
| **Total** | | **25** | **58** | |
