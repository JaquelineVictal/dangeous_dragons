# How to Start the Application

1. Run `docker-compose up` to start the database and the application.

2. To run the Java project with Spring Boot (Maven), follow these steps:
    - [Step 1]: Clone the repository to your local environment.
    - [Step 2]: Navigate to the project directory.
    - [Step 3]: Run the command `docker compose up`
    - [Step 4]: Run the command `mvn spring-boot:run` to start the application.

# How to Play

## Creating Your Character

1. Create your character (Hero or Monster) by giving it a name and choosing wisely from the available character types:

### Heroes

- **Warrior**
    - Life: 20
    - Strength: 7
    - Defense: 5
    - Agility: 6
    - Number of Dice: 1
    - Dice Faces: 12

- **Barbarian**
    - Life: 21
    - Strength: 10
    - Defense: 2
    - Agility: 5
    - Number of Dice: 2
    - Dice Faces: 8

- **Knight**
    - Life: 26
    - Strength: 6
    - Defense: 8
    - Agility: 3
    - Number of Dice: 2
    - Dice Faces: 6

### Monsters

- **Orc**
    - Life: 42
    - Strength: 7
    - Defense: 1
    - Agility: 2
    - Number of Dice: 3
    - Dice Faces: 4

- **Giant**
    - Life: 34
    - Strength: 10
    - Defense: 4
    - Agility: 4
    - Number of Dice: 2
    - Dice Faces: 6

- **Werewolf**
    - Life: 34
    - Strength: 7
    - Defense: 4
    - Agility: 7
    - Number of Dice: 2
    - Dice Faces: 4

## Starting a Battle

Now you can start a battle. You will always fight against a monster, and you can choose your opponent or let luck decide
for you.

When starting a battle, it will be determined who starts attacking, known as "initiative." To do this, both you and your
opponent will roll a 20-sided die. The player who rolls the highest value will have the initiative (ties are not
allowed). Keep in mind that the initiative will be the same for the entire battle.

## Turn

The turn is divided into two parts: attack and defense.

### Attack

The attack is simple: you will roll a 12-sided die, sum the result with your Strength and Agility.

### Defense

Defense is calculated in the same way: by rolling a 12-sided die, summing it with your Defense and Agility.

If the attack value is higher than the defense value, the damage will be calculated.

If the attack value is less than or equal to the defense value, the defender will successfully defend and receive no
damage.

## Damage

If the defense is lower than the attack, you will need to calculate the damage. The calculation is simple: roll a die
according to your character and add the value of your Strength.

Example:

- Barbarian: number of dice x dice faces (2 random numbers from 1 to 8, sum of 2 to 16).
- Orc: number of dice x dice faces (3 random numbers from 1 to 4, sum of 3 to 12).

## Hit Points

Finally, we have character hit points (HP). When taking damage, subtract the damage value from the character's HP. If
the defender reaches zero or fewer HP, the battle will end instantly.

If your defender survives the attack, it's their turn to attack. Get ready!

If both characters still have hit points at the end of the turn, you can start a new turn.
