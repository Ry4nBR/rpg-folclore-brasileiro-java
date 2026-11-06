# Progresso de execução — MVP RPG Folclore

## Estado atual

- **Fase:** 1 — Modelagem das entidades e conceitos fundamentais de POO
- **Bloco:** 1.2 — Habilidades (Polimorfismo)
- **Última tarefa concluída:** 1.2.5 — Vincular uma habilidade ao `Personagem`
- **Tarefa em execução:** Nenhuma
- **Próxima tarefa pendente:** Nenhuma dentro do Bloco 1.2
- **Status do bloco:** Em andamento
- **Bloqueios:** Nenhum.

## Decisões e estado Git verificado

- Branch de trabalho: `mvp/prototipo`; a branch já existia localmente e no remoto.
- A execução permanece na branch existente; não foram reescritos commits nem alteradas branches `develop` ou `main`.
- Tags `v0.*` já existentes no repositório exigem o prefixo `mvp-`, conforme a seção 9.4 do planejamento. Prefixo decidido: `mvp-`.
- A comparação inicial de `develop...mvp/prototipo` mostrou alterações amplas (incluindo remoções) além do documento de escopo previsto no aceite da tarefa 0.1.1. Divergência preservada conforme orientação do usuário; não será corrigida neste ciclo.
- Autorização explícita do usuário: executar tarefas na `mvp/prototipo`, sem PRs ou branches de tarefa, apenas commits e tags. Não fazer push.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 0.2.
- Em 06/11/2026, o usuário autorizou continuar para a Fase 1 / Bloco 1.1 e confirmou que executará sozinho na própria máquina; tarefas atribuídas a Guilherme podem ser tratadas localmente conforme essa autorização.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 1.2.

## Registro das tarefas

| Tarefa | Status | Commit (mensagem planejada) | Tag planejada | Validação |
|---|---|---|---|---|
| 0.1.1 — Criar a branch do MVP e registrar o escopo | Concluída | `a735288` — `docs: registro do escopo e da branch do mvp` | `mvp-v0.0.1` | Conteúdo obrigatório conferido; `git diff --check` passou |
| 0.1.2 — Configurar Maven (Java 17) e `.gitignore` | Concluída | `31b4df0` — `chore: configuracao do maven java 17 e gitignore do mvp` | `mvp-v0.0.2` | `mvn -q validate` passou com JDK 17; padrões `.gitignore` e ausência de dependências adicionais conferidos |
| 0.1.3 — Criar o ponto de entrada e a estrutura de pacotes | Concluída | `chore: ponto de entrada e estrutura de pacotes do mvp` | `mvp-v0.0.3` | `mvn -q compile exec:java` imprimiu a mensagem exigida; pacotes previstos conferidos; pacotes fora do MVP ausentes; `target/` ignorado |
| 0.2.1 — Validar o ambiente e documentar requisitos no README | Concluída | `0b56689` — `docs: requisitos e execucao do mvp no readme` | `mvp-v0.0.4` | README conferido; `java -version`, `mvn -version` e `mvn -q compile exec:java` passaram localmente; substituição do critério externo autorizada pelo usuário |
| 1.1.1 — Criar o enum `Tipo` | Concluída | `04e7098` — `feat: criacao do enum tipo com rotulos em portugues` | `mvp-v0.1.1` | `mvn -q compile` passou; JShell confirmou por igualdade exata os rótulos Vento, Físico e Fogo |
| 1.1.2 — Criar `Personagem` com atributos encapsulados | Concluída | `2b03cee` — `feat: classe abstrata personagem com atributos encapsulados` | `mvp-v0.1.2` | `mvn -q compile` passou; JShell validou getters, PV inicializado ao máximo, valores inválidos lançando exceção e ausência de setters |
| 1.1.3 — Implementar `estaVivo` e `receberDano` | Concluída | `757bcc6` — `feat: metodos estaVivo e receberDano em personagem` | `mvp-v0.1.3` | `mvn -q compile` passou; JShell validou dano 10, fatal 999 (aplicado 60, PV 0 e morto) e rejeição de dano negativo |
| 1.1.4 — Implementar `curar` e `curarPercentualDaVidaMaxima` | Concluída | `933e9fb` — `feat: metodos curar e curarPercentualDaVidaMaxima em personagem` | `mvp-v0.1.4` | `mvn -q compile` passou; JShell validou cura de 12/2 PV, saturação no máximo, rejeição de quantidade negativa e proteção contra overflow |
| 1.1.5 — Implementar `calcularDano` | Concluída | `7b32921` — `feat: criacao do metodo calcularDano na classe personagem` | `mvp-v0.1.5` | `mvn -q compile` passou; JShell confirmou dano 11, mínimo 1 e que o alvo não é alterado |
| 1.1.6 — Declarar ataques abstratos e criar resumo de status | Concluída | `ec72b3a` — `feat: metodos abstratos de ataque e resumo de status em personagem` | `mvp-v0.1.6` | `mvn -q compile` passou; JShell confirmou os dois métodos abstratos e o resumo exato |
| 1.2.1 — Criar `ResultadoAcao` e a interface `Habilidade` | Concluída | `feat: criacao de resultadoAcao e da interface habilidade` | `mvp-v0.1.7` | `mvn -q compile` passou; JShell confirmou criação, getters, campos finais e ausência de setters |
| 1.2.2 — Criar `HabilidadeBase` com controle de usos | Concluída | `feat: classe abstrata habilidadeBase com controle de usos` | `mvp-v0.1.8` | `mvn -q compile` passou; JShell validou contador, consumo até zero, exceção ao exceder e rejeição de usos não positivos |
| 1.2.3 — Implementar `HabilidadeDano` | Concluída | `feat: implementacao da habilidade de dano` | `mvp-v0.1.9` | `mvn -q compile` passou; JShell validou dois danos, usos consumidos, mensagem e terceira falha sem alterar o alvo |
| 1.2.4 — Implementar `HabilidadeCura` | Concluída | `feat: implementacao da habilidade de cura` | `mvp-v0.1.10` | `mvn -q compile` passou; JShell validou cura de 12 PV saindo de 40, cura efetiva de 2 saindo de 58, alvo ignorado e falha sem usos |
| 1.2.5 — Vincular uma habilidade ao `Personagem` | Concluída | `feat: habilidade vinculada ao personagem com metodo usarHabilidade` | `mvp-v0.1.11` | `mvn -q compile` passou; JShell validou execução via `usarHabilidade`, habilidade privada, rejeição de nulo e erro sem habilidade |

## Histórico do bloco

- 0.1.1: `docs/mvp-escopo.md` registra objetivo, comparativo, exclusões, decisões D1-D12 e prefixo `mvp-`; o prefixo foi necessário porque já existem tags `v0.*`.
- A branch `mvp/prototipo` já existia; foi preservada sem reescrita. A comparação inicial com `develop` revelou divergências amplas, registradas acima.
- A validação textual inicial no PowerShell usou a codificação padrão e não encontrou cabeçalhos acentuados; repetida explicitamente em UTF-8, passou. `git diff --check` passou.
- 0.1.1 foi commitada em `a735288` e recebeu a tag anotada `mvp-v0.0.1`.
- Ao iniciar 0.1.2, `java` e `mvn` não foram encontrados no PATH; a instalação local ainda será investigada antes da validação Maven.
- 0.1.2: o `winget` não conseguiu acessar a origem e a restauração requer administrador. Para validar sem alterar a instalação global, foram baixados JDK Temurin 17.0.21 e Maven 3.9.9 portáteis para a pasta temporária da sessão; os checksums oficiais SHA-256/SHA-512 conferiram. `mvn -q validate` passou. Nenhuma ferramenta foi adicionada ao repositório.
- 0.1.2 foi commitada em `31b4df0` e recebeu a tag anotada `mvp-v0.0.2`.
- 0.1.3: `mvn -q compile exec:java` executou com sucesso e imprimiu exatamente `RPG do Folclore Brasileiro (MVP) - iniciando`. Os sete diretórios de pacote previstos existem; `audio`, `ia`, `save` e `ui/efeitos` não existem; `target/` está ignorado.
- 0.1.3 foi commitada em `8c20795` e recebeu a tag anotada `mvp-v0.0.3`; não foi feito push.
- 0.2.1: README atualizado com descrição, aviso de idioma, JDK 17, Maven 3.8+ e comando de execução. A seção, os requisitos e o comando foram conferidos; JDK Temurin 17.0.21 e Maven 3.9.9 portáteis (checksums oficiais conferidos) executaram `java -version`, `mvn -version` e `mvn -q compile exec:java` com saída esperada. O usuário autorizou explicitamente substituir a execução na máquina do Guilherme pela validação local.
- 0.2.1 foi commitada em `0b56689` e recebeu a tag anotada `mvp-v0.0.4`; não foi feito push.
- O Bloco 1.1 foi autorizado em 06/11/2026 e executado sem avançar para o Bloco 1.2.
- Ferramentas de build não estão instaladas globalmente; para este bloco estão sendo usadas distribuições portáteis JDK 17 e Maven 3.9.9, baixadas com verificação dos checksums oficiais para a pasta temporária da sessão. Remover após as validações.
- 1.2.1: criado `ResultadoAcao` imutável com enum `Efeito`, valor e mensagem, além do contrato `Habilidade`.
- 1.2.1 foi commitada e tagueada como `mvp-v0.1.7`.
- 1.2.2: criado `HabilidadeBase` abstrato, com nome e limite fixos, contador inicializado e consumo protegido contra usos abaixo de zero.
- 1.2.2 foi commitada e tagueada como `mvp-v0.1.8`.
- 1.2.3: criado `HabilidadeDano`; execução sem usos retorna FALHA com valor zero e não altera PV nem contador.
- 1.2.3 foi commitada e tagueada como `mvp-v0.1.9`.
- 1.2.4: criado `HabilidadeCura`; a habilidade cura o usuário, ignora o alvo e retorna FALHA sem consumir uso quando esgotada.
- 1.2.4 foi commitada e tagueada como `mvp-v0.1.10`.
- 1.2.5: `Personagem` agora mantém a habilidade privada, permite definição não nula por subclasses e delega seu uso, lançando `IllegalStateException` quando não houver habilidade.
- 1.2.5 foi validada; o commit e a tag individuais serão registrados antes da revisão final do bloco.
- 1.1.1: criado `Tipo` com os valores VENTO, FISICO e FOGO e respectivos rótulos. A primeira verificação textual pelo pipe do PowerShell exibiu acento corrompido por codificação do console; a validação foi refeita em JShell por igualdade Unicode exata e passou.
- 1.1.2: criado `Personagem` com campos privados, construtor protegido validado e getters sem setters; JShell confirmou cenário válido e rejeição de nome vazio/em branco, tipo nulo, PV não positivo e ataque/defesa negativos.
- 1.1.2 foi commitada e tagueada como `mvp-v0.1.2`.
- 1.1.3: `receberDano` valida valor negativo, limita PV a zero e retorna o dano aplicado; `estaVivo` reflete o PV atual.
- 1.1.3 foi commitada e tagueada como `mvp-v0.1.3`.
- 1.1.4: cura percentual usa cálculo intermediário em `long`, e a cura limita soma ao PV máximo sem overflow; resultado preserva a regra de divisão inteira e valor mínimo 1.
- 1.1.4 foi commitada e tagueada como `mvp-v0.1.4`.
- 1.1.5: `calcularDano` aplica a fórmula D4 e somente calcula o valor; o PV do alvo permanece inalterado.
- 1.1.5 foi commitada e tagueada como `mvp-v0.1.5`.
- 1.1.6: `atacar(Personagem)` e `getNomeAtaqueBasico()` são abstratos; `resumoDeStatus()` usa os getters e produziu exatamente `Saci (Vento) | PV 60/60 | ATQ 10 | DEF 6`.
- Commits e tags do Bloco 1.1, respectivamente: `04e7098`/`mvp-v0.1.1`, `2b03cee`/`mvp-v0.1.2`, `757bcc6`/`mvp-v0.1.3`, `933e9fb`/`mvp-v0.1.4`, `7b32921`/`mvp-v0.1.5` e `ec72b3a`/`mvp-v0.1.6`. Todas as tags anotadas foram conferidas apontando para o commit da respectiva tarefa.
- Validação geral do bloco: `mvn -q clean compile` passou. Worktree limpo após o commit deste relatório, conferido por `git status`. Não houve push.
- Próximo bloco pendente: Fase 1 / Bloco 1.2. Aguardar autorização explícita.
