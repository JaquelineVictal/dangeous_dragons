package tech.java.dangeous_dragons.domain.service.turn.attacker;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tech.java.dangeous_dragons.domain.payloads.request.MakeTurnRequest;
import tech.java.dangeous_dragons.domain.service.turn.MakeTurnService;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

@Service
@RequiredArgsConstructor
public class OpponentAttack {
    private final MakeTurnService makeTurnService;

    public Turn execute(Battle battle, int turnOrder, double initialHealthCharacter, double initialHealthOpponent) {


        MakeTurnRequest makeTurnRequest = new MakeTurnRequest();
        makeTurnRequest.setAttackerConfig(battle.getOpponentConfig());
        makeTurnRequest.setDefenderConfig(battle.getCharacter().getCharacterConfig());
        makeTurnRequest.setBattle(battle);
        makeTurnRequest.setTurnOrder(turnOrder);
        makeTurnRequest.setAttacker(battle.getOpponentConfig().getCharacterType().toString());
        makeTurnRequest.setAttackerIsCharacter(false);
        makeTurnRequest.setInitialHealthAttacker(initialHealthOpponent);
        makeTurnRequest.setInitialHealthDefender(initialHealthCharacter);

        return makeTurnService.execute(makeTurnRequest);
    }
}
