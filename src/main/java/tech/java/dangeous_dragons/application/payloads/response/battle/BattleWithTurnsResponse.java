package tech.java.dangeous_dragons.application.payloads.response.battle;

import io.swagger.v3.core.util.Json;
import lombok.Data;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

import java.time.Instant;

@Data
public class BattleWithTurnsResponse {
    private Long id;
    private Character character;
    private CharacterConfig opponentConfig;
    private Long currentTurn;
    private String firstAttack;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    private Json turns;
}





