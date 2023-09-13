package tech.java.dangeous_dragons.domain.payloads.request;

import lombok.Data;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;

@Data
public class MakeTurnRequest {
    private CharacterConfigResponse attackerConfig;
    private CharacterConfigResponse defenderConfig;
    private Battle battle;
    private int turnOrder;
    private String attacker;
    private Boolean attackerIsCharacter;
    private double initialHealthAttacker;
    private double initialHealthDefender;

}
