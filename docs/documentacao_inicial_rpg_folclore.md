# Documento Inicial do Projeto — RPG de Batalhas do Folclore Brasileiro

**Versão:** 0.1 — Documento preliminar  
**Status:** Ideia inicial; requisitos técnicos e algumas regras de jogo ainda precisam ser confirmados.

---

## 1. Visão geral

O projeto consiste em um RPG de batalhas por turnos inspirado no estilo de combate dos jogos de Pokémon para Game Boy Advance (GBA), mas com uma apresentação visual moderna e personagens inspirados no folclore brasileiro.

O jogo será desenvolvido como projeto acadêmico para demonstrar os quatro pilares da Programação Orientada a Objetos (POO):

- Abstração;
- Encapsulamento;
- Herança;
- Polimorfismo.

A proposta combina batalhas por turnos, habilidades próprias para cada personagem, progressão por ginásios e desbloqueio gradual de poderes e melhorias de atributos.

> **Observação técnica:** o README fornecido como referência descreve uma base em Java, utilizando Swing e Graphics2D. A linguagem exigida no projeto atual e as APIs gráficas permitidas ainda precisam ser confirmadas com o professor. Por isso, este documento não assume que a base existente poderá ser reutilizada.

---

## 2. Objetivos do projeto

### 2.1 Objetivos principais

1. Criar um jogo 2D de batalhas por turnos.
2. Utilizar personagens e criaturas inspirados no folclore brasileiro.
3. Implementar habilidades com efeitos visuais e animações.
4. Criar uma progressão baseada em três ginásios elementais.
5. Demonstrar, de forma clara no código, os quatro pilares da POO.
6. Organizar o desenvolvimento em etapas documentadas e tarefas menores.

### 2.2 Direção visual

O jogo **não terá estética pixel art de GBA**. A referência ao Pokémon de GBA será principalmente o formato das batalhas e a organização da interface.

A direção visual desejada é de ilustrações 2D modernas, com personagens detalhados e efeitos visuais. Por exemplo, a habilidade do Saci poderá criar um redemoinho animado que parte do personagem, atravessa a tela, atinge o inimigo e apresenta um efeito de impacto.

A técnica de implementação dessas animações — sequência de imagens, desenhos em tempo real ou combinação dos dois — ainda será definida após confirmar as tecnologias permitidas.

---

## 3. Conceito e estrutura do jogo

- **Gênero:** RPG 2D com batalhas por turnos.
- **Referência de combate:** Pokémon de GBA.
- **Ambientação:** folclore brasileiro.
- **Exploração:** não haverá mapa aberto na proposta inicial.
- **Progressão:** conversas, menus, batalhas e avanço pelos ginásios.
- **Personagens:** criaturas com atributos e habilidades próprias.
- **Evolução:** desbloqueio de habilidades e melhoria de atributos após vencer ginásios.

A ideia inicial é que a progressão aconteça por meio de diálogos e telas específicas, sem exigir movimentação livre por um mundo aberto. A forma exata de apresentar a história ainda será definida.

---

## 4. Personagens iniciais e elenco inicial

### 4.1 Personagens iniciais

No início da partida, o jogador escolherá um dos três personagens:

1. Saci;
2. Lobisomem;
3. Negrinho do Pastoreio.

Esses personagens serão opções iniciais jogáveis. Suas diferenças de atributos, ataques e habilidades ainda precisam ser especificadas.

### 4.2 Criaturas dos elementos

A primeira versão do elenco terá três criaturas para cada elemento, além dos três personagens iniciais.

| Elemento | Criaturas planejadas |
|---|---|
| Fogo | Mãe de Ouro, Batatão, Mula sem Cabeça |
| Planta | Curupira, Caipora, Capelobo |
| Água | Iara, Boto-cor-de-rosa, Honorato |

**Total planejado para o elenco inicial: 12 personagens/criaturas**, considerando os três iniciais e as nove criaturas elementais.

Essa lista é preliminar. Ainda será necessário decidir quais criaturas serão inimigos comuns, chefes de ginásio, personagens desbloqueáveis ou recompensas. Também falta definir se os personagens iniciais terão um elemento associado.

---

## 5. Ginásios e progressão

O jogo terá três ginásios:

1. Ginásio do Fogo;
2. Ginásio da Planta;
3. Ginásio da Água.

Cada ginásio será associado ao seu elemento e oferecerá um desafio de batalha. Vencer ginásios será o principal mecanismo de progressão do jogador.

### 5.1 Recompensas planejadas

A ideia atual é utilizar as vitórias nos ginásios para liberar habilidades e melhorar atributos.

| Marco de progressão | Recompensa proposta |
|---|---|
| Início do jogo | Uma habilidade disponível para o personagem escolhido |
| Vitória no primeiro ginásio | Desbloqueio da segunda habilidade |
| Vitória no segundo ginásio | Desbloqueio da terceira habilidade e melhoria de atributos |
| Vitória no terceiro ginásio | Desbloqueio da quarta habilidade e nova melhoria de atributos |

Esta tabela representa uma **proposta inicial**, não uma regra fechada. Ainda é necessário decidir se a ordem dos ginásios será fixa, se todos os personagens seguirão a mesma progressão e quais serão os valores das melhorias.

Também precisa ser confirmado se as habilidades serão desbloqueadas individualmente por personagem, por elemento ou globalmente para a equipe.

---

## 6. Sistema de combate

### 6.1 Estrutura básica

O combate será por turnos, inspirado na organização clássica de Pokémon:

- O personagem do jogador aparece de um lado da tela.
- O inimigo aparece do lado oposto.
- A interface apresenta as informações de combate.
- O jogador seleciona uma ação.
- A ação é executada, a animação correspondente é exibida e o resultado é aplicado.
- O turno do inimigo é executado conforme as regras que ainda serão definidas.
- A batalha termina quando uma das condições de vitória, derrota ou fuga for atingida.

### 6.2 Ações disponíveis

As ações inicialmente planejadas são:

1. **Atacar:** executar um ataque básico.
2. **Habilidade:** selecionar uma habilidade desbloqueada e utilizá-la.
3. **Ver status:** consultar os atributos do inimigo.
4. **Fugir:** tentar encerrar a batalha, se a fuga estiver disponível naquele encontro.

A interface deverá mostrar apenas as habilidades que o personagem já desbloqueou. As regras de custo, recarga, precisão, efeitos de estado e chance de fuga ainda precisam ser definidas.

### 6.3 Atributos

Cada personagem deverá possuir atributos de combate. A lista inicial sugerida para discussão é:

- Pontos de vida (PV);
- Ataque;
- Defesa;
- Velocidade.

Essa lista ainda não está aprovada. O projeto deverá definir quais atributos realmente existirão e como influenciarão o combate.

### 6.4 Habilidades e animações

As habilidades deverão ter identidade visual própria. Exemplo:

- **Saci — Redemoinho:** um efeito de vento parte do Saci, percorre a tela, atinge o adversário e exibe uma animação de impacto.

Outras criaturas poderão ter habilidades associadas ao seu tema ou elemento, como fogo, água, vegetação ou características lendárias. Os nomes, efeitos, valores de dano e animações ainda serão projetados.

As animações devem estar sincronizadas com a lógica da batalha: o dano não deve ser aplicado de maneira inconsistente, e o próximo turno só deve começar no momento definido pelas regras de combate.

---

## 7. Aplicação dos quatro pilares da POO

A arquitetura deverá tornar os quatro pilares visíveis e fáceis de explicar durante a apresentação acadêmica.

### 7.1 Abstração

Criar uma classe abstrata, por exemplo `Personagem`, que represente as características e os comportamentos comuns aos personagens do jogo.

A classe poderá definir operações comuns, como atacar, receber dano e utilizar habilidades. A implementação exata dependerá da arquitetura final.

### 7.2 Encapsulamento

Atributos como vida, ataque, defesa e habilidades deverão ser protegidos, preferencialmente por atributos privados e métodos públicos controlados.

Exemplos conceituais: `receberDano()`, `curar()` e métodos para consultar os atributos. As regras de validação deverão impedir valores inválidos, como vida abaixo de zero quando isso não fizer parte da mecânica.

### 7.3 Herança

Criar especializações de uma classe base para compartilhar comportamentos e representar diferenças entre personagens e criaturas.

A hierarquia exata ainda será definida. O objetivo é evitar duplicação desnecessária sem forçar relações de herança que não façam sentido para o jogo.

### 7.4 Polimorfismo

Personagens diferentes deverão responder de formas diferentes a operações comuns. Por exemplo, um método de habilidade poderá produzir um redemoinho para o Saci e outro efeito para a Iara.

A demonstração deverá mostrar o mesmo contrato ou método sendo utilizado por tipos diferentes, com resultados específicos de cada personagem.

### 7.5 Evidências para a apresentação

Além de implementar os pilares, será importante documentar:

- Quais classes demonstram cada pilar;
- Quais métodos e atributos são relevantes;
- Como o jogo utiliza esses elementos durante uma batalha;
- Um exemplo prático de cada pilar no código.

A estrutura final deverá atender aos requisitos do professor, que ainda precisam ser confirmados.

---

## 8. Direção técnica e restrições

O README disponibilizado como referência descreve um projeto Java com classes, Swing, `Graphics2D`, sprites, áudio e detecção de colisão. Entretanto, ainda não foi confirmado se o projeto atual deve obrigatoriamente utilizar Java ou JavaScript, nem se o código-base deve ser aproveitado.

Antes de escolher a arquitetura técnica, será necessário confirmar:

- A linguagem obrigatória;
- Se APIs gráficas padrão da linguagem são permitidas;
- Se bibliotecas externas são proibidas;
- Se ferramentas como HTML e CSS são permitidas, caso o projeto seja feito em JavaScript;
- Como as imagens e os sons poderão ser carregados;
- Em qual ambiente o professor espera que o jogo seja executado.

**Nenhuma biblioteca, framework ou tecnologia externa está aprovada neste documento.** A escolha técnica será feita após as respostas do professor.

---

## 9. Organização do desenvolvimento

O desenvolvimento será dividido em etapas, com documentação para orientar a implementação e facilitar a colaboração.

### 9.1 Documentação planejada

1. **Documento de escopo:** define o que o jogo terá e o que ficará fora da primeira versão.
2. **Plano de etapas:** organiza a ordem de desenvolvimento e as dependências.
3. **Documentação de cada etapa:** descreve objetivos, regras, classes, critérios de conclusão e tarefas.
4. **Cards de tarefas:** detalham unidades pequenas de trabalho, com responsáveis e critérios de aceite.
5. **Histórico de commits:** registra as alterações de forma clara e rastreável.

### 9.2 Trabalho colaborativo

A proposta é dividir o desenvolvimento entre duas pessoas. Cada responsável desenvolverá sua parte e registrará as alterações com commits.

As tarefas e os limites de cada parte deverão ser definidos antes de começar, para reduzir conflitos e evitar que ambos alterem as mesmas classes sem coordenação.

A documentação detalhada das etapas e dos cards será criada somente depois de confirmar o escopo, as regras de combate e as restrições técnicas.

---

## 10. Fora do escopo inicial

Para manter a primeira versão viável, ficam **provisoriamente fora do escopo**, até nova decisão:

- Mapa aberto e exploração livre;
- Classes Guerreiro e Mago como sistema separado;
- Multiplayer;
- Sistema de captura de criaturas;
- Inventário complexo;
- Árvore extensa de evolução;
- Conteúdo adicional além do elenco inicial de 12 personagens/criaturas;
- Sistemas de missões secundárias.

Esses itens poderão ser reconsiderados se forem necessários ou se houver tempo suficiente, mas não devem ser tratados como requisitos aprovados.

---

## 11. Perguntas necessárias para a próxima versão da documentação

As perguntas abaixo deverão ser respondidas para transformar esta proposta em um documento de escopo mais completo. Respostas parciais são suficientes para começar.

### A. Linguagem e tecnologias

1. O professor exige **Java ou JavaScript**? A orientação mais recente é definitiva?
2. É permitido utilizar APIs padrão da linguagem, como Swing e Java 2D no Java, ou Canvas e recursos nativos do navegador no JavaScript?
3. É proibido utilizar qualquer biblioteca externa, ou algumas bibliotecas gráficas são permitidas?
4. O projeto deve partir obrigatoriamente do README/código-base fornecido?
5. O jogo precisa executar em uma janela desktop, em um navegador ou em outro ambiente?
6. É permitido utilizar ferramentas de IA para ajudar a escrever código e produzir recursos visuais?

### B. Escopo e apresentação

7. O trabalho é individual ou em dupla? Há outros integrantes?
8. Existe prazo de entrega?
9. O professor exige um jogo completo ou um protótipo funcional?
10. Além do código, é necessário entregar relatório, diagrama UML, documentação ou apresentação?
11. Há exigência de demonstrar os quatro pilares em classes específicas ou basta demonstrá-los de forma clara no projeto?

### C. Personagens e elementos

12. Os três iniciais (Saci, Lobisomem e Negrinho do Pastoreio) terão elementos próprios? Quais?
13. Os nove personagens elementais serão todos inimigos, ou alguns poderão ser desbloqueados e utilizados pelo jogador?
14. O jogador controlará apenas o personagem inicial durante toda a campanha ou poderá trocar de personagem?
15. Os três ginásios terão uma ordem fixa ou poderão ser enfrentados em qualquer ordem?
16. Qual criatura será o chefe de cada ginásio?
17. Derrotar um ginásio deve liberar uma criatura jogável, uma habilidade, uma melhoria de atributos ou mais de uma dessas recompensas?

### D. Regras de batalha

18. Cada personagem terá quatro habilidades no total, incluindo o ataque básico, ou quatro habilidades especiais além dele?
19. As habilidades terão custo de energia, limite de usos ou poderão ser utilizadas livremente?
20. O combate terá somente dano direto ou também efeitos como queimadura, envenenamento, lentidão, cura e defesa?
21. Como serão calculados o dano e a defesa? Queremos fórmulas simples ou um sistema mais elaborado?
22. A fuga estará disponível em todas as batalhas ou apenas em encontros comuns?
23. O inimigo agirá automaticamente depois de cada ação do jogador?
24. Ao perder uma batalha, o jogador poderá tentar novamente ou precisará recomeçar de algum ponto?

### E. Progressão

25. Os quatro poderes serão liberados gradualmente ao vencer os três ginásios? Qual será a regra exata?
26. Os atributos aumentarão automaticamente após cada ginásio ou o jogador poderá escolher as melhorias?
27. Haverá níveis numéricos de personagem e experiência, ou a progressão dependerá apenas dos ginásios?
28. As melhorias serão permanentes durante toda a campanha?
29. O jogador poderá salvar e carregar o progresso?

### F. Arte, animações e áudio

30. Qual estilo de arte moderna você prefere: ilustração 2D estilizada, anime, fantasia pintada ou outro?
31. Os personagens serão representados de corpo inteiro, em poses de batalha, ou por retratos?
32. As animações serão feitas com sequências de imagens prontas, efeitos desenhados pelo próprio programa ou uma combinação dos dois?
33. Você pretende criar as imagens com IA ou utilizar recursos de outras fontes?
34. O jogo terá efeitos sonoros e música?
35. Existe alguma restrição de tamanho dos arquivos, resolução ou formato dos recursos visuais?

### G. Interface e história

36. O jogo terá uma tela inicial com opções como Novo Jogo, Continuar e Sair?
37. Como serão apresentados os diálogos: caixas de texto com retratos, cenas ilustradas ou outra forma?
38. Haverá uma história principal com objetivo final ou o foco será apenas vencer os três ginásios?
39. O idioma do jogo será somente português?
40. Quais telas são indispensáveis para a primeira versão: seleção de personagem, batalha, diálogo, ginásios, vitória, derrota e créditos?

### H. Organização do trabalho

41. Onde o código será versionado: GitHub ou outra plataforma?
42. Os dois desenvolvedores trabalharão em branches separadas?
43. Você quer que cada card contenha objetivo, arquivos envolvidos, instruções para a IA, critérios de aceite e mensagem de commit sugerida?
44. A documentação será escrita em português e armazenada junto ao código em arquivos Markdown?
45. Você prefere organizar o projeto por etapas grandes, sprints ou uma lista sequencial de tarefas?

---

## 12. Próximo passo

A próxima versão deverá transformar este documento preliminar em uma **especificação de escopo**, com requisitos aprovados, regras de combate fechadas, elenco definido, progressão detalhada e restrições técnicas confirmadas.

Depois disso, será possível elaborar o plano de etapas, a arquitetura de classes, o diagrama UML e os cards de desenvolvimento sem antecipar decisões que ainda dependem do professor.

**Prioridade para responder primeiro:** perguntas 1 a 5, 7 a 11, 12 a 18, 25 a 27 e 30 a 32. Essas respostas já permitirão preparar uma segunda versão significativamente mais precisa.
