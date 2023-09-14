package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.BattleToBattleResponseMapper;
import tech.java.dangeous_dragons.application.mapper.TurnToSimpleTurnResponseMapper;
import tech.java.dangeous_dragons.application.payloads.response.turn.HistoryTurnResponse;
import tech.java.dangeous_dragons.application.payloads.response.turn.SimpleTurnResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.turn.TurnRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FirstTurn {

    private final CharacterAttack characterAttack;
    private final TurnRepository turnRepository;
    private final BattleRepository battleRepository;
    private final OpponentAttack opponentAttack;
    private final NotFirstTurn notFirstTurn;

    public HistoryTurnResponse execute(Battle battle) {
        if (Boolean.TRUE.equals(attackerIsCharacter(battle))) {
            Turn turn = characterAttack.execute(
                    battle,
                    1,
                    battle.getCharacter().getCharacterConfig().getHealthPoints(),
                    battle.getOpponentConfig().getHealthPoints()
            );
            Turn turnSaved = turnRepository.save(turn);

            if (Boolean.FALSE.equals(turnSaved.getBattle().getIsActive())) {
                updateBattle(turnSaved);
                List<SimpleTurnResponse> turns = new ArrayList<>();
                turns.add(TurnToSimpleTurnResponseMapper.execute(turnSaved));

                HistoryTurnResponse historyTurnResponse = new HistoryTurnResponse();
                historyTurnResponse.setBattleResponse(BattleToBattleResponseMapper.execute(turnSaved.getBattle()));
                historyTurnResponse.setTurns(turns);
                return historyTurnResponse;
            }
            return notFirstTurn.execute(turnSaved, turnSaved.getBattle(), 2, false);
        }

        Turn turn = opponentAttack.execute(
                battle,
                1,
                battle.getCharacter().getCharacterConfig().getHealthPoints(),
                battle.getOpponentConfig().getHealthPoints()
        );

        Turn turnSaved = turnRepository.save(turn);

        if (Boolean.FALSE.equals(turnSaved.getBattle().getIsActive())) {
            updateBattle(turnSaved);
            List<SimpleTurnResponse> turns = new ArrayList<>();
            turns.add(TurnToSimpleTurnResponseMapper.execute(turnSaved));

            HistoryTurnResponse historyTurnResponse = new HistoryTurnResponse();
            historyTurnResponse.setBattleResponse(BattleToBattleResponseMapper.execute(turnSaved.getBattle()));
            historyTurnResponse.setTurns(turns);
            return historyTurnResponse;
        }
        return notFirstTurn.execute(turnSaved, turnSaved.getBattle(), 2, true);
    }

    private Boolean attackerIsCharacter(Battle battle) {
        String firstAttack = battle.getFirstAttack();
        String characterName = battle.getCharacter().getName();

        return (firstAttack.equals(characterName));
    }

    private Battle updateBattle(Turn turn) {
        Battle updateBattle = turn.getBattle();
        updateBattle.setCurrentTurn(turn);
        return battleRepository.save(updateBattle);
    }
}
