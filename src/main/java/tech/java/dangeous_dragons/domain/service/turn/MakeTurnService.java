package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.domain.payloads.request.MakeTurnRequest;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

import java.util.Random;

@Service
@RequiredArgsConstructor
public class MakeTurnService {
    private final Random random = new Random();

    public Turn execute(MakeTurnRequest makeTurnRequest) {

        CharacterConfigResponse attackerConfig = makeTurnRequest.getAttackerConfig();
        CharacterConfigResponse defenderConfig = makeTurnRequest.getDefenderConfig();
        Battle battle = makeTurnRequest.getBattle();
        int turnOrder = makeTurnRequest.getTurnOrder();
        String attacker = makeTurnRequest.getAttacker();
        Boolean attackerIsCharacter = makeTurnRequest.getAttackerIsCharacter();
        double initialHealthAttacker = makeTurnRequest.getInitialHealthAttacker();
        double initialHealthDefender = makeTurnRequest.getInitialHealthDefender();


        ForceCalculation attack = getForceCalculation(attackerConfig.getAttack(), attackerConfig.getDefense());
        ForceCalculation defense = getForceCalculation(defenderConfig.getDefense(), attackerConfig.getAgility());

        Boolean isDamage = attack.getForce() > defense.getForce();

        int diceValueDamage = Boolean.TRUE.equals(isDamage)
                ? getDiceValueDamage(attackerConfig.getQuantityDice(), attackerConfig.getDiceFaces())
                : 0;

        double damageForce = Boolean.TRUE.equals(isDamage)
                ? getDamageForce(diceValueDamage, attackerConfig.getAttack())
                : 0;

        double finalHealthDefender = Boolean.TRUE.equals(isDamage)
                ? initialHealthDefender - damageForce
                : initialHealthDefender;

        Boolean battleIsActive = finalHealthDefender > 0;

        battle.setIsActive(battleIsActive);

        Turn turn = new Turn();
        turn.setBattle(battle);
        turn.setTurnOrder(turnOrder);
        turn.setAttacker(attacker);
        turn.setDiceAttack(attack.getDiceValue());
        turn.setAttackForce(attack.getForce());
        turn.setDiceDefense(defense.getDiceValue());
        turn.setDamageForce(defense.getForce());
        turn.setDiceDamage(diceValueDamage);
        turn.setDamageForce(damageForce);

        return setHealth(turn, attackerIsCharacter, initialHealthAttacker, initialHealthDefender, finalHealthDefender);

    }

    private ForceCalculation getForceCalculation(double attackOrDefense, double agility) {
        int diceDefense = random.nextInt(12);
        double defenseForce = diceDefense + attackOrDefense + agility;

        ForceCalculation forceCalculation = new ForceCalculation();
        forceCalculation.setForce(defenseForce);
        forceCalculation.setDiceValue(diceDefense);
        return forceCalculation;
    }

    private Integer getDiceValueDamage(int quantityDice, int diceFaces) {
        return random.nextInt(quantityDice, quantityDice * diceFaces);
    }

    private Double getDamageForce(int diceValueDamage, double attack) {
        return diceValueDamage + attack;
    }

    private Turn setHealth(Turn turn, Boolean attackerIsCharacter, double initialHealthAttacker, double initialHealthDefender, double finalHealthDefender) {
        if (Boolean.TRUE.equals(attackerIsCharacter)) {
            turn.setInitialHealthCharacter(initialHealthAttacker);
            turn.setFinalHealthCharacter(initialHealthAttacker);
            turn.setInitialHealthOpponent(initialHealthDefender);
            turn.setFinalHealthOpponent(finalHealthDefender);
            return turn;
        }
        turn.setInitialHealthCharacter(initialHealthDefender);
        turn.setFinalHealthCharacter(finalHealthDefender);
        turn.setInitialHealthOpponent(initialHealthAttacker);
        turn.setFinalHealthOpponent(initialHealthAttacker);

        return turn;
    }

}
