package tech.java.dangeous_dragons.application.payloads.response.character;

import tech.java.dangeous_dragons.common.enums.CharacterTypeEnum;

public record CreateCharacterConfigResponse(
        long id,
        boolean isHero,
        CharacterTypeEnum characterType,
        double healthPoints,
        double attack,
        double defense,
        double agility,
        int quantityDice,
        int diceFaces

) {
}
