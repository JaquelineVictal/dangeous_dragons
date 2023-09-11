package tech.java.dangeous_dragons.application.payloads.response.character;

public record CreateCharacterResponse(
        String name,
        CreateCharacterConfigResponse CharacterConfig

) {
}
