package tech.java.dangeous_dragons.application.mapper;

import tech.java.dangeous_dragons.application.payloads.response.turn.SimpleTurnResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

public class TurnToSimpleTurnResponseMapper {
    public static SimpleTurnResponse execute(Turn turn) {

        SimpleTurnResponse simpleTurnResponse = new SimpleTurnResponse();
        simpleTurnResponse.setId(turn.getId());
        simpleTurnResponse.setTurnOrder(turn.getTurnOrder());
        simpleTurnResponse.setAttacker(turn.getAttacker());
        simpleTurnResponse.setDiceAttack(turn.getDiceAttack());
        simpleTurnResponse.setAttackForce(turn.getAttackForce());
        simpleTurnResponse.setDiceDefense(turn.getDiceDefense());
        simpleTurnResponse.setDefenseForce(turn.getDefenseForce());
        simpleTurnResponse.setDiceDamage(turn.getDiceDamage());
        simpleTurnResponse.setDamageForce(turn.getDamageForce());
        simpleTurnResponse.setInitialHealthCharacter(turn.getInitialHealthCharacter());
        simpleTurnResponse.setFinalHealthCharacter(turn.getFinalHealthCharacter());
        simpleTurnResponse.setInitialHealthOpponent(turn.getInitialHealthOpponent());
        simpleTurnResponse.setFinalHealthOpponent(turn.getFinalHealthOpponent());
        simpleTurnResponse.setCreatedAt(turn.getCreatedAt());
        simpleTurnResponse.setUpdatedAt(turn.getUpdatedAt());
        return simpleTurnResponse;
    }
}
