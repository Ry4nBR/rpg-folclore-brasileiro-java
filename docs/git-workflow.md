# Fluxo de trabalho com Git

## Branches

- `main`: é a branch reservada para a versão final e estável do projeto, correspondente à versão `1.0.0`. Durante o desenvolvimento, deve permanecer sem alterações. Ao concluir e estabilizar o projeto, a versão final será integrada a essa branch.
- `develop`: é a branch principal de desenvolvimento e integração. Os dois desenvolvedores trabalham diretamente nela, seguindo a documentação de planejamento de nano-tarefas.
- `fix/<dev>-<id-tarefa>`: é uma branch auxiliar criada quando um erro ou problema precisar ser corrigido separadamente. Após a correção, as alterações devem ser integradas novamente à `develop`.

## Planejamento e execução das nano-tarefas

O desenvolvimento é organizado por meio de uma documentação de planejamento composta por nano-tarefas. Cada card descreve uma unidade específica de trabalho e deve conter as seguintes informações:

- **Tarefa:** identificador único da nano-tarefa.
- **Responsável:** desenvolvedor responsável pela execução.
- **Arquivo(s) Alvo:** arquivos que serão criados ou modificados.
- **Ação:** descrição objetiva da implementação ou alteração esperada.
- **Commit:** mensagem de commit previamente definida, seguindo o padrão Conventional Commits.
- **Tag:** identificador de versão associado à nano-tarefa, conforme estabelecido no planejamento.

Os desenvolvedores devem seguir as especificações de cada card, respeitando o escopo da tarefa, os arquivos-alvo, a mensagem de commit e a tag definidos no planejamento.

### Exemplo de nano-tarefa

- **Tarefa:** `11.1.1`
- **Responsável:** Ryan Amorim
- **Arquivo(s) Alvo:** `SRC progressao/CatalogoDePoderes.java`
- **Ação:** Criar `static Habilidade criarPorNome(String nome)` com `switch` cujo `default` lança `IllegalArgumentException`.
- **Commit:** `feat: esqueleto do catalogo de poderes`
- **Tag:** `v0.5.316`

## Commits e integração

- Os desenvolvedores implementam as nano-tarefas diretamente na branch `develop`.
- Cada nano-tarefa corresponde a um commit, utilizando a mensagem definida em seu respectivo card.
- As mensagens de commit devem seguir o padrão Conventional Commits, respeitando o texto estabelecido no planejamento.
- As tags são definidas previamente nos cards e devem seguir a sequência de versões estabelecida na documentação de planejamento.
- Não devem ser criados commits ou tags arbitrários que contrariem o planejamento. Caso seja necessário alterar o escopo ou a identificação de uma nano-tarefa, a documentação deve ser atualizada de acordo com o procedimento adotado pela equipe.
- Quando um erro exigir uma correção isolada, deve ser criada uma branch no formato `fix/<dev>-<id-tarefa>`, substituindo `<dev>` pelo identificador do desenvolvedor e `<id-tarefa>` pelo identificador da tarefa relacionada. Exemplo: `fix/ryan-11.1.1`.
- Após a correção, as alterações devem ser integradas à `develop`, preferencialmente por Pull Request revisado pelo outro desenvolvedor.
- A branch `main` permanece reservada à versão final estável. A tag `v1.0.0` será aplicada quando o projeto estiver concluído e pronto para entrega, conforme o processo de finalização definido pela equipe.

## Fluxo de trabalho

1. Consultar a documentação de planejamento e selecionar uma nano-tarefa pendente.
2. Verificar as informações do card: identificador, responsável, arquivos-alvo, ação, commit e tag.
3. Implementar a tarefa diretamente na branch `develop`.
4. Validar a implementação e conferir se ela atende ao escopo definido no card.
5. Registrar o commit com a mensagem especificada no planejamento.
6. Aplicar a tag prevista para a nano-tarefa, respeitando as regras de versionamento da equipe.
7. Se houver um erro que precise ser isolado, criar uma branch `fix`, realizar a correção e integrá-la à `develop`.
8. Continuar o desenvolvimento e a validação na `develop` até que o projeto esteja concluído e estável.
9. Integrar a versão final à `main` e identificá-la com a tag `v1.0.0`.

## Regras gerais

- A documentação de planejamento de nano-tarefas é a referência oficial para a execução do trabalho.
- Cada card representa uma unidade de trabalho com escopo e identificação definidos.
- Os dois desenvolvedores utilizam a `develop` como branch principal de desenvolvimento.
- Branches auxiliares são utilizadas quando uma correção precisar ser isolada.
- Commits e tags devem manter correspondência com o planejamento.
- A `main` é destinada exclusivamente à versão final estável `1.0.0` do projeto.
