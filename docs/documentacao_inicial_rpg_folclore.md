# Documentação inicial — RPG do Folclore Brasileiro

## 1. Status e objetivo

O projeto está **em planejamento**. Seu objetivo acadêmico é desenvolver um RPG 2D com batalhas por turnos inspirado na estrutura de combate dos jogos clássicos de Pokémon para Game Boy Advance, com personagens do folclore brasileiro e direção visual moderna. A proposta é praticar e demonstrar abstração, encapsulamento, herança e polimorfismo.

As ideias abaixo registram o escopo inicial, não requisitos definitivos. A linguagem obrigatória, as tecnologias permitidas e detalhes das mecânicas precisam ser confirmados com o professor antes de fechar a arquitetura ou iniciar a implementação.

## 2. Conceito e direção visual

O jogador escolherá um entre três personagens iniciais e avançará por três ginásios elementais. O combate será por turnos e poderá envolver habilidades próprias, efeitos visuais e progressão de atributos.

A referência a Pokémon de GBA descreve principalmente a estrutura do combate. A direção visual desejada é moderna, com ilustrações 2D detalhadas e animações de habilidades, sem estética pixel art.

## 3. Personagens iniciais

- Saci
- Lobisomem
- Negrinho do Pastoreio

## 4. Criaturas planejadas

| Elemento | Criaturas planejadas |
|---|---|
| Fogo | Mãe de Ouro, Batatão, Mula sem Cabeça |
| Planta | Curupira, Caipora, Capelobo |
| Água | Iara, Boto-cor-de-rosa, Honorato |

O elenco e os papéis de cada criatura ainda poderão ser ajustados durante a definição do escopo.

## 5. Batalhas

A proposta inicial é de batalhas por turnos. As ações consideradas são:

- **Atacar:** realizar um ataque básico.
- **Habilidade:** utilizar uma habilidade desbloqueada.
- **Ver status:** consultar os atributos do inimigo.
- **Fugir:** tentar encerrar a batalha quando permitido.

As habilidades deverão ter efeitos visuais próprios. Como exemplo de intenção visual, o Saci poderá lançar um redemoinho animado que atravessa a tela e atinge o inimigo. A disponibilidade das ações, as regras de turno, os atributos e os efeitos ainda precisam ser definidos.

## 6. Progressão e ginásios

Estão planejados três ginásios, na seguinte ordem:

1. Ginásio do Fogo;
2. Ginásio da Planta;
3. Ginásio da Água.

A proposta é desbloquear gradualmente até quatro habilidades e melhorar os atributos do personagem ao vencer ginásios. Regras de progressão, critérios de vitória e recompensas ainda serão definidos.

## 7. Aplicação dos quatro pilares da POO

- **Abstração:** representar conceitos comuns dos personagens por meio de classes e operações essenciais.
- **Encapsulamento:** proteger atributos, como vida e ataque, e controlar suas alterações por métodos.
- **Herança:** reutilizar comportamentos comuns em classes especializadas de personagens e criaturas.
- **Polimorfismo:** permitir que personagens diferentes implementem comportamentos próprios para operações comuns, como utilizar habilidades.

A arquitetura final deverá tornar os quatro conceitos claros no código e na apresentação do trabalho. A modelagem de classes e o diagrama UML ainda não foram definidos.

## 8. Linguagem e tecnologias

**A linguagem e as tecnologias ainda dependem de confirmação do professor.** O README do projeto-base fornecido para estudo descreve uma implementação em Java com Swing e Java 2D; isso não confirma que Java, essas APIs ou o reaproveitamento do código-base sejam obrigatórios ou permitidos para este projeto.

Nenhuma biblioteca ou framework externo está definido como permitido. Não se deve assumir uma stack nem adicionar dependências antes da confirmação.

## 9. Organização prevista do desenvolvimento

O trabalho será organizado em etapas documentadas, com a proposta de manter:

- documentação de escopo e requisitos;
- plano das etapas de desenvolvimento;
- documentação detalhada de cada etapa;
- cards de tarefas com critérios de conclusão;
- commits claros e rastreáveis;
- divisão de responsabilidades entre os integrantes.

## 10. Pendências

- [ ] Confirmar a linguagem obrigatória e as tecnologias permitidas.
- [ ] Confirmar quais APIs padrão podem ser utilizadas e se o código-base fornecido deve ser aproveitado.
- [ ] Fechar as regras de combate, incluindo turnos, atributos, habilidades e condição para fugir.
- [ ] Definir a progressão, as recompensas dos ginásios e o desbloqueio de até quatro habilidades.
- [ ] Revisar o elenco e os papéis das criaturas.
- [ ] Definir a arquitetura de classes e o diagrama UML, evidenciando os quatro pilares da POO.
- [ ] Planejar as etapas, os cards e a divisão de responsabilidades.
- [ ] Implementar e testar o jogo após a confirmação do escopo e da tecnologia.

## 11. Estado inicial

- [x] Definir o conceito inicial do jogo.
- [x] Definir os três personagens iniciais.
- [x] Listar três criaturas para cada elemento.
- [ ] Confirmar linguagem e tecnologias permitidas.
- [ ] Fechar as regras de combate.
- [ ] Definir a progressão e as recompensas dos ginásios.
- [ ] Definir a arquitetura de classes e o diagrama UML.
- [ ] Planejar as etapas e os cards de desenvolvimento.
- [ ] Implementar e testar o jogo.

As mecânicas e tecnologias registradas como propostas não devem ser consideradas requisitos definitivos até serem confirmadas pelo professor.
