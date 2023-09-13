package tech.java.dangeous_dragons.domain.mapper;

import tech.java.dangeous_dragons.application.payloads.response.turn.TurnResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

public class TurnResponseToTurnMapper {
    public static Turn execute(TurnResponse turnResponse) {
        Turn turn = new Turn();
        Battle battle = BattleReponseToBattleMapper.execute(turnResponse.getBattleResponse());

        turn.setId(turnResponse.getId());
        turn.setBattle(battle);
        turn.setTurnOrder(turnResponse.getTurnOrder());
        turn.setAttacker(turnResponse.getAttacker());
        turn.setDiceAttack(turnResponse.getDiceAttack());
        turn.setAttackForce(turnResponse.getAttackForce());
        turn.setDiceDefense(turnResponse.getDiceDefense());
        turn.setDefenseForce(turnResponse.getDefenseForce());
        turn.setDiceDamage(turnResponse.getDiceDamage());
        turn.setDamageForce(turnResponse.getDamageForce());
        turn.setInitialHealthCharacter(turnResponse.getInitialHealthCharacter());
        turn.setFinalHealthCharacter(turnResponse.getFinalHealthCharacter());
        turn.setInitialHealthOpponent(turnResponse.getInitialHealthOpponent());
        turn.setFinalHealthOpponent(turnResponse.getFinalHealthOpponent());
        turn.setCreatedAt(turnResponse.getCreatedAt());
        turn.setUpdatedAt(turnResponse.getUpdatedAt());

        return turn;
    }
}
