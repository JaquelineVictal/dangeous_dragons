package tech.java.dangeous_dragons.application.mapper;

import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.application.payloads.response.turn.TurnResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

public class TurnToTurnResponseMapper {
    public static TurnResponse execute(Turn turn) {
        BattleResponse battleResponse = BattleToBattleResponseMapper.execute(turn.getBattle());

        TurnResponse turnResponse = new TurnResponse();
        turnResponse.setId(turn.getId());
        turnResponse.setBattleResponse(battleResponse);
        turnResponse.setTurnOrder(turn.getTurnOrder());
        turnResponse.setAttacker(turn.getAttacker());
        turnResponse.setDiceAttack(turn.getDiceAttack());
        turnResponse.setAttackForce(turn.getAttackForce());
        turnResponse.setDiceDefense(turn.getDiceDefense());
        turnResponse.setDefenseForce(turn.getDefenseForce());
        turnResponse.setDiceDamage(turn.getDiceDamage());
        turnResponse.setDamageForce(turn.getDamageForce());
        turnResponse.setInitialHealthCharacter(turn.getInitialHealthCharacter());
        turnResponse.setFinalHealthCharacter(turn.getFinalHealthCharacter());
        turnResponse.setInitialHealthOpponent(turn.getInitialHealthOpponent());
        turnResponse.setFinalHealthOpponent(turn.getFinalHealthOpponent());
        turnResponse.setCreatedAt(turn.getCreatedAt());
        turnResponse.setUpdatedAt(turn.getUpdatedAt());
        return turnResponse;
    }
}
