# Decisões de escopo e regras de jogo

## Tecnologia e idioma

- O projeto será desenvolvido em Java 17, com Swing/Java2D e Maven, sem bibliotecas externas adicionais.
- O jogo terá somente Português do Brasil (PT-BR). Os textos serão escritos diretamente no código; não haverá suporte a outros idiomas, i18n ou arquivos de tradução.

## Batalha

- O dano é calculado por `max(1, ataque + poder - defesa)`.
- A ordem dos turnos é fixa: primeiro o jogador, depois o inimigo. Velocidade é um atributo, mas não altera a ordem.
- Cada ginásio tem duas criaturas comuns, seguidas pelo chefe.
- A fuga é permitida somente em encontros comuns e sempre funciona.
- Ao vencer um inimigo, o jogador libera o poder dele.
- Cada personagem tem até quatro slots de habilidade. Começa com dois; o primeiro contém o ataque básico. Cada ginásio vencido libera um slot, até o máximo de quatro.
- A troca dos poderes equipados só é permitida após liberar quatro poderes.

## Progressão

- A vitória sobre um inimigo comum concede 20 XP; a vitória sobre um chefe concede 50 XP.
- Cada ponto de melhoria custa 30 XP. Um ponto aumenta PV em 5 ou aumenta Ataque, Defesa ou Velocidade em 1.

## Equipe e derrota

- A equipe começa somente com o personagem inicial escolhido.
- Vencer os ginásios do Fogo e da Planta recruta os outros dois personagens iniciais.
- A quarta vaga fica reservada e vazia no protótipo.
- A troca do personagem titular só pode ocorrer fora de batalha.
- Na derrota, o jogador retorna ao spawn médico com tudo restaurado, e o ginásio atual é reiniciado.

## Salvamento

- O save será armazenado em um arquivo `.properties`.
