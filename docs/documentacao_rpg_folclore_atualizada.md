# Documento do Projeto — RPG de Batalhas do Folclore Brasileiro

**Versão:** 0.2 — Atualizado com as respostas do questionário  
**Status:** Escopo principal definido; detalhes de balanceamento e implementação ainda serão especificados.

---

## 1. Visão geral

O projeto será um RPG 2D de batalhas por turnos inspirado na estrutura de combate dos jogos de Pokémon, com personagens e criaturas do folclore brasileiro. Será executado em uma janela desktop e terá arte 2D pixelada.

A linguagem será **Java ou C#**. JavaScript está descartado. A equipe poderá usar ferramentas auxiliares, bibliotecas ou recursos de IA desde que consiga explicar como funcionam e como foram aplicados. O projeto pode começar do zero ou partir de uma base existente.

O objetivo acadêmico é demonstrar claramente os quatro pilares da Programação Orientada a Objetos (POO): abstração, encapsulamento, herança e polimorfismo.

- **Equipe:** dupla.
- **Entregável:** protótipo funcional.
- **Prazo informado:** de 23/11 a 27/11; prova no dia 27/11.
- **Documentação:** Markdown e Mermaid, em português, junto ao código.
- **Versionamento:** GitHub.

> **Pendente:** ainda é necessário escolher entre Java e C#. O ano do prazo informado também não foi explicitado nas respostas.

---

## 2. Objetivos e direção visual

### 2.1 Objetivos principais

1. Criar um RPG 2D com batalhas por turnos.
2. Utilizar personagens e criaturas do folclore brasileiro.
3. Implementar habilidades com efeitos visuais e animações.
4. Desenvolver uma história principal e três ginásios.
5. Demonstrar os quatro pilares da POO de maneira clara.
6. Entregar um protótipo funcional dentro do prazo.
7. Manter a documentação técnica em Markdown e diagramas Mermaid.
8. Organizar o trabalho em tarefas sequenciais, agrupadas em sprints de uma semana.

### 2.2 Estilo visual e áudio

- Arte **2D pixelada**.
- Personagens em poses de batalha de corpo inteiro, no estilo Pokémon.
- Diálogos em caixas de texto com retratos.
- Animações que combinem imagens prontas e efeitos produzidos pelo programa.
- Efeitos sonoros e música.
- A equipe pretende usar IA para criar recursos visuais.

Não foi informada uma restrição específica para resolução, tamanho ou formato dos arquivos. Se necessário, a equipe poderá estabelecer limites para manter o desempenho e o escopo viáveis.

---

## 3. Estrutura do jogo

- **Gênero:** RPG 2D com batalhas por turnos.
- **Plataforma:** janela desktop.
- **Exploração:** não haverá mapa aberto na proposta atual.
- **Progressão:** história, diálogos, encontros comuns, experiência e ginásios.
- **Idiomas:** português brasileiro e inglês.
- **Inimigos:** controlados por IA.
- **Telas previstas:** tela inicial, seleção de personagem, batalha, diálogos, ginásios, vitória, derrota e créditos.

O jogo terá uma história principal. A tela inicial deverá oferecer opções como **Novo Jogo**, **Continuar** e **Sair**.

### 3.1 Ponto de recuperação e portas dos ginásios

Haverá um ponto de recuperação chamado provisoriamente de **spawn médico**, onde os personagens poderão ser revigorados após cada batalha, antes de seguir para o próximo ginásio.

A partir desse ponto haverá portas, uma para cada ginásio. As portas serão abertas de acordo com o progresso do jogador.

---

## 4. Personagens e equipe

### 4.1 Personagens iniciais

No início da partida, o jogador escolherá um dos três personagens:

| Personagem | Tipo de habilidade | Conceito |
|---|---|---|
| Saci | Vento | Habilidades associadas ao vento e a redemoinhos |
| Lobisomem | Físico | Ataques físicos |
| Negrinho do Pastoreio | Psíquico | Capacidade de manipular objetos perdidos |

Os três serão personagens jogáveis iniciais e deverão apresentar diferenças claras de habilidades e estilo de combate.

### 4.2 Criaturas elementais

| Elemento | Criaturas planejadas |
|---|---|
| Fogo | Mãe de Ouro, Batatão, Mula sem Cabeça |
| Planta | Curupira, Caipora, Capelobo |
| Água | Iara, Boto-cor-de-rosa, Honorato |

O elenco planejado totaliza **12 personagens/criaturas**: três iniciais e nove criaturas elementais. Todos poderão aparecer como inimigos, e cada inimigo será único.

Ainda será necessário definir os atributos, as habilidades, os encontros e a distribuição de cada criatura pelos ginásios.

### 4.3 Composição da equipe

A equipe terá os três personagens iniciais e **um espaço adicional**. O jogador poderá trocar de personagem. Ainda precisa ser definida a forma de preencher o quarto espaço e como a troca funcionará durante a campanha e as batalhas.

---

## 5. Ginásios e progressão

O jogo terá três ginásios, em ordem fixa:

1. Ginásio do Fogo;
2. Ginásio da Planta;
3. Ginásio da Água.

Cada ginásio terá um chefe (*boss*). A criatura escolhida como chefe de cada ginásio ainda precisa ser definida.

### 5.1 Poderes e espaços de habilidade

As regras já estabelecidas são:

- Cada vitória contra um inimigo libera um poder.
- Cada inimigo será único.
- A vitória em um ginásio libera um espaço de habilidade.
- A variedade de habilidades será desbloqueada por XP.
- Depois de liberar quatro poderes, o jogador poderá trocar os poderes equipados/fixos.
- Cada personagem terá quatro habilidades no total, contando o ataque básico.

A relação exata entre os poderes liberados por inimigos, os espaços desbloqueados por ginásios e a variedade de habilidades desbloqueada por XP ainda precisa ser detalhada.

### 5.2 Experiência e melhorias

O sistema utilizará experiência (XP) como parte do balanceamento. O jogador poderá escolher quais atributos melhorar, e as melhorias serão permanentes durante a campanha.

Ainda serão definidos:

- Quantidade de XP recebida por encontro;
- Requisitos de XP para desbloquear habilidades;
- Custos de melhoria de atributos;
- Valores de aumento dos atributos;
- Se o XP também determina níveis numéricos de personagem.

As fórmulas devem ser simples e fáceis de explicar na apresentação.

---

## 6. Sistema de combate

### 6.1 Fluxo de turno

A ordem dos turnos será **fixa**. Em linhas gerais:

1. A interface mostra os personagens e seus estados.
2. O jogador escolhe uma ação.
3. O jogo executa a ação e a animação correspondente.
4. O dano ou efeito é aplicado.
5. A IA executa a ação do inimigo.
6. O jogo verifica vitória, derrota ou continuidade.
7. A batalha termina ou começa o próximo turno.

A velocidade poderá existir como atributo, mas não mudará a ordem dos turnos enquanto a regra de ordem fixa permanecer.

### 6.2 Ações disponíveis

1. **Atacar:** executar o ataque básico.
2. **Habilidade:** selecionar um poder desbloqueado.
3. **Ver status:** consultar os atributos do inimigo.
4. **Fugir:** disponível em encontros comuns.

As habilidades terão limite de uso. A quantidade de usos e a forma de recuperação ainda precisam ser definidas.

### 6.3 Atributos e cálculo

A lista inicial de atributos a considerar é:

- Pontos de vida (PV);
- Ataque;
- Defesa;
- Velocidade.

A lista final e as fórmulas de dano, defesa, cura e experiência ainda não estão fechadas. O sistema deve ser simples, consistente e fácil de explicar.

### 6.4 Efeitos das habilidades

As habilidades poderão causar dano e aplicar efeitos adicionais. Um exemplo definido é **ventos revigorantes**, que recuperam **5% da vida máxima**.

Outros efeitos e valores serão especificados posteriormente. As animações deverão acompanhar a lógica da batalha para que dano e cura sejam aplicados no momento correto.

### 6.5 IA inimiga

Os inimigos agirão automaticamente. A IA deverá selecionar ações de acordo com regras que a equipe consiga explicar. A lógica exata de escolha de ataques, habilidades e alvos ainda precisa ser definida.

### 6.6 Derrota e recuperação

Ao perder uma batalha, o jogador poderá recomeçar de algum ponto. O ponto exato de retorno e as consequências da derrota ainda precisam ser definidos.

Após cada batalha, o spawn médico permitirá revigorar os personagens antes de seguir ao próximo ginásio.

---

## 7. Aplicação dos quatro pilares da POO

O professor confirmou que os pilares devem ser demonstrados claramente no projeto; não é necessário usar classes específicas indicadas previamente.

### 7.1 Abstração

Criar uma classe abstrata, por exemplo `Personagem`, para representar atributos e comportamentos comuns. Ela poderá definir operações como atacar, receber dano, curar e utilizar habilidades.

### 7.2 Encapsulamento

Manter atributos como vida, ataque, defesa, XP e habilidades protegidos, preferencialmente com atributos privados e métodos públicos controlados.

Exemplos conceituais: `receberDano()`, `curar()`, `ganharExperiencia()` e métodos para consultar atributos. Os métodos deverão impedir valores inválidos.

### 7.3 Herança

Criar classes especializadas a partir de uma classe base quando houver características e comportamentos compartilhados. A hierarquia final será definida após fechar atributos e habilidades.

### 7.4 Polimorfismo

Personagens diferentes deverão responder de formas diferentes a operações comuns. Por exemplo, o Saci poderá executar uma habilidade de vento, o Lobisomem um ataque físico e o Negrinho do Pastoreio uma habilidade psíquica, por meio de uma interface ou método comum com comportamentos específicos.

### 7.5 Evidências para a apresentação

A documentação deverá registrar:

- Quais classes demonstram cada pilar;
- Quais métodos e atributos são relevantes;
- Como o jogo utiliza esses elementos durante uma batalha;
- Um exemplo prático de cada pilar no código.

---

## 8. Tecnologias e restrições

### 8.1 Linguagem e execução

- Linguagens permitidas: **Java ou C#**.
- JavaScript: não será utilizado.
- Execução: janela desktop.
- Base inicial: pode começar do zero ou utilizar um projeto-base.

A linguagem final ainda precisa ser escolhida. O README de referência fornecido anteriormente descreve um projeto Java com Swing e Graphics2D, mas isso não obriga a equipe a usar aquela base.

### 8.2 Ferramentas e bibliotecas

É permitido usar recursos auxiliares, desde que a equipe entenda e explique como funcionam e como foram aplicados. O uso de IA também foi autorizado.

As restrições específicas a bibliotecas externas não foram detalhadas. Antes de selecionar dependências, a equipe deverá confirmar se existe algum limite adicional.

### 8.3 Recursos visuais e áudio

O projeto terá pixel art, retratos, poses de batalha de corpo inteiro, efeitos visuais, música e efeitos sonoros. A produção dos recursos poderá combinar IA, imagens prontas e efeitos gerados pelo programa.

---

## 9. Organização do desenvolvimento

O projeto será versionado no **GitHub** e documentado em arquivos Markdown em português, com diagramas Mermaid quando forem úteis.

### 9.1 Tarefas e sprints

O planejamento será uma lista sequencial de tarefas, organizadas em sprints de uma semana. As tarefas deverão indicar, conforme necessário:

- Objetivo;
- Descrição e instruções para implementação;
- Arquivos envolvidos;
- Responsável;
- Dependências;
- Critérios de aceite;
- Mensagem de commit sugerida.

Essas informações são consideradas indispensáveis para a documentação do projeto.

### 9.2 Trabalho em dupla e branches

A resposta sobre o uso de branches separadas foi “sim e não”, portanto não define uma estratégia clara. A equipe deverá decidir se cada integrante trabalhará em sua própria branch e como as alterações serão integradas antes de iniciar o desenvolvimento conjunto.

### 9.3 Prazo e entregável

- **Período informado:** 23/11 a 27/11.
- **Prova:** 27/11.
- **Entregável:** protótipo funcional.
- **Equipe:** dupla.

O ano não foi informado. Como o prazo é curto, a prioridade deve ser implementar primeiro o ciclo essencial de combate e progressão.

---

## 10. Fora do escopo inicial

Para manter o protótipo viável, ficam fora do escopo atual, salvo decisão posterior:

- Mapa aberto e exploração livre;
- Multiplayer;
- Inventário complexo;
- Missões secundárias;
- Conteúdo muito além do elenco inicial;
- Sistemas que não sejam necessários para demonstrar o ciclo principal de jogo e os quatro pilares da POO.

A prioridade é entregar um fluxo funcional de história, encontros, combate, progressão e ginásios.

---

## 11. Decisões confirmadas e pontos pendentes

### 11.1 Decisões confirmadas

| Tema | Decisão |
|---|---|
| Linguagem | Java ou C#; JavaScript descartado |
| Ferramentas auxiliares | Permitidas, desde que sejam explicadas |
| Projeto-base | Pode começar do zero ou utilizar uma base |
| Plataforma | Janela desktop |
| IA para código e recursos | Permitida |
| Equipe | Dupla |
| Prazo | 23/11 a 27/11; prova no dia 27/11 |
| Entregável | Protótipo funcional |
| Documentação | Markdown e Mermaid |
| POO | Demonstrar claramente os quatro pilares |
| Iniciais | Saci (vento), Lobisomem (físico), Negrinho do Pastoreio (psíquico) |
| Inimigos | Todos os personagens/criaturas podem aparecer como inimigos |
| Troca de personagem | Permitida |
| Ordem dos turnos | Fixa |
| Ginásios | Três, cada um com um chefe |
| Equipe | Três personagens iniciais e um espaço adicional |
| Habilidades | Quatro no total, incluindo o ataque básico |
| Usos | Habilidades terão limite de uso |
| Efeitos | Incluem cura de 5% da vida máxima por ventos revigorantes |
| Balanceamento | Fórmulas simples e explicáveis |
| Fuga | Permitida em encontros comuns |
| IA inimiga | Sim |
| Derrota | Permite recomeçar de algum ponto |
| Recuperação | Spawn médico após cada batalha, antes do próximo ginásio |
| Portas | Acesso aos ginásios liberado conforme o progresso |
| Poderes | Uma vitória contra inimigo libera um poder; após quatro poderes, será possível trocar os poderes equipados |
| Atributos | Jogador escolhe o que melhorar |
| XP | Usado no balanceamento e para desbloquear variedade de habilidades |
| Espaços de habilidade | Desbloqueados por vitórias em ginásios |
| Melhorias | Permanentes |
| Salvamento | O progresso poderá ser salvo e carregado |
| Arte | 2D pixelada |
| Poses de batalha | Corpo inteiro, no estilo Pokémon |
| Animações | Combinação de imagens prontas e efeitos produzidos pelo programa |
| Recursos visuais | Pretende-se usar IA |
| Áudio | Música e efeitos sonoros |
| Tela inicial | Novo Jogo, Continuar e Sair, entre opções previstas |
| Diálogos | Caixas de texto com retratos |
| História | História principal |
| Idiomas | Português brasileiro e inglês |
| Telas | Seleção, batalha, diálogo, ginásios, vitória, derrota e créditos |
| Versionamento | GitHub |
| Tarefas | Objetivo, arquivos, instruções, critérios de aceite e commit sugerido |
| Planejamento | Lista sequencial em sprints semanais |

### 11.2 Pontos pendentes

1. Escolher a linguagem final: Java ou C#.
2. Confirmar eventuais limites para bibliotecas externas.
3. Confirmar o ano correspondente ao prazo de 23/11 a 27/11.
4. Escolher o chefe de cada ginásio.
5. Definir como será preenchido o quarto espaço da equipe.
6. Detalhar a relação entre poderes recebidos por vitória, espaços liberados por ginásio e habilidades desbloqueadas por XP.
7. Definir os limites de uso das habilidades e quando serão restaurados.
8. Definir fórmulas de dano, cura, XP e melhoria de atributos.
9. Definir o ponto de retorno e as consequências após uma derrota.
10. Definir atributos, habilidades, IA e recompensas de cada inimigo.
11. Definir a estratégia de branches e integração no GitHub.
12. Definir formatos, resolução e tamanho dos recursos, se necessário.
13. Definir como o jogador alternará entre português e inglês.
14. Definir quais dados serão salvos e como o sistema de salvamento funcionará.
15. Escrever a sequência principal da história e seu desfecho no protótipo.

---

## 12. Próximos passos

Ordem recomendada de trabalho:

1. Escolher Java ou C#.
2. Criar o projeto desktop e definir sua estrutura inicial.
3. Fechar as regras de atributos, dano, cura, XP e limite de uso.
4. Escolher os chefes e distribuir os inimigos pelos encontros e ginásios.
5. Definir a história principal e o quarto espaço da equipe.
6. Criar o diagrama de classes em Mermaid, destacando os quatro pilares da POO.
7. Organizar as tarefas sequenciais em sprints semanais.
8. Implementar primeiro um ciclo mínimo de batalha funcional.
9. Adicionar progressão, ginásios, história, salvamento, idiomas, recursos visuais e áudio.
10. Testar o protótipo e preparar exemplos de código para a apresentação.

**Prioridade de execução:** entregar um protótipo funcional com combate por turnos, IA inimiga, progressão básica, três ginásios e demonstração clara da POO. Recursos adicionais devem ser incorporados somente quando o fluxo principal estiver funcionando.
