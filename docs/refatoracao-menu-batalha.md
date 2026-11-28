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
