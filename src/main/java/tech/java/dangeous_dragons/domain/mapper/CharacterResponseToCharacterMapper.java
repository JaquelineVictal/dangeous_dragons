package tech.java.dangeous_dragons.domain.mapper;

import tech.java.dangeous_dragons.application.payloads.response.character.CharacterResponse;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.Character;
import tech.java.dangeous_dragons.infrastructure.persistence.entity.character.CharacterConfig;

public class CharacterResponseToCharacterMapper {

    public static Character execute(CharacterResponse characterResponse) {

        CharacterConfig characterConfig = CharacterConfigResponseToCharacterConfigMapper.execute(characterResponse.getCharacterConfigResponse());

        Character character = new Character();
        character.setId(characterResponse.getId());
        character.setName(characterResponse.getName());
        character.setCreatedAt(characterResponse.getCreatedAt());
        character.setUpdatedAt(characterResponse.getUpdatedAt());
        character.setCharacterConfig(characterConfig);

        return character;
    }
}
