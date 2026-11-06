# Progresso de execução — MVP RPG Folclore

## Estado atual

- **Fase:** 6 — Integração do combate
- **Bloco:** 6.2 — Ações do jogador
- **Última tarefa concluída:** 6.2.3 — Criar o botão Habilidade e ligá-lo à batalha
- **Tarefa em execução:** Nenhuma
- **Próxima tarefa pendente:** 6.3.1 — Disparar o turno do chefe automaticamente
- **Status do bloco:** Bloco 6.2 em execução; demais tarefas da Fase 6 autorizadas
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
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 4.2.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 5.1.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 5.2.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 5.3.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 5.4.
- O usuário autorizou continuar para o Bloco 6.1.
- Em 06/11/2026, o usuário autorizou continuar para o Bloco 5.5.
- Em 06/11/2026, o usuário autorizou executar todos os blocos restantes da Fase 6.

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
| 4.1.1 — Criar `MulaSemCabeca` com status base e ataque básico | Concluída | `40f35eb` — `feat: implementacao do chefe mula sem cabeca com ataque basico` | `mvp-v0.4.1` | `mvn -q compile` passou; JShell confirmou atributos, ataque Coice Flamejante e dano 8 contra defesa 6 |
| 4.1.2 — Adicionar Galope Infernal e o comportamento do chefe | Concluída | `8f40986` — `feat: habilidade galope infernal e decisao de turno do chefe` | `mvp-v0.4.2` | `mvn -q compile` e `mvn -q clean compile` passaram; JShell confirmou uso nos turnos pares enquanto há usos e retorno `false` após os dois usos |
| 4.2.1 — Criar `DemoPilares` para demonstrar o polimorfismo | Concluída | `cd33c97` — `test: demo de console dos pilares de poo` | `mvp-v0.4.3` | `mvn -q compile exec:java -Dexec.mainClass=br.folclore.demo.DemoPilares` passou; saída mostrou as três subclasses, ataques, danos e mensagens de habilidade diferentes |
| 4.2.2 — Criar `DemoBatalhaVitoria` | Concluída | `d3f496f` — `test: demo de batalha com vitoria dos dois herois` | `mvp-v0.4.4` | `mvn -q compile exec:java -Dexec.mainClass=br.folclore.demo.DemoBatalhaVitoria` passou; Saci venceu em 5 turnos com 18 PV e Lobisomem em 4 turnos |
| 4.2.3 — Criar `DemoBatalhaDerrota` | Concluída | `test: demo de batalha com derrota e habilidade sem usos` | `mvp-v0.4.5` | `mvn -q compile exec:java -Dexec.mainClass=br.folclore.demo.DemoBatalhaDerrota` passou; saída imprime `DERROTA` e `Sem usos restantes de Uivo Brutal!`; a terceira ação foi recusada sem alterar o estado |
| 5.1.1 — Criar janela principal, painel de desenho e loop | Concluída | `52cd8f7` — `feat: janela principal com painel e loop de renderizacao` | `mvp-v0.5.1` | `mvn -q clean compile` passou; probe Swing via `Main` confirmou janela visível com título correto, painel 960x540, fundo preto e fechamento |
| 5.1.2 — Criar `Tela`, `GerenciadorDeTelas` e o repasse de eventos | Concluída | `e4eec44` — `feat: interface tela e gerenciador de telas com repasse de eventos` | `mvp-v0.5.2` | `mvn -q clean compile` passou; probe Swing confirmou chamadas de atualização, desenho, clique, movimento e tecla; gerenciador sem tela atual não lança erro |
| 5.1.3 — Criar o componente `Botao` | Concluída | `559d86f` — `feat: criacao do componente botao com hover e estado habilitado` | `mvp-v0.5.3` | `mvn -q clean compile` passou; tela temporária confirmou hover, clique habilitado e botão desabilitado em cinza que ignora clique |
| 5.2.1 — Criar `TelaInicial` com Jogar e Sair | Concluída | `77fc9a9` — `feat: tela inicial com botoes jogar e sair` | `mvp-v0.5.4` | `mvn -q clean compile` passou; probe confirmou fundo, hover, clique em Jogar, ENTER e encerramento com código 0 ao acionar Sair |
| 5.3.1 — Desenhar o layout da `TelaSelecaoPersonagem` | Concluída | `f2cd2c6` — `feat: layout da tela de selecao de personagem` | `mvp-v0.5.5` | `mvn -q clean compile` passou; probe confirmou cartões com atributos D2, habilidades/usos D3 e figuras ciano/laranja |
| 5.3.2 — Programar seleção, Confirmar e Voltar | Concluída | `119d534` — `feat: selecao confirmacao e retorno na tela de personagem` | `mvp-v0.5.6` | `mvn -q clean compile` passou; probe confirmou confirmação bloqueada sem seleção, escolha e confirmação correta por mouse/setas/ENTER, borda amarela, hover e retorno por ESC/botão |
| 5.3.3 — Ligar o menu à seleção de personagem | Concluída | `4783af3` — `feat: navegacao entre menu e selecao de personagem` | `mvp-v0.5.7` | `mvn -q clean compile` passou; três ciclos reais Menu → Seleção → Confirmar → Voltar imprimiram Saci, Lobisomem e Saci |
| 5.4.1 — Criar o componente `BarraDeVida` | Concluída | `fb647bc` — `feat: componente barra de vida com cores por faixa` | `mvp-v0.5.8` | `mvn -q clean compile` passou; probe confirmou 60/60 verde, 25/60 amarela, 5/60 vermelha e PV máximo zero sem divisão por zero |
| 5.4.2 — Criar o componente `CaixaDeMensagens` | Concluída | `feat: componente caixa de mensagens com quebra de linha` | `mvp-v0.5.9` | `mvn -q clean compile` passou; probe com texto de 200 caracteres confirmou quebra em múltiplas linhas e nenhum desenho fora da caixa |
| 5.4.3 — Desenhar o layout da `TelaBatalha` | Concluída | `134e541` — `feat: layout base da tela de batalha` | `mvp-v0.5.10` | `mvn -q clean compile` passou; probe confirmou degradê, combatentes coloridos, HUDs com PV cheio e nenhuma alteração ao estado da batalha |
| 5.5.1 — Criar a `TelaResultado` | Concluída | `a318ef2` — `feat: tela de resultado com vitoria e derrota` | `mvp-v0.5.11` | `mvn -q clean compile` passou; probe confirmou títulos coloridos, retry apenas na derrota e callbacks de menu/reinício |
| 6.1.1 — Abrir a `TelaBatalha` ao confirmar o personagem | Concluída | `09c3594` — `feat: abertura da batalha a partir da selecao de personagem` | `mvp-v0.6.1` | Saci e Lobisomem abrem a batalha com personagem correto e PV cheio |
| 6.2.1 — Exibir as mensagens da batalha em fila | Concluída | `638fe63` — `feat: fila de mensagens de combate na tela de batalha` | `mvp-v0.6.2` | `mvn -q clean compile`, probe da abertura e avanço por ENTER, ESPAÇO e clique, `git diff --check` |
| 6.2.2 — Criar o botão Atacar e ligá-lo à batalha | Concluída | `fe14c17` — `feat: botao atacar na tela de batalha` | `mvp-v0.6.3` | `mvn -q clean compile`; clique reduz PV do chefe, atualiza estado e desabilita o botão até o fim da fila; `git diff --check` |
| 6.2.3 — Criar o botão Habilidade e ligá-lo à batalha | Concluída | `feat: botao habilidade com contador de usos na tela de batalha` | `mvp-v0.6.4` | `mvn -q clean compile`; probe validou cura do Saci e contadores 3→2→0, Lobisomem 2→1, bloqueio sem usos/mensagens e rótulo dentro do botão; `git diff --check` |

## Histórico do bloco 6.1

- 6.1.1 foi commitada em `09c3594` (`feat: abertura da batalha a partir da selecao de personagem`) e recebeu a tag anotada `mvp-v0.6.1`, apontando para esse commit.
- Validação final: `mvn -q clean compile` e `git diff --check` passaram. Teste integrado pela seleção confirmou que Saci e Lobisomem abrem `TelaBatalha` com o personagem correto, PV atual igual ao máximo para jogador e chefe, e HUDs cheios renderizados.
- Arquivo modificado: `src/main/java/br/folclore/ui/JanelaPrincipal.java`.
- Não houve push. O próximo bloco pendente é 6.2 e aguarda autorização explícita.

## Histórico do bloco 5.5

- 5.5.1 foi commitada em `a318ef2` e recebeu a tag anotada `mvp-v0.5.11`.
- Validação final do Bloco 5.5: `mvn -q clean compile`, `git diff --check` e teste dos dois modos de resultado, visibilidade do botão de reinício e execução dos callbacks passaram.
- Arquivo criado: `src/main/java/br/folclore/ui/telas/TelaResultado.java`.
- Não houve push. A próxima fase/bloco aguarda autorização explícita.

## Histórico do bloco 5.4

- 5.4.1 foi commitada em `fb647bc` e recebeu a tag anotada `mvp-v0.5.8`.
- 5.4.2 foi commitada em `5dd605f` e recebeu a tag anotada `mvp-v0.5.9`.
- 5.4.3 foi commitada em `134e541` e recebeu a tag anotada `mvp-v0.5.10`.
- Validação final do Bloco 5.4: `mvn -q clean compile`, `git diff --check`, testes visuais das faixas de PV e quebra de texto de 200 caracteres, e probe da tela de batalha confirmando os dois HUDs cheios e estado de combate inalterado.
- Arquivos criados no bloco: `src/main/java/br/folclore/ui/componentes/BarraDeVida.java`, `src/main/java/br/folclore/ui/componentes/CaixaDeMensagens.java` e `src/main/java/br/folclore/ui/telas/TelaBatalha.java`.
- Não houve push. O próximo bloco pendente é 5.5 e aguarda autorização explícita.

## Histórico do bloco 5.3

- 5.3.1 foi commitada em `f2cd2c6` e recebeu a tag anotada `mvp-v0.5.5`.
- 5.3.2 foi commitada em `119d534` e recebeu a tag anotada `mvp-v0.5.6`.
- 5.3.3 foi commitada em `4783af3` e recebeu a tag anotada `mvp-v0.5.7`.
- Validação final do Bloco 5.3: `mvn -q clean compile`, `git diff --check`, conferência de atributos e habilidades dos dois cartões e três ciclos reais de navegação pela janela passaram.
- Arquivos criados/modificados no bloco: `src/main/java/br/folclore/ui/telas/TelaSelecaoPersonagem.java`, `src/main/java/br/folclore/ui/JanelaPrincipal.java` e `src/main/java/br/folclore/ui/PainelDoJogo.java` (injeção do gerenciador compartilhado).
- Não houve push. O próximo bloco pendente é 5.4 e aguarda autorização explícita.

## Histórico do bloco 5.2

- 5.2.1 foi commitada em `77fc9a9` e recebeu a tag anotada `mvp-v0.5.4`.
- Validação final do Bloco 5.2: `mvn -q clean compile` e `git diff --check` passaram; testes Swing confirmaram o fundo verde-escuro, destaque de hover, ação de Jogar por clique e ENTER e encerramento limpo ao clicar em Sair.
- Arquivos criados/modificados no bloco: `src/main/java/br/folclore/ui/telas/TelaInicial.java` e `src/main/java/br/folclore/ui/JanelaPrincipal.java`.
- Não houve push. O próximo bloco pendente é 5.3 e aguarda autorização explícita.

## Histórico do bloco 5.1

- 5.1.1 foi commitada em `52cd8f7` e recebeu a tag anotada `mvp-v0.5.1`.
- 5.1.2 foi commitada em `e4eec44` e recebeu a tag anotada `mvp-v0.5.2`.
- 5.1.3 foi commitada em `559d86f` e recebeu a tag anotada `mvp-v0.5.3`.
- Validação final do Bloco 5.1: `mvn -q clean compile` e `git diff --check` passaram; testes Swing confirmaram janela 960x540, renderização preta, atualização/desenho, repasse de clique/movimento/tecla e comportamento visual e funcional dos botões.
- Arquivos criados/modificados no bloco: `src/main/java/br/folclore/Main.java`, `src/main/java/br/folclore/ui/ConfiguracoesUi.java`, `src/main/java/br/folclore/ui/JanelaPrincipal.java`, `src/main/java/br/folclore/ui/PainelDoJogo.java`, `src/main/java/br/folclore/ui/GerenciadorDeTelas.java`, `src/main/java/br/folclore/ui/telas/Tela.java` e `src/main/java/br/folclore/ui/componentes/Botao.java`.
- Não houve push. O próximo bloco pendente é 5.2 e aguarda autorização explícita.

## Histórico do bloco 4.2

- 4.2.1 foi commitada em `cd33c97` e recebeu a tag anotada `mvp-v0.4.3`.
- 4.2.2 foi commitada em `d3f496f` e recebeu a tag anotada `mvp-v0.4.4`.
- 4.2.3 foi commitada com a mensagem `test: demo de batalha com derrota e habilidade sem usos` e recebeu a tag anotada `mvp-v0.4.5`.
- Validação final do Bloco 4.2: `mvn -q clean compile`, execução dos três demos com conferência das saídas previstas e `git diff --check` passaram.
- Arquivos criados/modificados no bloco: `src/main/java/br/folclore/demo/DemoPilares.java`, `src/main/java/br/folclore/demo/DemoBatalhaVitoria.java`, `src/main/java/br/folclore/demo/DemoBatalhaDerrota.java` e `pom.xml` (classe principal do exec Maven configurável).
- Não houve push. O próximo bloco pendente é 5.1 e aguarda autorização explícita.

## Histórico do bloco 4.1

- 4.1.1 foi commitada em `40f35eb` e recebeu a tag anotada `mvp-v0.4.1`.
- 4.1.2 foi commitada em `8f40986` e recebeu a tag anotada `mvp-v0.4.2`.
- Validação final do Bloco 4.1: `mvn -q clean compile` e `git diff --check` passaram; as duas tags anotadas foram conferidas apontando para os commits das respectivas tarefas. JShell validou os atributos e o ataque básico do chefe, além da regra determinística e do esgotamento do Galope Infernal.
- Arquivo de código criado/modificado: `src/main/java/br/folclore/modelo/MulaSemCabeca.java`.
- Não houve push. O próximo bloco pendente é 4.2 e aguarda autorização explícita.

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
