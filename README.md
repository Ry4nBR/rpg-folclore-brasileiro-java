# RPG do Folclore Brasileiro

Projeto acadêmico de desenvolvimento de um RPG 2D com batalhas por turnos, inspirado no estilo de combate dos jogos clássicos de Pokémon para Game Boy Advance, mas com arte moderna e personagens do folclore brasileiro.

O projeto tem como objetivo praticar e demonstrar os quatro pilares da Programação Orientada a Objetos (POO): **abstração, encapsulamento, herança e polimorfismo**.

> **Status:** em planejamento. A linguagem obrigatória, as tecnologias permitidas e alguns detalhes das mecânicas ainda precisam ser confirmados com o professor.

## Conceito do jogo

O jogador escolherá um dos três personagens iniciais e avançará por três ginásios elementais. As batalhas acontecerão por turnos, com habilidades próprias, efeitos visuais e progressão de atributos.

A referência a Pokémon de GBA diz respeito principalmente à estrutura do combate. A direção visual desejada é moderna, com ilustrações 2D detalhadas e animações de habilidades, sem estética pixel art.

## Personagens iniciais

- Saci
- Lobisomem
- Negrinho do Pastoreio

## Criaturas planejadas

| Elemento | Criaturas |
|---|---|
| Fogo | Mãe de Ouro, Batatão, Mula sem Cabeça |
| Planta | Curupira, Caipora, Capelobo |
| Água | Iara, Boto-cor-de-rosa, Honorato |

O elenco e os papéis de cada criatura ainda poderão ser ajustados durante a definição do escopo.

## Sistema de batalha

A proposta inicial inclui as seguintes ações:

- **Atacar:** realizar um ataque básico.
- **Habilidade:** utilizar uma habilidade desbloqueada.
- **Ver status:** consultar os atributos do inimigo.
- **Fugir:** tentar encerrar a batalha quando permitido.

As habilidades terão efeitos visuais próprios. Por exemplo, o Saci poderá lançar um redemoinho animado que atravessa a tela e atinge o inimigo.

## Progressão

O jogo terá três ginásios:

1. Ginásio do Fogo;
2. Ginásio da Planta;
3. Ginásio da Água.

A proposta é desbloquear até quatro habilidades gradualmente e melhorar os atributos do personagem ao vencer ginásios. As regras exatas de progressão e as recompensas ainda serão definidas.

## Aplicação dos quatro pilares da POO

- **Abstração:** representar os conceitos comuns dos personagens por meio de classes e operações essenciais.
- **Encapsulamento:** proteger atributos, como vida e ataque, e controlar sua alteração por métodos.
- **Herança:** reutilizar comportamentos comuns em classes especializadas de personagens e criaturas.
- **Polimorfismo:** permitir que personagens diferentes implementem comportamentos próprios para operações comuns, como utilizar habilidades.

A arquitetura final deverá tornar esses conceitos claros no código e na apresentação do trabalho.

## Tecnologias

As tecnologias ainda precisam ser confirmadas com o professor.

O README do projeto-base fornecido para estudo descreve uma implementação em Java, com Swing e Java 2D. Ainda será necessário confirmar a linguagem exigida para este projeto, quais APIs padrão podem ser utilizadas e se o código-base deverá ser aproveitado.

**Nenhuma biblioteca ou framework externo está definido como permitido neste momento.**

## Organização do desenvolvimento

O trabalho será organizado em etapas documentadas. A proposta é manter:

- Documentação de escopo e requisitos;
- Plano de etapas de desenvolvimento;
- Documentação detalhada de cada etapa;
- Cards de tarefas com critérios de conclusão;
- Commits claros e rastreáveis;
- Divisão de responsabilidades entre os integrantes.

## Documentação

A documentação inicial do projeto será mantida em Markdown. Ela registra a ideia atual, as mecânicas propostas, os pilares da POO e as perguntas que precisam ser respondidas antes de fechar o escopo.

## Status do projeto

- [x] Definir o conceito inicial do jogo.
- [x] Definir os três personagens iniciais.
- [x] Listar três criaturas para cada elemento.
- [ ] Confirmar linguagem e tecnologias permitidas.
- [ ] Fechar as regras de combate.
- [ ] Definir a progressão e as recompensas dos ginásios.
- [ ] Definir a arquitetura de classes e o diagrama UML.
- [ ] Planejar as etapas e os cards de desenvolvimento.
- [ ] Implementar e testar o jogo.

## Observação

Este repositório está em fase de planejamento. As mecânicas e tecnologias descritas como propostas ainda não devem ser consideradas requisitos definitivos até serem confirmadas.
