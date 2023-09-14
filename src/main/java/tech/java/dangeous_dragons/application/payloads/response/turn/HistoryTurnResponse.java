package tech.java.dangeous_dragons.application.payloads.response.turn;

import lombok.Data;
import tech.java.dangeous_dragons.application.payloads.response.battle.BattleResponse;

import java.util.List;

@Data
public class HistoryTurnResponse {
    private BattleResponse battleResponse;
    private List<SimpleTurnResponse> turns;
}
