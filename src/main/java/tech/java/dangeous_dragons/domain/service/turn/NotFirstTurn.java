package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.BattleToBattleResponseMapper;
import tech.java.dangeous_dragons.application.mapper.TurnToSimpleTurnResponseMapper;
import tech.java.dangeous_dragons.application.payloads.response.turn.HistoryTurnResponse;
import tech.java.dangeous_dragons.application.payloads.response.turn.SimpleTurnResponse;
import tech.java.dangeous_dragons.domain.service.turn.attacker.CharacterAttack;
import tech.java.dangeous_dragons.domain.service.turn.attacker.OpponentAttack;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.turn.TurnRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotFirstTurn {

    private final CharacterAttack characterAttack;
    private final TurnRepository turnRepository;
    private final BattleRepository battleRepository;
    private final OpponentAttack opponentAttack;

    public HistoryTurnResponse execute(Turn turn, Battle battle, int turnOrder, Boolean attackerIsCharacter) {
        if (Boolean.TRUE.equals(attackerIsCharacter)) {
            Turn nextTurn = characterAttack.execute(
                    battle,
                    turnOrder,
                    turn.getFinalHealthCharacter(),
                    turn.getFinalHealthOpponent()
            );
            Turn nextTurnSaved = turnRepository.save(nextTurn);
            Battle updateBattle = updateBattle(nextTurnSaved);

            List<SimpleTurnResponse> turns = new ArrayList<>();
            turns.add(TurnToSimpleTurnResponseMapper.execute(turn));
            turns.add(TurnToSimpleTurnResponseMapper.execute(nextTurnSaved));

            HistoryTurnResponse historyTurnResponse = new HistoryTurnResponse();
            historyTurnResponse.setBattleResponse(BattleToBattleResponseMapper.execute(updateBattle));
            historyTurnResponse.setTurns(turns);

            return historyTurnResponse;
        }
        Turn nextTurn = opponentAttack.execute(
                battle,
                turnOrder,
                turn.getFinalHealthCharacter(),
                turn.getFinalHealthOpponent()
        );

        Turn nextTurnSaved = turnRepository.save(nextTurn);
        Battle updateBattle = updateBattle(nextTurnSaved);

        List<SimpleTurnResponse> turns = new ArrayList<>();
        turns.add(TurnToSimpleTurnResponseMapper.execute(turn));
        turns.add(TurnToSimpleTurnResponseMapper.execute(nextTurnSaved));

        HistoryTurnResponse historyTurnResponse = new HistoryTurnResponse();
        historyTurnResponse.setBattleResponse(BattleToBattleResponseMapper.execute(updateBattle));
        historyTurnResponse.setTurns(turns);

        return historyTurnResponse;
    }

    private Battle updateBattle(Turn turn) {
        Battle updateBattle = turn.getBattle();
        updateBattle.setCurrentTurn(turn);
        return battleRepository.save(updateBattle);
    }
}
