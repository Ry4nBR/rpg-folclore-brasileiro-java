# Progresso de execução — MVP RPG Folclore

## Estado atual

- **Fase:** 0 — Preparação do ambiente e estrutura inicial
- **Bloco:** 0.1 — Branch, build e esqueleto
- **Última tarefa concluída:** 0.1.1 — Criar a branch do MVP e registrar o escopo
- **Tarefa em execução:** Nenhuma
- **Próxima tarefa pendente:** 0.1.2 — Configurar Maven (Java 17) e `.gitignore`
- **Status do bloco:** Em andamento
- **Bloqueios:** Nenhum após autorização para executar diretamente na branch `mvp/prototipo`, sem PRs.

## Decisões e estado Git verificado

- Branch de trabalho: `mvp/prototipo`; a branch já existia localmente e no remoto.
- A execução permanece na branch existente; não foram reescritos commits nem alteradas branches `develop` ou `main`.
- Tags `v0.*` já existentes no repositório exigem o prefixo `mvp-`, conforme a seção 9.4 do planejamento. Prefixo decidido: `mvp-`.
- A comparação inicial de `develop...mvp/prototipo` mostrou alterações amplas (incluindo remoções) além do documento de escopo previsto no aceite da tarefa 0.1.1. Divergência preservada conforme orientação do usuário; não será corrigida neste ciclo.
- Autorização explícita do usuário: executar tarefas na `mvp/prototipo`, sem PRs ou branches de tarefa, apenas commits e tags. Não fazer push.

## Registro das tarefas

| Tarefa | Status | Commit (mensagem planejada) | Tag planejada | Validação |
|---|---|---|---|---|
| 0.1.1 — Criar a branch do MVP e registrar o escopo | Concluída | `docs: registro do escopo e da branch do mvp` | `mvp-v0.0.1` | Conteúdo obrigatório conferido; `git diff --check` passou; branch e ausência prévia da tag conferidas |
| 0.1.2 — Configurar Maven (Java 17) e `.gitignore` | Pendente | `chore: configuracao do maven java 17 e gitignore do mvp` | `mvp-v0.0.2` | Pendente |
| 0.1.3 — Criar o ponto de entrada e a estrutura de pacotes | Pendente | `chore: ponto de entrada e estrutura de pacotes do mvp` | `mvp-v0.0.3` | Pendente |

## Histórico do bloco

- 0.1.1: `docs/mvp-escopo.md` registra objetivo, comparativo, exclusões, decisões D1-D12 e prefixo `mvp-`; o prefixo foi necessário porque já existem tags `v0.*`.
- A branch `mvp/prototipo` já existia; foi preservada sem reescrita. A comparação inicial com `develop` revelou divergências amplas, registradas acima.
- A validação textual inicial no PowerShell usou a codificação padrão e não encontrou cabeçalhos acentuados; repetida explicitamente em UTF-8, passou. `git diff --check` passou.
- Commit e tag de 0.1.1 serão registrados imediatamente após esta validação. Nenhuma tarefa do Bloco 0.2 ou de fases posteriores será iniciada sem nova autorização explícita.
