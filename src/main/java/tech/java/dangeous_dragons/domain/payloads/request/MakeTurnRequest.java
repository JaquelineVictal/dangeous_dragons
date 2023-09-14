package tech.java.dangeous_dragons.domain.payloads.request;

import lombok.Data;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.battle.Battle;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

@Data
public class MakeTurnRequest {
    private CharacterConfig attackerConfig;
    private CharacterConfig defenderConfig;
    private Battle battle;
    private int turnOrder;
    private String attacker;
    private Boolean attackerIsCharacter;
    private double initialHealthAttacker;
    private double initialHealthDefender;

}
