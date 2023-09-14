package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.payloads.request.turn.TurnRequest;
import tech.java.dangeous_dragons.application.payloads.response.turn.HistoryTurnResponse;
import tech.java.dangeous_dragons.common.exceptions.BattleNotActiveException;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.turn.TurnRepository;

@Service
@RequiredArgsConstructor
public class CreateTurnService {


    private final BattleRepository battleRepository;
    private final TurnRepository turnRepository;
    private final NotFirstTurn notFirstTurn;
    private final FirstTurn firstTurn;


    public HistoryTurnResponse execute(TurnRequest turnRequest) {

        Battle battle = battleRepository.findByIdAndIsActiveTrue(turnRequest.battleId())
                .orElseThrow(BattleNotActiveException::new);

        if (battle.getCurrentTurn() == null) return firstTurn.execute(battle);

        Turn turn = turnRepository.getReferenceById(battle.getCurrentTurn().getId());

        if (Boolean.TRUE.equals(attackerIsCharacter(battle))) {
            int currentTurnOrder = turn.getTurnOrder() + 1;
            HistoryTurnResponse historyTurnResponse = notFirstTurn.execute(turn, battle, currentTurnOrder, true);

            if (Boolean.FALSE.equals(historyTurnResponse.getBattleResponse().getIsActive())) {
                return historyTurnResponse;
            }

            Battle updateBattle = battleRepository.getReferenceById(turnRequest.battleId());

            Turn newTurn = turnRepository.getReferenceById(updateBattle.getCurrentTurn().getId());
            return notFirstTurn.execute(newTurn, updateBattle, currentTurnOrder + 1, false);
        }
        int currentTurnOrder = turn.getTurnOrder() + 1;
        HistoryTurnResponse historyTurnResponse = notFirstTurn.execute(turn, battle, currentTurnOrder, false);

        if (Boolean.FALSE.equals(historyTurnResponse.getBattleResponse().getIsActive())) {
            return historyTurnResponse;
        }

        Battle updateBattle = battleRepository.getReferenceById(turnRequest.battleId());

        Turn newTurn = turnRepository.getReferenceById(updateBattle.getCurrentTurn().getId());
        return notFirstTurn.execute(newTurn, updateBattle, currentTurnOrder + 1, true);
    }

    private Boolean attackerIsCharacter(Battle battle) {
        String firstAttack = battle.getFirstAttack();
        String characterName = battle.getCharacter().getName();

        return (firstAttack.equals(characterName));
    }
}
