package tech.java.dangeous_dragons.application.mapper;


import tech.java.dangeous_dragons.application.payloads.response.character.CharacterConfigResponse;
import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;

public class CharacterToCreateCharacterResponseMapper {

    public static CharacterResponse execute(Character character) {
        CharacterConfigResponse characterConfigResponse = CharacterConfigToCharacterConfigResponseMapper.execute(character.getCharacterConfig());

        CharacterResponse characterResponse = new CharacterResponse();
        characterResponse.setId(character.getId());
        characterResponse.setName(character.getName());
        characterResponse.setCreatedAt(character.getCreatedAt());
        characterResponse.setUpdatedAt(character.getUpdatedAt());
        characterResponse.setCharacterConfigResponse(characterConfigResponse);

        return characterResponse;
    }


}