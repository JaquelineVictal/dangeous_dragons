package tech.java.dangeous_dragons.application.mapper;


import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class CharacterToCreateCharacterResponseMapper {

    public static CharacterResponse execute(Character character) {
        CharacterConfigResponse characterConfigResponse = formatCharacterConfigResponse(character.getCharacterConfig());

        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setId(character.getId());
        characterResponse.setName(character.getName());
        characterResponse.setCreatedAt(character.getCreatedAt());
        characterResponse.setUpdatedAt(character.getUpdatedAt());
        characterResponse.setCharacterConfigResponse(characterConfigResponse);

        return characterResponse;
    }

    private static CharacterConfigResponse formatCharacterConfigResponse(CharacterConfig characterConfig) {

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