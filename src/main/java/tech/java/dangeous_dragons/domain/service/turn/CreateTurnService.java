package tech.java.dangeous_dragons.domain.service.turn;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.application.mapper.BattleToBattleResponseMapper;
import tech.java.dangeous_dragons.application.mapper.TurnToTurnResponseMapper;
import tech.java.dangeous_dragons.application.payloads.request.turn.TurnRequest;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;
import tech.java.dangeous_dragons.application.payloads.response.turn.TurnResponse;
import tech.java.dangeous_dragons.domain.mapper.BattleReponseToBattleMapper;
import tech.java.dangeous_dragons.domain.mapper.TurnResponseToTurnMapper;
import tech.java.dangeous_dragons.domain.payloads.request.MakeTurnRequest;
import tech.java.dangeous_dragons.domain.service.battle.GetByIdAndIsActiveService;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.battle.BattleRepository;
import tech.java.dangeous_dragons.infrastructure.persistence.repository.turn.TurnRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class CreateTurnService {
    private final GetByIdAndIsActiveService getByIdAndIsActiveService;
    private final MakeTurnService makeTurnService;
    private final TurnRepository turnRepository;
    private final BattleRepository battleRepository;

    public List<TurnResponse> execute(TurnRequest turnRequest) {

        BattleResponse battleResponse = getByIdAndIsActiveService.execute(turnRequest.battleId());

        if (battleResponse.getCurrentTurn() == null) return isFirstTurn(battleResponse);

        Turn turn = turnRepository.getReferenceById(battleResponse.getCurrentTurn().getId());

        if (Boolean.TRUE.equals(attackerIsCharacter(battleResponse))) {
            int currentTurnOrder = turn.getTurnOrder() + 1;
            List<TurnResponse> turnResponseList = isNotFirsFirstTurn(turn, battleResponse, currentTurnOrder, true);

            if (Boolean.FALSE.equals(turnResponseList.get(1).getBattleResponse().getIsActive()))
                return turnResponseList;

            Turn newTurn = TurnResponseToTurnMapper.execute(turnResponseList.get(1));
            return isNotFirsFirstTurn(newTurn, turnResponseList.get(1).getBattleResponse(), currentTurnOrder + 1, false);
        }
        int currentTurnOrder = turn.getTurnOrder() + 1;
        List<TurnResponse> turnResponseList = isNotFirsFirstTurn(turn, battleResponse, currentTurnOrder, false);

        if (Boolean.FALSE.equals(turnResponseList.get(1).getBattleResponse().getIsActive())) return turnResponseList;

        Turn newTurn = TurnResponseToTurnMapper.execute(turnResponseList.get(1));
        return isNotFirsFirstTurn(newTurn, turnResponseList.get(1).getBattleResponse(), currentTurnOrder + 1, true);
    }

    private List<TurnResponse> isFirstTurn(BattleResponse battleResponse) {
        if (Boolean.TRUE.equals(attackerIsCharacter(battleResponse))) {
            Turn turn = characterAttack(
                    battleResponse,
                    1,
                    battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints(),
                    battleResponse.getOpponentConfigResponse().getHealthPoints()
            );
            Turn turnSaved = turnRepository.save(turn);

            if (Boolean.FALSE.equals(turnSaved.getBattle().getIsActive()))
                return Collections.singletonList(TurnToTurnResponseMapper.execute(turnSaved));

            BattleResponse secondBattleResponse = BattleToBattleResponseMapper.execute(turnSaved.getBattle());

            return isNotFirsFirstTurn(turnSaved, secondBattleResponse, 2, false);
        }

        Turn turn = opponentAttack(battleResponse,
                1,
                battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints(),
                battleResponse.getOpponentConfigResponse().getHealthPoints()
        );

        Turn turnSaved = turnRepository.save(turn);

        if (Boolean.FALSE.equals(turnSaved.getBattle().getIsActive()))
            return Collections.singletonList(TurnToTurnResponseMapper.execute(turnSaved));

        BattleResponse secondBattleResponse = BattleToBattleResponseMapper.execute(turnSaved.getBattle());

        return isNotFirsFirstTurn(turnSaved, secondBattleResponse, 2, true);
    }

    private List<TurnResponse> isNotFirsFirstTurn(Turn turn, BattleResponse battleResponse, int turnOrder, Boolean attackerIsCharacter) {
        if (Boolean.TRUE.equals(attackerIsCharacter)) {
            Turn nextTurn = opponentAttack(
                    battleResponse,
                    turnOrder,
                    battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints(),
                    turn.getFinalHealthOpponent()
            );
            Turn nextTurnSaved = turnRepository.save(nextTurn);
            Battle secondBattle = nextTurnSaved.getBattle();
            secondBattle.setCurrentTurn(nextTurnSaved);

            battleRepository.save(secondBattle);

            List<TurnResponse> turnResponseList = new ArrayList<>();
            turnResponseList.add(TurnToTurnResponseMapper.execute(turn));
            turnResponseList.add(TurnToTurnResponseMapper.execute(nextTurnSaved));

            return turnResponseList;
        }
        Turn nextTurn = characterAttack(
                battleResponse,
                turnOrder,
                battleResponse.getCharacterResponse().getCharacterConfigResponse().getHealthPoints(),
                turn.getFinalHealthOpponent()
        );
        Turn nextTurnSaved = turnRepository.save(nextTurn);
        Battle secondBattle = nextTurnSaved.getBattle();
        secondBattle.setCurrentTurn(nextTurnSaved);

        battleRepository.save(secondBattle);

        List<TurnResponse> turnResponseList = new ArrayList<>();
        turnResponseList.add(TurnToTurnResponseMapper.execute(turn));
        turnResponseList.add(TurnToTurnResponseMapper.execute(nextTurnSaved));

        return turnResponseList;

    }

    private Boolean attackerIsCharacter(BattleResponse battleResponse) {
        return Objects.equals(battleResponse.getFirstAttack(), battleResponse.getCharacterResponse().getName());
    }

    private Turn characterAttack(BattleResponse battleResponse, int turnOrder, double initialHealthCharacter, double initialHealthOpponent) {

        Battle battle = BattleReponseToBattleMapper.execute(battleResponse);

        MakeTurnRequest makeTurnRequest = new MakeTurnRequest();

        makeTurnRequest.setAttackerConfig(battleResponse.getCharacterResponse().getCharacterConfigResponse());
        makeTurnRequest.setDefenderConfig(battleResponse.getOpponentConfigResponse());
        makeTurnRequest.setBattle(battle);
        makeTurnRequest.setTurnOrder(turnOrder);
        makeTurnRequest.setAttacker(battleResponse.getCharacterResponse().getName());
        makeTurnRequest.setAttackerIsCharacter(true);
        makeTurnRequest.setInitialHealthAttacker(initialHealthCharacter);
        makeTurnRequest.setInitialHealthDefender(initialHealthOpponent);

        return makeTurnService.execute(makeTurnRequest);
    }

    private Turn opponentAttack(BattleResponse battleResponse, int turnOrder, double initialHealthCharacter, double initialHealthOpponent) {

        Battle battle = BattleReponseToBattleMapper.execute(battleResponse);

        MakeTurnRequest makeTurnRequest = new MakeTurnRequest();

        makeTurnRequest.setAttackerConfig(battleResponse.getCharacterResponse().getCharacterConfigResponse());
        makeTurnRequest.setDefenderConfig(battleResponse.getOpponentConfigResponse());
        makeTurnRequest.setBattle(battle);
        makeTurnRequest.setTurnOrder(turnOrder);
        makeTurnRequest.setAttacker(battleResponse.getCharacterResponse().getName());
        makeTurnRequest.setAttackerIsCharacter(false);
        makeTurnRequest.setInitialHealthAttacker(initialHealthOpponent);
        makeTurnRequest.setInitialHealthDefender(initialHealthCharacter);

        return makeTurnService.execute(makeTurnRequest);
    }
}
