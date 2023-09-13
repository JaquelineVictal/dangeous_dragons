package tech.java.dangeous_dragons.application.payloads.response.battle;

import lombok.Data;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.turn.Turn;

import java.time.Instant;

@Data
public class BattleResponse {
    private Long id;
    private CharacterResponse characterResponse;
    private CharacterConfigResponse opponentConfigResponse;
    private String firstAttack;
    private Turn currentTurn;
    private Boolean isActive;
    private Instant createdAt;
    private Instant updatedAt;


}
