# Fluxo de trabalho com Git

## Branches

- `main` é a branch estável e recebe as tags das versões.
- `dev` é a branch de integração das tarefas concluídas.
- Cada tarefa deve ser desenvolvida em uma branch no formato `feature/<dev>-<id-tarefa>`, substituindo `<dev>` pelo nome do desenvolvedor e `<id-tarefa>` pelo identificador da tarefa. Exemplo: `feature/ryan-1.3.1`.

## Commits e integração

- Cada tarefa corresponde a um único commit, com mensagem no formato Conventional Commits.
- A integração de uma tarefa em `dev` é feita por Pull Request.
- O Pull Request deve ser revisado pelo outro integrante antes da integração.
