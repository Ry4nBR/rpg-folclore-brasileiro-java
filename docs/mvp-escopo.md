# Escopo do MVP — RPG do Folclore Brasileiro

## Objetivo

Este MVP é um protótipo executável para validar a arquitetura em Programação Orientada a Objetos, o combate por turnos e o fluxo de interface Swing do menu até o resultado do confronto.

O fluxo do jogo é: **Menu → Seleção de personagem → Batalha contra o chefe → Resultado → Menu**. O jogo é exclusivamente em Português do Brasil.

## Diferenças entre o projeto completo e o MVP

| Aspecto | Projeto completo | MVP |
|---|---|---|
| Ginásios | 3 (Fogo, Planta, Água) | 1 (Fogo) |
| Personagens jogáveis | 3 iniciais + recrutamento | 2 (Saci e Lobisomem) |
| Inimigos | 9 criaturas (6 comuns + 3 chefes) | 1 chefe (Mula sem Cabeça) |
| Atributos | PV, Ataque, Defesa, Velocidade | PV, Ataque, Defesa |
| Habilidades | Slots, poderes liberados e troca | 1 habilidade fixa por combatente |
| Progressão | XP, melhorias e slots por ginásio | Apenas concluir o ginásio |
| Persistência | Salvar e continuar | Nenhuma |
| Ações de batalha | Atacar, Habilidade, Ver status, Fugir | Atacar e Habilidade |
| IA | Pacote de IA com regras | Regra simples no próprio chefe |
| Visual e áudio | Sprites, retratos, animações, BGM e SFX | Formas desenhadas em Java2D; sem áudio |

## Funcionalidades excluídas

Ficam fora do MVP: múltiplos ginásios e chefes; equipe, recrutamento e troca de personagem; XP, melhorias e níveis; desbloqueio e equipamento de habilidades; save e load; fuga e ação de ver status; atributo Velocidade e vantagens de tipo; múltiplos encontros, exploração e spawn médico; diálogos, roteiro e créditos; sprites, retratos, animações e áudio; qualquer idioma além de Português do Brasil.

## Decisões técnicas do MVP

### D1 — Seleção de personagem

O jogador escolhe Saci ou Lobisomem e usa esse personagem até o fim. Não há equipe, troca ou recrutamento. Duas subclasses demonstram herança e polimorfismo.

### D2 — Atributos iniciais

Somente PV, Ataque e Defesa. Velocidade foi removida porque a ordem dos turnos é fixa e o atributo não teria efeito.

| Combatente | Tipo | PV | Ataque | Defesa |
|---|---|---:|---:|---:|
| Saci | Vento | 60 | 10 | 6 |
| Lobisomem | Físico | 70 | 12 | 7 |
| Mula sem Cabeça (chefe) | Fogo | 55 | 9 | 5 |

`Tipo` é apenas um rótulo exibido; não há vantagem ou desvantagem de tipo.

### D3 — Habilidades

Cada combatente possui ataque básico ilimitado e uma habilidade de usos limitados:

| Combatente | Ataque básico (poder) | Habilidade | Efeito | Usos |
|---|---|---|---|---:|
| Saci | Rasteira de Vento (6) | Ventos Revigorantes | Cura 20% do PV máximo | 3 |
| Lobisomem | Garra Selvagem (7) | Uivo Brutal | Dano com poder 12 | 2 |
| Mula sem Cabeça | Coice Flamejante (5) | Galope Infernal | Dano com poder 10 | 2 |

A cura do Saci é 20% (em vez de 5% no projeto completo) para que seja visível e testável na barra de vida: 12 PV em vez de 3.

### D4 — Cálculo de dano

`dano = max(1, ataque do atacante + poder − defesa do alvo)`. A cura é `max(1, PV máximo × percentual / 100)`, usando divisão inteira e respeitando o PV máximo.

### D5 — Ordem dos turnos

A ordem é fixa: o jogador age e depois o chefe. Se o chefe chegar a 0 PV, não age naquele turno.

### D6 — Habilidade sem usos

A ação é recusada com uma mensagem e não consome o turno.

### D7 — Comportamento do chefe

Em turno par, se ainda houver usos de Galope Infernal, a Mula sem Cabeça usa a habilidade; caso contrário, usa o ataque básico. A decisão fica na própria classe do chefe, sem pacote de IA.

### D8 — Condição de vitória

O jogador vence quando o PV do chefe chega a 0.

### D9 — Condição de derrota

O jogador perde quando seu PV chega a 0, verificado após o turno do chefe.

### D10 — Encerramento do ginásio

Ao vencer, a sessão marca `ginasioConcluido = true`, exibe «Ginásio do Fogo concluído!» e permite voltar ao menu. Na derrota, é possível tentar novamente em uma batalha nova, com PV cheio e chefe novo, ou voltar ao menu.

### D11 — Visual

Os combatentes são retângulos coloridos desenhados em Java2D com seus nomes. Não são necessários arquivos de imagem nem áudio.

### D12 — Balanceamento esperado

Conforme conferido manualmente no planejamento, Saci vence a Mula sem Cabeça em 5 turnos e termina com 18 PV; Lobisomem vence em 4 turnos. A derrota ocorre se o jogador desperdiçar turnos.

## Branch e política de tags

O trabalho do MVP é mantido na branch `mvp/prototipo`, sem integrar alterações em `dev` ou `main`. O repositório já contém tags `v0.*`; conforme a seção 9.4 do planejamento, as tags deste MVP usam o prefixo `mvp-` (por exemplo, `mvp-v0.0.1`) para evitar colisões. As tags são anotadas e apontam para o commit da tarefa correspondente.

Para este ciclo, por autorização explícita do usuário, as tarefas são executadas diretamente em `mvp/prototipo`, sem branches curtas, PRs ou operações remotas; cada tarefa mantém seu commit e tag individuais.
