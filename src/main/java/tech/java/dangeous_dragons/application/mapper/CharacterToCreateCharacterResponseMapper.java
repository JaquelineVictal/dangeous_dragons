package tech.java.dangeous_dragons.application.mapper;

import tech.java.dangeous_dragons.application.payloads.response.character.CreateCharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CreateCharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class CharacterToCreateCharacterResponseMapper {

    public static CreateCharacterResponse execute(Character character) {
        CreateCharacterConfigResponse createCharacterConfigResponse = formatCharacterConfigResponse(character.getCharacterConfig());

        CreateCharacterResponse createCharacterResponse = new CreateCharacterResponse();
        createCharacterResponse.setId(character.getId());
        createCharacterResponse.setName(character.getName());
        createCharacterResponse.setCreateCharacterConfigResponse(createCharacterConfigResponse);

        return createCharacterResponse;
    }

    private static CreateCharacterConfigResponse formatCharacterConfigResponse(CharacterConfig characterConfig) {

        CreateCharacterConfigResponse createCharacterConfigResponse = new CreateCharacterConfigResponse();

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