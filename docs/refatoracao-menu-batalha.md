# Refatoração do menu de batalha

Complemento ao planejamento para a solicitação de quatro golpes por personagem.
Trabalho na branch de funcionalidade já aberta, `feat/implementa-sprites`.
Commits pequenos em português, seguindo Conventional Commits. Não há novas tags
de versão previstas para esta solicitação.

| Etapa | Escopo | Commit previsto |
|---|---|---|
| 1 | Registrar a reprodução de GIF e a habilidade já implementadas | `feat: reproduz gifs animados nos sprites e na habilidade do saci` |
| 2 | Três poderes por personagem, previsão de dano/cura e migração de saves | `feat: completa os quatro golpes dos personagens` |
| 3 | Ataque, Mochila, Status dos dois combatentes e Fugir | `feat: reorganiza o menu e os paineis da batalha` |
| 3.1 | Preservar os acentos dos nomes e mensagens | `fix: preserva acentos nos nomes dos golpes e nas mensagens` |
| 4 | Associar os quatro GIFs aos golpes do Saci | `feat: associa animacoes aos quatro golpes do saci` |
| 5 | Verificar navegação, golpes, animações e compatibilidade de saves | `test: valida o menu de batalha e os quatro golpes` |

## Comportamento esperado

- Quatro opções de golpe: ataque básico sem limite e três poderes com usos.
- Previsões calculadas sem consumir usos nem alterar pontos de vida.
- Cura mostra PV recuperados em vez de apresentar dano inexistente.
- Saves antigos recebem os poderes iniciais faltantes, mantendo atributos,
  progresso e poderes já obtidos.
- Mochila vazia até existir um sistema de itens; abrir painéis não consome turno.
- Fugir respeita a regra existente que impede fuga de chefes.
- Saci: Ataque, Chute Furacão, Vento Revigorante e Assobio; cada golpe usa seu GIF.
- Animações devem terminar antes de liberar ações e o próximo turno.

## Validação

Compilação Maven e verificações Java executáveis sem dependências de teste.
As verificações de interface renderizam imagens em `target/` para inspeção.

Execute `mvn test` para compilar e rodar todas as verificações. O plugin Maven
de execução já usado pelo projeto chama `VerificacoesDoCombate` na fase de
testes; uma asserção com falha interrompe a compilação. `-DskipTests` permite
pular essas verificações quando necessário.

Os testes cobrem composição e descarte de quadros GIF, tempos, repetição,
previsões de dano/cura, consumo de usos, os 12 personagens, saves antigos,
quatro botões de golpe, mochila, status, voltar, fuga e seleção dos quatro
arquivos de animação do Saci. As animações de ação percorrem seu ciclo real.

## Controles

- Menu principal: mouse ou A (Ataque), M (Mochila), S (Status), F (Fugir).
- Menu de golpes: mouse ou 1, 2, 3 e 4.
- Esc: voltar ao menu ou fechar painel, sem consumir turno.
- Enter: completar/avançar mensagens; nos painéis, fechar.

## Golpes iniciais

Cada personagem mantém seu ataque básico e recebe os três poderes abaixo.
O dano mostrado depende do ataque do usuário, da defesa e dos PV restantes
do alvo. Poderes de cura mostram a recuperação possível no estado atual.

| Personagem | Poderes |
|---|---|
| Saci | Chute Furacão; Vento Revigorante; Assobio |
| Lobisomem | Uivo Brutal; Mordida Lunar; Vigor da Lua |
| Negrinho do Pastoreio | Objetos Perdidos; Luz da Vela; Amparo do Pastoreio |
| Mãe de Ouro | Chama Dourada; Brilho Incandescente; Centelha de Ouro |
| Batatão | Rolo de Fogo; Explosão de Brasas; Sopro de Fumaça |
| Mula sem Cabeça | Galope Infernal; Relincho Ardente; Labareda Maldita |
| Curupira | Pegadas Invertidas; Raízes da Floresta; Seiva Protetora |
| Caipora | Cipó Enrolante; Investida do Caititu; Ervas da Mata |
| Capelobo | Uivo da Mata; Garras do Brejo; Investida Feral |
| Iara | Águas Curativas; Canto das Águas; Onda Encantada |
| Boto-cor-de-rosa | Redemoinho Rosado; Salto das Águas; Beijo do Rio |
| Honorato | Maré Fúria; Abraço da Cobra; Tsunami Amazônico |

## Animações do Saci

Os recursos ficam em `src/main/resources/efeitos/` e são carregados sob demanda.
Cada ação reinicia no primeiro quadro e toca um ciclo; a animação normal fica
oculta até a conclusão. Arquivos retangulares mantêm a proporção e o alinhamento
inferior dentro da área de 192 × 192 pixels do personagem.

| Golpe | Recurso | Duração |
|---|---|---|
| Ataque | `saci_ataque.gif` | 1680 ms |
| Chute Furacão | `saci_habilidade.gif` | 1200 ms |
| Vento Revigorante | `saci_vento_revigorante.gif` | 1260 ms |
| Assobio | `saci_assobio.gif` | 1080 ms |
