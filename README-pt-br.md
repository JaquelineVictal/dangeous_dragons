# Como Iniciar a Aplicação

1. Execute `docker-compose up` para iniciar o banco de dados e a aplicação.

2. Para rodar o projeto Java com Spring Boot (Maven), siga os seguintes passos:
    - [Passo 1]: Clone o repositório para o seu ambiente local.
    - [Passo 2]: Navegue até o diretório do projeto.
    - [Passo 3]: Execute o comando `docker compose up`
    - [Passo 4]: Execute o comando `mvn spring-boot:run` para iniciar a aplicação.

# Como Jogar

## Criando o Seu Personagem

1. Crie o seu personagem (Herói ou Monstro) dando um nome para ele e escolhendo sabiamente entre os tipos de personagens
   disponíveis:

### Heróis

- **Guerreiro**
    - Vida: 20
    - Força: 7
    - Defesa: 5
    - Agilidade: 6
    - Quantidade de Dados: 1
    - Faces do Dado: 12

- **Bárbaro**
    - Vida: 21
    - Força: 10
    - Defesa: 2
    - Agilidade: 5
    - Quantidade de Dados: 2
    - Faces do Dado: 8

- **Cavaleiro**
    - Vida: 26
    - Força: 6
    - Defesa: 8
    - Agilidade: 3
    - Quantidade de Dados: 2
    - Faces do Dado: 6

### Monstros

- **Orc**
    - Vida: 42
    - Força: 7
    - Defesa: 1
    - Agilidade: 2
    - Quantidade de Dados: 3
    - Faces do Dado: 4

- **Gigante**
    - Vida: 34
    - Força: 10
    - Defesa: 4
    - Agilidade: 4
    - Quantidade de Dados: 2
    - Faces do Dado: 6

- **Lobisomem**
    - Vida: 34
    - Força: 7
    - Defesa: 4
    - Agilidade: 7
    - Quantidade de Dados: 2
    - Faces do Dado: 4

## Iniciando uma Batalha

Agora você pode começar uma batalha. Você sempre lutará contra um monstro, podendo escolher seu oponente ou deixar a
sorte decidir por você.

Ao iniciar uma batalha, será determinado quem começará atacando, conhecido como "iniciativa". Para isso, você e seu
oponente jogarão um dado de 20 faces cada um. O jogador que tirar o maior valor terá a iniciativa (empates não são
permitidos). Lembre-se de que a iniciativa será a mesma para toda a batalha.

## Turno

O turno é dividido em duas partes: ataque e defesa.

### Ataque

O ataque é simples: você jogará um dado de 12 faces, somará o resultado com sua Força e Agilidade.

### Defesa

A defesa é calculada da mesma forma: jogando um dado de 12 faces, somando com sua Defesa e Agilidade.

Se o valor do ataque for maior que a defesa, o dano será calculado.

Se o valor do ataque for menor ou igual à defesa, o defensor realizará uma defesa bem-sucedida e não receberá dano.

## Dano

Se a defesa for menor do que o ataque, você precisará calcular o dano. O cálculo é simples: jogue um dado de acordo com
seu personagem e some o valor de sua Força.

Exemplo:

- Bárbaro: quantidade de dados x faces do dado (2 números aleatórios de 1 a 8, soma de 2 a 16).
- Orc: quantidade de dados x faces do dado (3 números aleatórios de 1 a 4, soma de 3 a 12).

## Pontos de Vida

Por fim, temos os pontos de vida (PV) do personagem. Ao sofrer dano, subtraia o valor do dano dos PV do personagem. Se o
defensor ficar com zero ou menos de PV, a luta terminará instantaneamente.

Se o seu defensor sobreviver ao ataque, é a vez dele de atacar. Prepare-se!

Se ambos ainda tiverem pontos de vida no final do turno, você pode começar um novo turno.
