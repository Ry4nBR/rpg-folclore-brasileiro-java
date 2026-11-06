# Progresso de execução — MVP RPG Folclore

## Estado atual

- **Fase:** 4 — Implementação do chefe e confronto principal
- **Bloco:** 4.1 — Mula sem Cabeça
- **Última tarefa concluída:** 3.3.1 — Criar `CatalogoDeHerois`
- **Tarefa em execução:** 4.1.1 — Criar `MulaSemCabeca` com status base e ataque básico
- **Próxima tarefa pendente:** 4.1.2 — Adicionar Galope Infernal e o comportamento do chefe
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
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 2.1.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 2.2.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 2.3.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 3.1.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 3.2.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 3.3.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 4.1.

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
| 1.2.1 — Criar `ResultadoAcao` e a interface `Habilidade` | Concluída | `d0be221` — `feat: criacao de resultadoAcao e da interface habilidade` | `mvp-v0.1.7` | `mvn -q compile` passou; JShell confirmou criação, getters, campos finais e ausência de setters |
| 1.2.2 — Criar `HabilidadeBase` com controle de usos | Concluída | `ae67467` — `feat: classe abstrata habilidadeBase com controle de usos` | `mvp-v0.1.8` | `mvn -q compile` passou; JShell validou contador, consumo até zero, exceção ao exceder e rejeição de usos não positivos |
| 1.2.3 — Implementar `HabilidadeDano` | Concluída | `b12e478` — `feat: implementacao da habilidade de dano` | `mvp-v0.1.9` | `mvn -q compile` passou; JShell validou dois danos, usos consumidos, mensagem e terceira falha sem alterar o alvo |
| 1.2.4 — Implementar `HabilidadeCura` | Concluída | `36f6672` — `feat: implementacao da habilidade de cura` | `mvp-v0.1.10` | `mvn -q compile` passou; JShell validou cura de 12 PV saindo de 40, cura efetiva de 2 saindo de 58, alvo ignorado e falha sem usos |
| 1.2.5 — Vincular uma habilidade ao `Personagem` | Concluída | `b70026e` — `feat: habilidade vinculada ao personagem com metodo usarHabilidade` | `mvp-v0.1.11` | `mvn -q compile` passou; JShell validou execução via `usarHabilidade`, habilidade privada, rejeição de nulo e erro sem habilidade |
| 2.1.1 — Criar o enum `EstadoBatalha` | Concluída | `7d58fac` — `feat: enum de estados da batalha` | `mvp-v0.2.1` | `mvn -q compile` passou; JShell confirmou exatamente os quatro estados |
| 2.1.2 — Criar `Batalha` com estado, turno e registro de mensagens | Concluída | `86d14db` — `feat: criacao da classe batalha com estado turno e mensagens` | `mvp-v0.2.2` | `mvn -q compile` e `mvn -q clean compile` passaram; JShell validou referências, valores iniciais, mensagem exata, cópia/limpeza da fila e rejeição de nulos |
| 2.2.1 — Implementar a ação Atacar e a checagem de vitória | Concluída | `5bf999b` — `feat: acao atacar na batalha com verificacao de vitoria` | `mvp-v0.2.3` | `mvn -q compile` passou; JShell validou dano, mensagem, estado após ataque, vitória e rejeição fora do estado |
| 2.2.2 — Implementar a ação Habilidade | Concluída | `86546f7` — `feat: acao habilidade na batalha com limite de usos` | `mvp-v0.2.4` | `mvn -q compile` passou; JShell validou habilidades de dano e cura, consumo de uso, mensagens, estado, recusa sem usos e ausência de consumo de turno |
| 2.3.1 — Criar o gancho `deveUsarHabilidade` em `Personagem` | Concluída | `211cc78` — `feat: gancho deveUsarHabilidade na classe personagem` | `mvp-v0.2.5` | `mvn -q compile` passou; JShell confirmou retorno `false` para personagens comuns em turnos variados |
| 2.3.2 — Implementar o turno do chefe e a checagem de derrota | Concluída | `fe938de` — `feat: turno do chefe e verificacao de derrota na batalha` | `mvp-v0.2.6` | `mvn -q compile` passou; JShell validou ataque básico, habilidade indicada pelo polimorfismo, retorno ao turno do jogador, derrota e estado inválido |
| 2.3.3 — Criar `ResultadoBatalha` e consulta de fim de combate | Concluída | `d0e5c96` — `feat: resultado de batalha e consulta de encerramento` | `mvp-v0.2.7` | `mvn -q compile` passou; JShell confirmou estado em andamento, vitória e derrota |
| 3.1.1 — Criar a classe `Saci` com status base e ataque básico | Concluída | `7590b7e` — `feat: implementacao do personagem saci com ataque basico` | `mvp-v0.3.1` | `mvn -q compile` passou; JShell confirmou resumo, ataque e dano de 10 contra defesa 6 |
| 3.1.2 — Adicionar a habilidade Ventos Revigorantes ao Saci | Concluída | `0a91078` — `feat: habilidade ventos revigorantes do saci` | `mvp-v0.3.2` | `mvn -q compile` passou; JShell confirmou cura de 12 PV (40→52), três usos consumidos e falha no uso seguinte |
| 3.2.1 — Criar a classe `Lobisomem` com status base e ataque básico | Concluída | `d07c905` — `feat: implementacao do personagem lobisomem com ataque basico` | `mvp-v0.3.3` | `mvn -q compile` passou; JShell confirmou resumo, ataque e dano 14 contra defesa 5 |
| 3.2.2 — Adicionar a habilidade Uivo Brutal ao Lobisomem | Concluída | `1dcea68` — `feat: habilidade uivo brutal do lobisomem` | `mvp-v0.3.4` | `mvn -q compile` passou; JShell confirmou dano 19, dois usos e falha na terceira tentativa |
| 3.3.1 — Criar `CatalogoDeHerois` | Concluída | `9e23d6e` — `feat: catalogo dos herois jogaveis` | `mvp-v0.3.5` | `mvn -q compile` e `mvn -q clean compile` passaram; JShell confirmou dois itens na ordem Saci/Lobisomem, instâncias independentes, PV cheio e usos restaurados |
| 4.1.1 — Criar `MulaSemCabeca` com status base e ataque básico | Concluída | `feat: implementacao do chefe mula sem cabeca com ataque basico` | `mvp-v0.4.1` | `mvn -q compile` passou; JShell confirmou atributos, ataque Coice Flamejante e dano 8 contra defesa 6 |
| 4.1.2 — Adicionar Galope Infernal e o comportamento do chefe | Pendente | `feat: habilidade galope infernal e decisao de turno do chefe` | `mvp-v0.4.2` | Pendente |

## Histórico do bloco 3.3

- 3.3.1 foi commitada em `9e23d6e` e recebeu a tag anotada `mvp-v0.3.5`.
- Validação final do Bloco 3.3: `mvn -q clean compile` e `git diff --check` passaram; a tag anotada foi conferida apontando para o commit da tarefa. JShell validou tamanho e ordem do catálogo, novas instâncias por chamada, PV cheio, habilidades com usos restaurados e independência de estado.
- Arquivo de código criado: `src/main/java/br/folclore/modelo/CatalogoDeHerois.java`.
- Não houve push. O próximo bloco pendente é 4.1 e aguarda autorização explícita.

## Histórico do bloco 3.2

- 3.2.1 foi commitada em `d07c905` e recebeu a tag anotada `mvp-v0.3.3`.
- 3.2.2 foi commitada em `1dcea68` e recebeu a tag anotada `mvp-v0.3.4`.
- Validação final do Bloco 3.2: `mvn -q clean compile` e `git diff --check` passaram; as duas tags anotadas foram conferidas apontando para os commits das respectivas tarefas. JShell confirmou resumo, ataque básico, dano da habilidade, usos e falha ao esgotar.
- Arquivo de código criado/modificado: `src/main/java/br/folclore/modelo/Lobisomem.java`.
- Não houve push. O próximo bloco pendente é 3.3 e aguarda autorização explícita.

## Histórico do bloco 3.1

- 3.1.1 foi commitada em `7590b7e` e recebeu a tag anotada `mvp-v0.3.1`.
- 3.1.2 foi commitada em `0a91078` e recebeu a tag anotada `mvp-v0.3.2`.
- Validação final do Bloco 3.1: `mvn -q clean compile` e `git diff --check` passaram; as duas tags anotadas foram conferidas apontando para os commits das respectivas tarefas. JShell validou status, ataque básico, cura e usos da habilidade.
- Arquivo de código criado/modificado: `src/main/java/br/folclore/modelo/Saci.java`.
- Não houve push. O próximo bloco pendente é 3.2 e aguarda autorização explícita.

## Histórico do bloco 2.3

- 2.3.1 foi commitada em `211cc78` e recebeu a tag anotada `mvp-v0.2.5`.
- 2.3.2 foi commitada em `fe938de` e recebeu a tag anotada `mvp-v0.2.6`.
- 2.3.3 foi commitada em `d0e5c96` e recebeu a tag anotada `mvp-v0.2.7`.
- Validação final do Bloco 2.3: `mvn -q clean compile` e `git diff --check` passaram; as três tags anotadas foram conferidas apontando para os commits das respectivas tarefas. JShell cobriu o comportamento padrão do personagem, os caminhos de ação do chefe, vitória, derrota e consulta de encerramento.
- Arquivos modificados: `src/main/java/br/folclore/modelo/Personagem.java`, `src/main/java/br/folclore/batalha/Batalha.java` e `src/main/java/br/folclore/batalha/ResultadoBatalha.java`.
- Não houve push. O próximo bloco pendente é Fase 3 / Bloco 3.1 e aguarda autorização explícita.

## Histórico do bloco 2.2

- 2.2.1 foi commitada em `5bf999b` e recebeu a tag anotada `mvp-v0.2.3`.
- 2.2.2 foi commitada em `86546f7` e recebeu a tag anotada `mvp-v0.2.4`.
- Validação final do Bloco 2.2: `mvn -q clean compile` e `git diff --check` passaram; as tags anotadas foram conferidas apontando para os commits das respectivas tarefas.
- Arquivo de código modificado: `src/main/java/br/folclore/batalha/Batalha.java`. O progresso deste bloco está registrado neste documento.
- Não houve push. O próximo bloco pendente é 2.3 e aguarda autorização explícita.

## Histórico do bloco 2.1

- 2.1.1 foi commitada em `7d58fac` e recebeu a tag anotada `mvp-v0.2.1`.
- 2.1.2 foi commitada em `86d14db` e recebeu a tag anotada `mvp-v0.2.2`.
- Validação final do Bloco 2.1: `mvn -q clean compile` e `git diff --check` passaram; as tags anotadas foram conferidas apontando para os commits das respectivas tarefas. O teste JShell da mensagem foi executado com escapes Unicode para contornar a codificação do pipe do terminal.
- Arquivos criados: `src/main/java/br/folclore/batalha/EstadoBatalha.java` e `src/main/java/br/folclore/batalha/Batalha.java`.
- Não houve push. O próximo bloco pendente é 2.2 e aguarda autorização explícita.

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
- Ferramentas de build não estão instaladas globalmente; para as validações foram usadas distribuições portáteis JDK 17 e Maven 3.9.9 com checksums oficiais conferidos. Os arquivos temporários foram removidos ao final.
- 1.2.1: criado `ResultadoAcao` imutável com enum `Efeito`, valor e mensagem, além do contrato `Habilidade`.
- 1.2.1 foi commitada e tagueada como `mvp-v0.1.7`.
- 1.2.2: criado `HabilidadeBase` abstrato, com nome e limite fixos, contador inicializado e consumo protegido contra usos abaixo de zero.
- 1.2.2 foi commitada e tagueada como `mvp-v0.1.8`.
- 1.2.3: criado `HabilidadeDano`; execução sem usos retorna FALHA com valor zero e não altera PV nem contador.
- 1.2.3 foi commitada e tagueada como `mvp-v0.1.9`.
- 1.2.4: criado `HabilidadeCura`; a habilidade cura o usuário, ignora o alvo e retorna FALHA sem consumir uso quando esgotada.
- 1.2.4 foi commitada e tagueada como `mvp-v0.1.10`.
- 1.2.5: `Personagem` agora mantém a habilidade privada, permite definição não nula por subclasses e delega seu uso, lançando `IllegalStateException` quando não houver habilidade.
- Commits e tags do Bloco 1.2: `d0be221`/`mvp-v0.1.7`, `ae67467`/`mvp-v0.1.8`, `b12e478`/`mvp-v0.1.9`, `36f6672`/`mvp-v0.1.10` e `b70026e`/`mvp-v0.1.11`. Todas as tags anotadas foram conferidas apontando para os commits das tarefas.
- Validação geral do bloco: `mvn -q clean compile` passou. Não houve push; ferramentas e arquivos de download temporários foram removidos após as validações.
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
