# Os quatro pilares da POO no MVP

Os exemplos abaixo vêm do código executável do MVP. Eles aparecem no fluxo da batalha contra a Mula sem Cabeça.

## Abstração

`Personagem` define o que todos os combatentes compartilham, sem decidir como cada um ataca. A interface `Habilidade` descreve as operações disponíveis para qualquer habilidade.

- Referências: [`Personagem.java`](../src/main/java/br/folclore/modelo/Personagem.java), método `atacar(Personagem)`; [`Habilidade.java`](../src/main/java/br/folclore/habilidades/Habilidade.java), método `executar(Personagem, Personagem)`.
- Na batalha: `Batalha` pode chamar o ataque e a habilidade do personagem escolhido sem depender de uma classe concreta.

```java
public abstract int atacar(Personagem alvo);
public abstract String getNomeAtaqueBasico();
```

## Encapsulamento

Os atributos do personagem são privados. O construtor valida os dados iniciais, e os métodos de domínio controlam alterações de PV em vez de expor acesso direto.

- Referência: [`Personagem.java`](../src/main/java/br/folclore/modelo/Personagem.java), construtor, `receberDano(int)` e `curar(int)`.
- Na batalha: `Batalha` aplica ações pelos métodos do personagem; a interface apenas consulta os valores para desenhar o HUD.

```java
private int pvAtual;

public int receberDano(int dano) {
    if (dano < 0) {
        throw new IllegalArgumentException("O dano não pode ser negativo.");
    }
    int pvAntesDoDano = pvAtual;
    pvAtual = Math.max(0, pvAtual - dano);
    return pvAntesDoDano - pvAtual;
}
```

## Herança

Saci, Lobisomem e Mula sem Cabeça herdam os dados e comportamentos comuns de `Personagem`; cada um fornece seus próprios ataques. As habilidades concretas também reutilizam o controle de usos de `HabilidadeBase`.

- Referências: [`Saci.java`](../src/main/java/br/folclore/modelo/Saci.java), [`Lobisomem.java`](../src/main/java/br/folclore/modelo/Lobisomem.java), [`MulaSemCabeca.java`](../src/main/java/br/folclore/modelo/MulaSemCabeca.java), [`HabilidadeDano.java`](../src/main/java/br/folclore/habilidades/HabilidadeDano.java) e [`HabilidadeCura.java`](../src/main/java/br/folclore/habilidades/HabilidadeCura.java).
- Na batalha: a sessão cria a Mula como chefe, enquanto a seleção fornece Saci ou Lobisomem como jogador.

```java
public class Saci extends Personagem { ... }
public class HabilidadeCura extends HabilidadeBase { ... }
```

## Polimorfismo

Uma chamada a `atacar` executa o comportamento da classe concreta do combatente. Do mesmo modo, `usarHabilidade` delega a `Habilidade.executar`: `HabilidadeCura` recupera PV do usuário, enquanto `HabilidadeDano` reduz PV do alvo. A Mula também sobrescreve `deveUsarHabilidade` para escolher o Galope Infernal nos turnos pares quando ainda há usos.

- Referências: `atacar(Personagem)` em [`Saci.java`](../src/main/java/br/folclore/modelo/Saci.java), [`Lobisomem.java`](../src/main/java/br/folclore/modelo/Lobisomem.java) e [`MulaSemCabeca.java`](../src/main/java/br/folclore/modelo/MulaSemCabeca.java); `executar` em [`HabilidadeCura.java`](../src/main/java/br/folclore/habilidades/HabilidadeCura.java) e [`HabilidadeDano.java`](../src/main/java/br/folclore/habilidades/HabilidadeDano.java); `deveUsarHabilidade(int)` em [`MulaSemCabeca.java`](../src/main/java/br/folclore/modelo/MulaSemCabeca.java).
- Na batalha: o ataque ou a habilidade selecionada é despachada pelo tipo real do objeto, e a lógica da batalha não precisa distinguir Saci de Lobisomem.
- Demonstração: [`DemoPilares.java`](../src/main/java/br/folclore/demo/DemoPilares.java) percorre os três combatentes por uma lista de `Personagem` e imprime seus resumos, ataques e mensagens de habilidade.
