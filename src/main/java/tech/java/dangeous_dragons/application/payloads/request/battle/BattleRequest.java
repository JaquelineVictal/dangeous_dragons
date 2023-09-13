package tech.java.dangeous_dragons.application.payloads.request.battle;

import jakarta.validation.constraints.NotNull;
import tech.java.dangeous_dragons.common.enums.MonsterTypeEnum;

import java.util.Optional;

public record BattleRequest(
        @NotNull
        long characterId,
        Optional<MonsterTypeEnum> opponentType
) {
}
