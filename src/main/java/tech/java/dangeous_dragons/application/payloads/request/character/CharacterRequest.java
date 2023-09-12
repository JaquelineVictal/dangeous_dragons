package tech.java.dangeous_dragons.application.payloads.request.character;

import jakarta.validation.constraints.NotNull;
import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;

public record CharacterRequest(
        @NotNull
        String name,
        @NotNull
        CharacterTypeEnum characterType) {
}
