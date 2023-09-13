package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.TurnToTurnResponseMapper;
import tech.java.dangeous_dragons.application.payloads.request.turn.TurnRequest;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.application.payloads.response.turn.TurnResponse;
import tech.java.dangeous_dragons.domain.mapper.BattleReponseToBattleMapper;
import tech.java.dangeous_dragons.domain.service.battle.GetByIdAndIsActiveService;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.turn.TurnRepository;

import java.util.Objects;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CreateTurnService {
    private final GetByIdAndIsActiveService getByIdAndIsActiveService;
    private final TurnRepository turnRepository;
    private final Random random = new Random();

    public TurnResponse execute(TurnRequest turnRequest) {

        BattleResponse battleResponse = getByIdAndIsActiveService.execute(turnRequest.battleId());

        if (battleResponse.getCurrentTurn() == null) return isFirstTurn(battleResponse);


    }

    private TurnResponse isFirstTurn(BattleResponse battleResponse) {
        if (attackerIsCharacter(battleResponse)) {

        }
    }

    private Boolean attackerIsCharacter(BattleResponse battleResponse) {
        return Objects.equals(battleResponse.getFirstAttack(), battleResponse.getCharacterResponse().getName());
    }

    private TurnResponse characterAttack(BattleResponse battleResponse) {
        ForceCalculation attack = characterAttackForce(battleResponse.getCharacterResponse());
        ForceCalculation defense = opponentDefenseForce(battleResponse.getOpponentConfigResponse());

        ForceCalculation damage = attack.getForce() > defense.getForce()
                ? characterDamageForce(battleResponse.getCharacterResponse())
                : null;

        int diceDamage = damage != null
                ? damage.getDiceValue()
                : 0;

        double damageForce = damage != null
                ? damage.getForce()
                : 0;

        double finalHealthOpponent = damage != null
                ? battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints() - damage.getForce()
                : battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints();

        Battle battle = BattleReponseToBattleMapper.execute(battleResponse);
        battle.setIsActive(finalHealthOpponent > 0);

        Turn turn = new Turn();
        turn.setBattle(battle);
        turn.setTurnOrder(1);
        turn.setAttacker(battleResponse.getCharacterResponse().getName());
        turn.setDiceAttack(attack.getDiceValue());
        turn.setAttackForce(attack.getForce());
        turn.setDiceDefense(defense.getDiceValue());
        turn.setDamageForce(defense.getForce());
        turn.setDiceDamage(diceDamage);
        turn.setDamageForce(damageForce);
        turn.setInitialHealthCharacter(battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints());
        turn.setFinalHealthCharacter(battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints());
        turn.setInitialHealthOpponent(battleResponse.getOpponentConfigResponse().getHealthPoints());
        turn.setFinalHealthOpponent(finalHealthOpponent);

        Turn turnSaved = turnRepository.save(turn);

        return TurnToTurnResponseMapper.execute(turnSaved);
    }

    private ForceCalculation characterAttackForce(CharacterResponse characterResponse) {
        return getForceCalculation(characterResponse.getCharacterConfigResponse().getAttack(),
                characterResponse.getCharacterConfigResponse().getAgility());
    }

    private ForceCalculation characterDefenseForce(CharacterResponse characterResponse) {
        return getForceCalculation(characterResponse.getCharacterConfigResponse().getDefense(),
                characterResponse.getCharacterConfigResponse().getAgility());
    }

    private ForceCalculation characterDamageForce(CharacterResponse characterResponse) {
        int quantityDice = characterResponse.getCharacterConfigResponse().getQuantityDice();
        int diceFaces = characterResponse.getCharacterConfigResponse().getDiceFaces();

        int diceDamage = random.nextInt(quantityDice, quantityDice * diceFaces);
        double damageForce = diceDamage + characterResponse.getCharacterConfigResponse().getAttack();

        ForceCalculation forceCalculation = new ForceCalculation();
        forceCalculation.setForce(damageForce);
        forceCalculation.setDiceValue(diceDamage);
        return forceCalculation;
    }

    private ForceCalculation opponentAttackForce(CharacterConfigResponse opponentResponse) {
        return getForceCalculation(opponentResponse.getAttack(),
                opponentResponse.getAgility());
    }

    private ForceCalculation opponentDefenseForce(CharacterConfigResponse opponentResponse) {
        return getForceCalculation(opponentResponse.getDefense(),
                opponentResponse.getAgility());
    }

    private ForceCalculation opponentDamageForce(CharacterConfigResponse opponentResponse) {
        int quantityDice = opponentResponse.getQuantityDice();
        int diceFaces = opponentResponse.getDiceFaces();

        int diceDamage = random.nextInt(quantityDice, quantityDice * diceFaces);
        double damageForce = diceDamage + opponentResponse.getAttack();

        ForceCalculation forceCalculation = new ForceCalculation();
        forceCalculation.setForce(damageForce);
        forceCalculation.setDiceValue(diceDamage);
        return forceCalculation;
    }

    private ForceCalculation getForceCalculation(double attackOrDefense, double agility) {
        int diceDefense = random.nextInt(12);
        double defenseForce = diceDefense + attackOrDefense + agility;

        ForceCalculation forceCalculation = new ForceCalculation();
        forceCalculation.setForce(defenseForce);
        forceCalculation.setDiceValue(diceDefense);
        return forceCalculation;
    }

}
