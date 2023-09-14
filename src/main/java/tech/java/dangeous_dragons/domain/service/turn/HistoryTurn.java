package tech.java.dangeous_dragons.domain.service.turn;

import lombok.Data;
import tech.java.dangeous_dragons.application.payloads.response.turn.SimpleTurnResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

import java.util.List;

@Data
public class HistoryTurn {
    private Battle battle;
    private List<SimpleTurnResponse> turns;
}
