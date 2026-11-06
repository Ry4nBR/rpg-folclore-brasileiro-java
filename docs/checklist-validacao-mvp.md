# Checklist de validação do MVP

Executado em 06/11/2026, na branch `mvp/prototipo`, com JDK 17. A validação funcional foi feita com a aplicação Swing, navegação por eventos de mouse/teclado e inspeção dos estados renderizados; os itens marcados abaixo têm a observação da evidência.

| # | Verificação | Resultado | Observação |
|---:|---|---|---|
| 1 | A aplicação inicia sem erro. | OK | JAR temporário aberto com JDK 17; a janela iniciou e fechou normalmente. |
| 2 | A tela inicial aparece com **Jogar** e **Sair**. | OK | Tela inicial aberta; controles e callbacks verificados. |
| 3 | É possível escolher Saci ou Lobisomem e voltar ao menu sem perder estabilidade. | OK | Os dois personagens foram selecionados; retorno ao menu por ESC e pela tela de resultado foi testado. |
| 4 | Confirmar o personagem abre a batalha contra a Mula sem Cabeça. | OK | Confirmação abriu `TelaBatalha` com o personagem escolhido e o chefe esperado. |
| 5 | Os HUDs mostram nome, tipo e PV de ambos os combatentes. | OK | Renderização da batalha confere nome/tipo e barra com PV atual e máximo para ambos. |
| 6 | **Atacar** reduz o PV do chefe conforme `max(1, ataque + poder − defesa)`. | OK | Saci causou 11 PV; cálculo independente `max(1, 10 + 6 − 5) = 11`. |
| 7 | **Habilidade** funciona (cura do Saci, dano do Lobisomem) e exibe usos restantes. | OK | Cura do Saci recuperou 12 PV; Uivo Brutal do Lobisomem causou 19 PV; contadores e rótulos foram conferidos. |
| 8 | Habilidade sem usos fica desabilitada e não consome turno. | OK | Com 0/2 usos, o botão ficou desabilitado; clique não alterou turno, PV ou contador. |
| 9 | O chefe age sozinho: básico nos turnos ímpares, Galope Infernal nos pares enquanto houver usos. | OK | Turnos 1 e 3 usaram Coice Flamejante; turno 2 usou Galope Infernal. |
| 10 | Todas as mensagens aparecem em ordem e são lidas antes da próxima ação. | OK | Fila inicial/ação/chefe conferida; ação não avança enquanto a mensagem anterior não é lida. |
| 11 | Vitória (PV do chefe = 0) leva à tela «Vitória!» com «Ginásio do Fogo concluído!». | OK | Saci venceu; mensagens finais foram lidas antes da tela de resultado, com a mensagem de conclusão. |
| 12 | Derrota (PV do jogador = 0) leva à tela «Derrota» com «Tentar novamente». | OK | Derrota forçada para cobrir o fluxo; mensagem e botão de nova tentativa conferidos. |
| 13 | «Tentar novamente» reinicia com PV cheio, usos cheios e chefe novo. | OK | Nova batalha criada com instâncias distintas, PV máximos, usos restaurados e sessão sem ginásio concluído. |
| 14 | «Voltar ao menu» reinicia o estado; um novo jogo começa limpo. | OK | Retorno à tela inicial seguido de nova batalha com Lobisomem, PV/usos cheios e chefe novo. |
| 15 | **Sair** encerra a aplicação sem erro. | OK | O callback acionou `System.exit(0)`; o motor Java encerrou sem exceção e com código 0. |
| 16 | `mvn -q compile` termina sem erros ou avisos relevantes. | OK | `mvn -q clean compile` e `mvn -q compile` passaram com JDK 17. |
| 17 | `DemoPilares`, `DemoBatalhaVitoria` e `DemoBatalhaDerrota` executam com a saída esperada. | OK | As três demos foram executadas; vitórias/derrotas e conteúdo esperado foram conferidos. |
| 18 | `java -jar target/rpg-folclore-mvp.jar` executa o fluxo completo. | OK | `mvn -q package` criou o JAR com `Main-Class: br.folclore.Main`; `java -jar` abriu o menu com apenas o JDK 17 no PATH, e vitória, derrota e retry passaram usando as classes do JAR. |
| 19 | Nenhum arquivo da pasta `ui` contém fórmula de dano. | OK | Busca por fórmulas e expressões de dano em `src/main/java/br/folclore/ui` não encontrou ocorrências. |
| 20 | Os quatro pilares de POO estão documentados com referência real a arquivos. | OK | Quatro pilares conferidos; 15 referências a arquivos resolvidas em `pilares-poo-mvp.md`. |
| 21 | Todas as tarefas têm commit e tag na branch `mvp/prototipo`. | OK | As 58 tarefas têm commits e tags anotadas locais; não foram publicadas remotamente, conforme autorização. |
| 22 | `dev` e `main` não receberam commits ou tags do MVP. | OK | A branch `dev` não existe; HEADs de `develop` e `main` permaneceram inalterados e sem tags do MVP. |
| 23 | A tag final `mvp-v1.0.0` existe e aponta para o último commit do MVP. | OK | Tag anotada local apontando para o commit final; não publicada remotamente. |

## Revisão cruzada

A revisão por outro desenvolvedor não está disponível nesta execução local individual. Como substituição, foram repetidos três itens em probes independentes: #6 (fórmula de dano), #13 (tentar novamente) e #15 (sair); todos passaram. A tag final e o registro local foram conferidos sem PR ou publicação remota.
