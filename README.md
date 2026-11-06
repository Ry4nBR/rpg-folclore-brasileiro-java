# RPG do Folclore Brasileiro

## MVP

Protótipo jogável em Português do Brasil para validar a arquitetura orientada a objetos, o combate por turnos e o fluxo de interface Swing:
**Menu → Seleção de personagem → Batalha → Resultado → Menu**.

O MVP está na branch `mvp/prototipo`. Ele contém um ginásio (Fogo), dois personagens jogáveis (Saci e Lobisomem) e um chefe (Mula sem Cabeça).

### Requisitos

- JDK 17
- Maven 3.8 ou superior

### Executar

Na raiz do repositório, rode uma das opções:

```shell
mvn compile exec:java
```

Para gerar e executar o JAR:

```shell
mvn package
java -jar target/rpg-folclore-mvp.jar
```

### Controles

- **Mouse:** acionar **Jogar**/**Sair**, selecionar e confirmar o personagem, escolher **Atacar** ou **Habilidade** e acionar os botões das telas de resultado.
- **Setas esquerda/direita:** escolher Saci ou Lobisomem na tela de seleção.
- **Enter:** abrir a seleção no menu, confirmar o personagem ou avançar a mensagem atual da batalha.
- **Espaço:** avançar a mensagem atual da batalha.
- **Esc:** voltar ao menu a partir da tela de seleção.

Durante a batalha, leia as mensagens em sequência; uma nova ação só pode ser escolhida depois que as mensagens pendentes forem avançadas. A tela de derrota oferece **Tentar novamente** e **Voltar ao menu**.

### Regras do combate

- O jogador escolhe Saci ou Lobisomem; ambos enfrentam a Mula sem Cabeça.
- A ordem é fixa: primeiro o jogador, depois o chefe. Se o chefe for derrotado, ele não age naquele turno.
- O dano é `max(1, ataque do atacante + poder do golpe − defesa do alvo)`.
- O Saci usa **Rasteira de Vento** e pode usar **Ventos Revigorantes**, que cura 20% do PV máximo, até três vezes.
- O Lobisomem usa **Garra Selvagem** e pode usar **Uivo Brutal** para causar dano, até duas vezes.
- A Mula usa **Coice Flamejante** nos turnos ímpares e **Galope Infernal** nos turnos pares enquanto ainda tiver usos (dois). Sem usos, volta ao ataque básico.
- Uma habilidade sem usos fica desabilitada e não consome o turno.
- A vitória ocorre quando o PV da Mula chega a zero e conclui o Ginásio do Fogo. Se o PV do jogador chegar a zero, é possível tentar novamente com uma batalha nova.
- O tipo dos personagens é exibido, mas não altera o dano.

### Escopo

Este MVP não inclui outros ginásios ou personagens, exploração, progressão por XP, troca ou desbloqueio de habilidades, salvamento, fuga, consulta de status, vantagens de tipo, sprites, animações ou áudio.

### Documentação

- [Escopo e regras do MVP](docs/mvp-escopo.md)
- [Planejamento de tarefas](docs/planejamento_nano_tarefas_mvp_rpg_folclore.md)
- [Diagramas de classes, estados e telas](docs/diagrama-classes-mvp.md)
- [Pilares da POO no MVP](docs/pilares-poo-mvp.md)
- [Checklist de validação](docs/checklist-validacao-mvp.md)
- [Progresso da execução](docs/planning/EXECUTION_PROGRESS_MVP_RPG_FOLCLORE.md)
