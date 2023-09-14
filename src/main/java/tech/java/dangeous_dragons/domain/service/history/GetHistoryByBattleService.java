package tech.java.dangeous_dragons.domain.service.history;


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

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetHistoryByBattleService {

    private final BattleRepository battleRepository;
    private final TurnRepository turnRepository;

    public HistoryTurnResponse execute(Long battleId) {
        Battle battle = battleRepository.getReferenceById(battleId);
        List<Turn> turns = turnRepository.findByBattleId(battleId);


        List<SimpleTurnResponse> simpleTurnsResponse = turns
                .stream()
                .map(TurnToSimpleTurnResponseMapper::execute)
                .toList();

        HistoryTurnResponse historyTurnResponse = new HistoryTurnResponse();
        historyTurnResponse.setBattleResponse(BattleToBattleResponseMapper.execute(battle));
        historyTurnResponse.setTurns(simpleTurnsResponse);

        return historyTurnResponse;
    }
}
