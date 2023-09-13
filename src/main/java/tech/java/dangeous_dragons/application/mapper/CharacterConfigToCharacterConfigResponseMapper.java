package tech.java.dangeous_dragons.application.mapper;

import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class CharacterConfigToCharacterConfigResponseMapper {

    public static CharacterConfigResponse execute(CharacterConfig characterConfig) {

        CharacterConfigResponse createCharacterConfigResponse = new CharacterConfigResponse();

        createCharacterConfigResponse.setId(characterConfig.getId());
        createCharacterConfigResponse.setHero(characterConfig.isHero());
        createCharacterConfigResponse.setCharacterType(characterConfig.getCharacterType());
        createCharacterConfigResponse.setHealthPoints(characterConfig.getHealthPoints());
        createCharacterConfigResponse.setAttack(characterConfig.getAttack());
        createCharacterConfigResponse.setDefense(characterConfig.getDefense());
        createCharacterConfigResponse.setAgility(characterConfig.getAgility());
        createCharacterConfigResponse.setQuantityDice(characterConfig.getQuantityDice());
        createCharacterConfigResponse.setDiceFaces(characterConfig.getDiceFaces());

        return createCharacterConfigResponse;
    }
}
